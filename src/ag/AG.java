package ag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excelGenerator.ChromosomeToExcel;
import excelGenerator.ExcelGenerator;
import util.GeneratorFile;

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
	private ChromosomeToExcel chromosomeToExcel;

	private void registerLog() throws IOException {
		List<ChromosomeBinary> chromosomeClone = new ArrayList<>(population);
		Collections.sort(chromosomeClone, Collections.reverseOrder());
		for (ChromosomeBinary chromosome : chromosomeClone) {
			generatorFile.insertLog("Individuo: " + chromosome.getID());
			generatorFile.insertLog("Fitness: " + chromosome.getFitnessValue());
			generatorFile.insertLog(chromosome.toStringBinaryGenesNumbers());
			generatorFile.insertLog("");
		}
	}

	public AG(int sizePopulation, int countGeneration, String order) throws Exception {
		this.chromosomeToExcel = new ChromosomeToExcel(order);
		this.selection = new Selection();
		this.fitness = new Fitness();
		this.crossover = new Crossover();
		this.mutation = new Mutation();
		this.population = new ArrayList<>();
		this.factoryChromosome = FactoryChromosome.getInstance();

		for (int i = 0; i < sizePopulation; i++) {
			ChromosomeBinary chromosome = factoryChromosome.factoryChromosome();
			chromosome.randonInitializeGenesBinary();
			population.add(chromosome);
		}

		generatorFile = new GeneratorFile(order);
		chromosomeToExcel.insertLabelRows();

		for (int i = 0; i < countGeneration; i++) {
			System.out.println("Geração: " + (i + 1));

			generatorFile
					.insertLog("----------------------------Generação: (" + (i + 1) + ") --------------------------");
			//
			// EVALUATION FITNESS TO PARENTS
			if (i == 0)
				fitness.fitnessGeneratorClassificator(population);

			// SELECTION PARENTS TO NEXT GENERATION
			// VALUE RANGE BETWEEN 0.5 and 1
			List<ChromosomeBinary> parents = new ArrayList<>();
			parents.addAll(selection.rank(population, 1, false));
			parents.addAll(selection.rouletteSelectNormalized(population, sizePopulation - 1, false));

			// CROSSOVER
			List<ChromosomeBinary> offspring = crossover.onePoint(parents, 1);

			// MUTATION
			mutation.mutationBinaryConstanteNumber(offspring, 7);

			// EVALUATION FITNESS TO OFFSPRING
			fitness.fitnessGeneratorClassificator(offspring);

			// SELECT POPULATION TO NEXT GENERATION
			List<ChromosomeBinary> nextPopulation = new ArrayList<>();
			nextPopulation.addAll(population);
			nextPopulation.addAll(offspring);

			population.clear();
			population.addAll(selection.rank(nextPopulation, 1, false));
			population.addAll(selection.tournament(nextPopulation, sizePopulation - 1, false, 4));

			registerLog();
			chromosomeToExcel.converterChromosomeToExcelRow(population, i);

		}

		generatorFile.closeLog();
		chromosomeToExcel.closeFile();
		System.out.println("FINAL DA SELEÇÃO");

	}

	public static void main(String[] args) throws Exception {
		// SIZE POPULATION, COUNT GENERATION
		// new AG(1, 2, "0");

		for (int i = 4; i < 30; i++) {
			System.out.println("------------------ Repetição" + i + "-------------------");
			new AG(60, 1000, "" + (i + 1));
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
