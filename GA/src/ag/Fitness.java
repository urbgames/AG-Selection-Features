package ag;

import java.util.List;

import classificator.Classification;
import classificator.ResultClassification;

public class Fitness {

	private Classification classification;

	public int getSeedClassification() throws Exception {
		classification = Classification.getInstance();
		return classification.getSeed();
	}

	public void changeSeed() throws Exception {
		classification = Classification.getInstance();
		classification.changeSeed();
	}

	public void fitnessGeneratorClassificator(List<ChromosomeBinary> chromosomes) throws Exception {
		classification = Classification.getInstance();
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
