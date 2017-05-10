package ag;

import java.util.List;

import classificator.Classification;
import classificator.ResultClassification;

public class Fitness {
	public void fitnessGeneratorClassificator(List<ChromosomeBinary> chromosomes, Classification classification) throws Exception {
		for (int i = 0; i < chromosomes.size(); i++) {
			ResultClassification resultClassification = classification
					.getFitnessClafissation(chromosomes.get(i).getBinaryGenes());
			chromosomes.get(i).setFitnessValue(resultClassification.getPctCorrect());
			chromosomes.get(i).setPctCorrectRate(resultClassification.getPctCorrect());
			chromosomes.get(i).setFAR(resultClassification.getFAR());
			chromosomes.get(i).setFRR(resultClassification.getFRR());
		}
	}

}
