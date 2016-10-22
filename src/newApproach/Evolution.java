package newApproach;

import java.util.Collections;
import java.util.List;

import ag.ChromosomeBinary;

public class Evolution {

	public void evolutionBestRank(List<ChromosomeBinary> chromosomes) {

		int half = chromosomes.size() / 2;
		Collections.sort(chromosomes, Collections.reverseOrder());
		ChromosomeBinary bestChromosome = chromosomes.get(0);

		for (int i = 1; i < half; i++) {
			for (int j = 0; i < chromosomes.get(i).getBinaryGenes().length; j++) {
				if (bestChromosome.getBinaryGenes()[j] != chromosomes.get(i).getBinaryGenes()[j]) {
					chromosomes.get(i).setGene(j, bestChromosome.getBinaryGenes()[j]);
					break;
				}
			}
		}
		
	}
}
