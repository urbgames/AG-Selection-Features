package ag;

import java.util.ArrayList;
import java.util.List;

import classificator.Classification;
import excelGenerator.ChromosomeToExcel;
import thread.ThreadManager;

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
	private ChromosomeToExcel chromosomeToExcel2;

	public AG(int sizePopulation, int countGeneration, int order, Classification classifier) throws Exception {
		this.chromosomeToExcel2 = new ChromosomeToExcel(classifier.getClassifierName() + "_ExperimentoGA" + order);
		this.selection = new Selection();
		this.fitness = new Fitness();
		this.crossover = new Crossover();
		this.mutation = new Mutation();
		this.population = new ArrayList<>();
		this.factoryChromosome = FactoryChromosome.getInstance(classifier);

		for (int i = 0; i < sizePopulation; i++) {
			ChromosomeBinary chromosome = factoryChromosome.factoryChromosome();
			chromosome.randonInitializeGenesBinary();
			population.add(chromosome);
		}

		chromosomeToExcel2.insertLabelRows2();

		for (int i = 0; i < countGeneration; i++) {
			
			long starTime = System.currentTimeMillis();

			//
			// EVALUATION FITNESS INITIAL
			if (i == 0)
				fitness.fitnessGeneratorClassificator(population, classifier);

			// SELECTION PARENTS
			List<ChromosomeBinary> parents = new ArrayList<>();
			parents.addAll(selection.rouletteSelect(population, sizePopulation, false));

			// CROSSOVER
			List<ChromosomeBinary> offspring = crossover.randomPoint(parents, classifier);

			// MUTATION
			mutation.mutationBinaryAllGenes(offspring, 0.1);

			// EVALUATION FITNESS TO OFFSPRING
			fitness.fitnessGeneratorClassificator(offspring, classifier);

			// JOIN POPULATION AND OFFSPRINT
			List<ChromosomeBinary> nextPopulation = new ArrayList<>();
			nextPopulation.addAll(population);
			nextPopulation.addAll(offspring);

			// SELECT POPULATION TO NEXT GENERATION
			population.clear();
			population.addAll(selection.rank(nextPopulation, 1, false));
			population.addAll(selection.tournament(nextPopulation, sizePopulation - 1, false, 2));

			long totalTime = System.currentTimeMillis() - starTime;

			chromosomeToExcel2.converterChromosomeToExcelRow2(population, i, totalTime, classifier.getSeed());

		}

		chromosomeToExcel2.closeFile();

	}

	public static void main(String[] args) throws Exception {
		new ThreadManager();
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
