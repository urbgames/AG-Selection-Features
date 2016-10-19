package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Selection {

	// https://en.wikipedia.org/wiki/Fitness_proportionate_selection
	public List<ChromosomeBinary> rouletteSelect(List<ChromosomeBinary> chromosome, int n, boolean percente) {

		if (percente) {
			n = (int) (chromosome.size() * (n / 100.0f));
		}

		List<ChromosomeBinary> chromosomeParent = new ArrayList<>();
		double sumFitness = 0;

		for (ChromosomeBinary chromosome2 : chromosome) {
			sumFitness += chromosome2.getFitnessValue();
		}

		for (int j = 0; j < n; j++) {
			double valueRandom = new Random().nextDouble() * sumFitness;
			for (ChromosomeBinary chromosome2 : chromosome) {
				valueRandom -= chromosome2.getFitnessValue();
				if (valueRandom <= 0) {
					chromosomeParent.add(chromosome2);
					break;
				}
			}
		}

		return chromosomeParent;

	}

	public List<ChromosomeBinary> rank(List<ChromosomeBinary> chromosomes, int count) {
		
		List<ChromosomeBinary> selectionChromosomes = new ArrayList<>();
		Collections.sort(chromosomes, Collections.reverseOrder());

		if (count>chromosomes.size()) {
			throw new IndexOutOfBoundsException("O tamanho do Selection Rank é superior à quantidade de cromossomos.");
		}
		
		for (int i = 0; i < count; i++) {
			selectionChromosomes.add(chromosomes.remove(0));
		}

		return selectionChromosomes;

	}

}
