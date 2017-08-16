package ag;

import java.util.ArrayList;
import java.util.List;

import classificator.Classification;
import excelGenerator.ChromosomeToExcel;
import excelGenerator.ExcelGenerator;
import mail.CreatableMail;
import systemStatusControl.StatusControl;
import systemStatusControl.StatusGA;
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

	public AG(int sizePopulation, int maxInteration, int experiment, Classification classifier, int seed,
			StatusGA... statusGA) throws Exception {
		this.selection = new Selection();
		this.fitness = new Fitness();
		this.crossover = new Crossover();
		this.mutation = new Mutation();
		this.population = new ArrayList<>();

		this.factoryChromosome = FactoryChromosome.getInstance(classifier);

		int currentInteration = 0;
		if (statusGA.length > 0) {
			population = statusGA[0].getPopulacao();
			seed = statusGA[0].getSeed();
			currentInteration = statusGA[0].getCurrentInteretor() + 1;
		} else {
			population = new ArrayList<>();
			for (int i = 0; i < sizePopulation; i++)
				population.add(factoryChromosome.factoryChromosome());
		}

		String nameFile = classifier.getClassifierName() + "_ExperimentoGA" + experiment;

		ExcelGenerator excelGenerator = new ExcelGenerator(nameFile);
		ChromosomeToExcel.createLabelExcel(excelGenerator);

		for (; currentInteration < maxInteration; currentInteration++) {

			long starTime = System.currentTimeMillis();

			//
			// EVALUATION FITNESS INITIAL
			if (currentInteration == 0)
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

			ChromosomeToExcel.updateExcelByGeneration(excelGenerator, population, currentInteration, totalTime,
					classifier.getSeed());
			try {
				StatusControl.insertOrUpdate(new StatusGA(population, currentInteration, maxInteration, experiment,
						sizePopulation, seed, classifier.getClassifierName()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		CreatableMail.sendMail(nameFile + ".xls", nameFile);

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
