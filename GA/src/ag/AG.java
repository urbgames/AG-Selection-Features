package ag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excelGenerator.ChromosomeToExcel;
import util.GeneratorFile;
import weka.core.expressionlanguage.parser.sym;

public class AG {

	private int IDGenarator = 0;
	private List<ChromosomeBinary> population;
	private boolean uniqueChromosomes = true;
	private int maxCountGenes;
	private Selection selection;
	private Fitness fitness;
	private Crossover crossover;
	private Mutation mutation;
	private FactoryChromosome factoryChromosome;
	private GeneratorFile generatorFile;
	private ChromosomeToExcel chromosomeToExcel, chromosomeToExcel2;

	private void registerLog(GeneratorFile generatorFile, List<ChromosomeBinary> chromosomes) throws IOException {
		List<ChromosomeBinary> chromosomeClone = new ArrayList<>(chromosomes);
		Collections.sort(chromosomeClone, Collections.reverseOrder());
		for (ChromosomeBinary chromosome : chromosomeClone) {
			generatorFile.insertLog("Individuo: " + chromosome.getID());
			generatorFile.insertLog("Fitness: " + chromosome.getFitnessValue());
			generatorFile.insertLog("PCTCorrent: " + chromosome.getPctCorrectRate());
			generatorFile.insertLog("FAR: " + chromosome.getFAR());
			generatorFile.insertLog("FRR: " + chromosome.getFRR());
			generatorFile.insertLog(chromosome.toStringBinaryGenesNumbers());
			generatorFile.insertLog("");
		}
	}

	public AG(int sizePopulation, int countGeneration, String order) throws Exception {
		this.chromosomeToExcel = new ChromosomeToExcel(order);
		this.chromosomeToExcel2 = new ChromosomeToExcel("RELATÓRIO " + order);
		this.selection = new Selection();
		this.fitness = new Fitness();
		this.crossover = new Crossover();
		this.mutation = new Mutation();
		this.population = new ArrayList<>();
		this.factoryChromosome = FactoryChromosome.getInstance();

		GeneratorFile file = new GeneratorFile("SEED" + order);
		fitness.changeSeed();
		file.insertLog("" + fitness.getSeedClassification());
		file.closeLog();

		for (int i = 0; i < sizePopulation; i++) {
			ChromosomeBinary chromosome = factoryChromosome.factoryChromosome();
			chromosome.randonInitializeGenesBinary();
			population.add(chromosome);
		}

		generatorFile = new GeneratorFile(order);
		chromosomeToExcel.insertLabelRows();
		chromosomeToExcel2.insertLabelRows2();

		for (int i = 0; i < countGeneration; i++) {
			System.out.println("Geração: " + (i + 1));

			String labelGeneration = "----------------------------Generação: (" + (i + 1)
					+ ") --------------------------";

			generatorFile.insertLog(labelGeneration);

			long starTime = System.currentTimeMillis();

			//
			// EVALUATION FITNESS INITIAL
			if (i == 0)
				fitness.fitnessGeneratorClassificator(population);

			// SELECTION PARENTS
			List<ChromosomeBinary> parents = new ArrayList<>();
			parents.addAll(selection.rouletteSelect(population, sizePopulation, false));

			// CROSSOVER
			List<ChromosomeBinary> offspring = crossover.randomPoint(parents);

			// MUTATION
			mutation.mutationBinaryAllGenes(offspring, 0.1);

			// EVALUATION FITNESS TO OFFSPRING
			fitness.fitnessGeneratorClassificator(offspring);

			// JOIN POPULATION AND OFFSPRINT
			List<ChromosomeBinary> nextPopulation = new ArrayList<>();
			nextPopulation.addAll(population);
			nextPopulation.addAll(offspring);

			// SELECT POPULATION TO NEXT GENERATION
			population.clear();
			population.addAll(selection.rank(nextPopulation, 1, false));
			population.addAll(selection.tournament(nextPopulation, sizePopulation - 1, false, 2));

			long totalTime = System.currentTimeMillis() - starTime;

			registerLog(this.generatorFile, this.population);
			chromosomeToExcel.converterChromosomeToExcelRow(population, i);
			chromosomeToExcel2.converterChromosomeToExcelRow2(population, i, totalTime);

		}

		generatorFile.closeLog();
		chromosomeToExcel.closeFile();
		chromosomeToExcel2.closeFile();
		System.out.println("FINAL DA SELEÇÃO");

	}

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 10; i++) {
			System.out.println("------------------ Repetição" + i + "-------------------");
			// PARAMETERS (SIZE POPULATION, SIZE GENERATION)
			new AG(100, 100, "" + (i + 1));
		}
	}

	protected int geratorID() {
		return ++this.IDGenarator;
	}

	public boolean isUniqueChromosomes() {
		return uniqueChromosomes;
	}

	public void setUniqueChromosomes(boolean uniqueChromosomes) {
		this.uniqueChromosomes = uniqueChromosomes;
	}

	public int getMaxCountGenes() {
		return maxCountGenes;
	}

	public void setMaxCountGenes(int maxCountGenes) {
		this.maxCountGenes = maxCountGenes;
	}

}
