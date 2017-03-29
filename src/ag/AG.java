package ag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.rmi.CORBA.Util;

import excelGenerator.ChromosomeToExcel;
import util.GeneratorFile;
import util.MathUtil;

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
	private GeneratorFile generatorFile, generatorFileParentsAfterCrossAndMutation,
			generatorFileOffspringBeforeCrossAndMutation;
	private ChromosomeToExcel chromosomeToExcel, chromosomeToExcelParentsAfterCrossAndMutation,
			chromosomeToExcelOffspringBeforeCrossAndMutation;

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
		this.chromosomeToExcelParentsAfterCrossAndMutation = new ChromosomeToExcel("After" + order);
		this.chromosomeToExcelOffspringBeforeCrossAndMutation = new ChromosomeToExcel("Before" + order);
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
		generatorFileParentsAfterCrossAndMutation = new GeneratorFile("Parents" + order);
		generatorFileOffspringBeforeCrossAndMutation = new GeneratorFile("Offspring" + order);
		chromosomeToExcel.insertLabelRows();
		chromosomeToExcelParentsAfterCrossAndMutation.insertLabelRows();
		chromosomeToExcelOffspringBeforeCrossAndMutation.insertLabelRows();

		for (int i = 0; i < countGeneration; i++) {
			System.out.println("Geração: " + (i + 1));

			String labelGeneration = "----------------------------Generação: (" + (i + 1)
					+ ") --------------------------";

			generatorFile.insertLog(labelGeneration);
			generatorFileParentsAfterCrossAndMutation.insertLog(labelGeneration);
			generatorFileOffspringBeforeCrossAndMutation.insertLog(labelGeneration);
			
			//
			// EVALUATION FITNESS INITIAL
			if (i == 0)
				fitness.fitnessGeneratorClassificator(population);

			
			// SELECTION PARENTS 
			List<ChromosomeBinary> parents = new ArrayList<>();
			parents.addAll(selection.rouletteSelect(population, sizePopulation, false));

			registerLog(this.generatorFileParentsAfterCrossAndMutation, parents);
			chromosomeToExcelParentsAfterCrossAndMutation.converterChromosomeToExcelRow(parents, i);

			// CROSSOVER
			List<ChromosomeBinary> offspring = crossover.onePoint(parents, 10);

			// MUTATION
			mutation.mutationBinaryAllGenes(offspring, 0.1);

			// EVALUATION FITNESS TO OFFSPRING
			fitness.fitnessGeneratorClassificator(offspring);
			registerLog(generatorFileOffspringBeforeCrossAndMutation, offspring);

			chromosomeToExcelOffspringBeforeCrossAndMutation.converterChromosomeToExcelRow(offspring, i);

			//JOIN POPULATION AND OFFSPRINT
			List<ChromosomeBinary> nextPopulation = new ArrayList<>();
			nextPopulation.addAll(population);
			nextPopulation.addAll(offspring);


			// SELECT POPULATION TO NEXT GENERATION
			population.clear();
			population.addAll(selection.rank(nextPopulation, 1, false));
			population.addAll(selection.tournament(nextPopulation, sizePopulation - 1, false, 2));

			registerLog(this.generatorFile, this.population);
			chromosomeToExcel.converterChromosomeToExcelRow(population, i);

		}

		generatorFile.closeLog();
		generatorFileParentsAfterCrossAndMutation.closeLog();
		generatorFileOffspringBeforeCrossAndMutation.closeLog();

		chromosomeToExcel.closeFile();
		chromosomeToExcelParentsAfterCrossAndMutation.closeFile();
		chromosomeToExcelOffspringBeforeCrossAndMutation.closeFile();

		System.out.println("FINAL DA SELEÇÃO");

	}

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 5; i++) {
			System.out.println("------------------ Repetição" + i + "-------------------");
			// PARAMETERS (SIZE POPULATION, SIZE GENERATION)
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
