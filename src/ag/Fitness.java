package ag;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import classificator.Classification;
import classificator.ResultClassification;
import util.PrintStatusChromosome;

public class Fitness {

	private Classification classification;

	public void fitnessGenerator(List<ChromosomeBinary> chromosomes) {
		printStatus();
		for (int i = 0; i < chromosomes.size(); i++) {
			float fitness = 0;
			for (int j = 0; j < chromosomes.get(i).countGenes(); j++) {
				if (chromosomes.get(i).getBinaryGenes()[j]) {
					fitness += chromosomes.get(i).getGenes().get(j);
				}
			}
			chromosomes.get(i).setFitnessValue(fitness);
		}
	}

	public void changeSeed() throws IOException {
		classification.changeSeed();
	}

	public void closeLog() throws IOException {
		classification.closeLog();
	}

	public void fitnessGeneratorClassificator(List<ChromosomeBinary> chromosomes) throws Exception {
		printStatus();
		classification = Classification.getInstance();
		for (int i = 0; i < chromosomes.size(); i++) {
			ResultClassification resultClassification = classification
					.getFitnessClafissation(chromosomes.get(i).getBinaryGenes());
			double fitness = ((int) resultClassification.getPctCorrect()) + (1 - resultClassification.getFAR());
			chromosomes.get(i).setFitnessValue(fitness);
			chromosomes.get(i).setPctCorrectRate(resultClassification.getPctCorrect());
			chromosomes.get(i).setFAR(resultClassification.getFAR());
			chromosomes.get(i).setFRR(resultClassification.getFRR());
		}
	}

	private void printStatus() {
		PrintStatusChromosome.InitializeFitness();
	}

}
