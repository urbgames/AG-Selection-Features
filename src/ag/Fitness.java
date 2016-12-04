package ag;

import java.util.List;

import classificator.Classification;
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
	
	public void fitnessGeneratorClassificator(List<ChromosomeBinary> chromosomes) throws Exception {
		printStatus();
		classification = Classification.getInstance();
		for (int i = 0; i < chromosomes.size(); i++) {
//			System.out.println(chromosomes.get(i).getID());
			chromosomes.get(i).setFitnessValue(classification.getFitnessClafissation(chromosomes.get(i).getBinaryGenes()));
//			System.out.println();
		}
	}

	private void printStatus() {
		PrintStatusChromosome.InitializeFitness();
	}

}
