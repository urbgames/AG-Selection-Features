package ag;

import java.util.List;
import java.util.Random;

public class Mutation {

	// MUTATING ALL GENES (RANDOM)
	public void mutationBinaryAllGenes(List<ChromosomeBinary> chromosomes) {
		for (int i = 0; i < chromosomes.size(); i++) {
			for (int j = 0; j < chromosomes.get(i).getBinaryGenes().length; j++) {
				if (Math.random() <= 0.5 ? true : false)
					chromosomes.get(i).inverterGene(j);
			}
		}
	}

	// MUTATING A CONSTANTE NUMBER OF THE GENES (DEFINIED BY J)
	public void mutationBinaryConstanteNumber(List<ChromosomeBinary> chromosomes, int countGenesMutation) {
		for (int i = 0; i < chromosomes.size(); i++) {
			for (int j = 0; j < countGenesMutation; j++) {
				chromosomes.get(i).inverterGene(new Random().nextInt(chromosomes.get(i).getBinaryGenes().length - 1));
			}
		}
	}

}
