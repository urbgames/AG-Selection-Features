package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import util.MathUtil;

public class Selection {

	// https://en.wikipedia.org/wiki/Fitness_proportionate_selection
	public List<ChromosomeBinary> rouletteSelect(List<ChromosomeBinary> chromosomes, int size, boolean percent) {

		if (percent) {
			size = (int) (chromosomes.size() * (size / 100.0f));
		}

		List<ChromosomeBinary> chromosomeParent = new ArrayList<>();
		double sumFitness = 0;

		for (ChromosomeBinary chromosome2 : chromosomes) {
			sumFitness += chromosome2.getFitnessValue();
		}

		for (int j = 0; j < size; j++) {
			double valueRandom = new Random().nextDouble() * sumFitness;
			for (ChromosomeBinary chromosome2 : chromosomes) {
				valueRandom -= chromosome2.getFitnessValue();
				if (valueRandom <= 0) {
					chromosomeParent.add(chromosome2);
					break;
				}
			}
		}

		return chromosomeParent;

	}

	public List<ChromosomeBinary> rouletteSelectNormalized(List<ChromosomeBinary> chromosomes, int size,
			boolean percent) {

		if (percent) {
			size = (int) (chromosomes.size() * (size / 100.0f));
		}

		List<ChromosomeBinary> chromosomeParent = new ArrayList<>();
		double sumProportionalFitness = 0;
		double[] proportionalFitness = new double[chromosomes.size()];

		for (int i = 0; i < chromosomes.size(); i++) {
			proportionalFitness[i] = chromosomes.get(i).getFitnessValue();
			sumProportionalFitness += chromosomes.get(i).getFitnessValue();
		}

		proportionalFitness = MathUtil.normalize(proportionalFitness);

		for (int j = 0; j < size; j++) {
			double valueRandom = new Random().nextDouble() * sumProportionalFitness;
			for (int i = 0; i < proportionalFitness.length; i++) {
				valueRandom -= proportionalFitness[i];
				if (valueRandom <= 0) {
					chromosomeParent.add(chromosomes.remove(i));
					break;
				}
			}
		}

		return chromosomeParent;

	}

	public List<ChromosomeBinary> rank(List<ChromosomeBinary> chromosomes, int size) {

		List<ChromosomeBinary> selectionChromosomes = new ArrayList<>();
		Collections.sort(chromosomes, Collections.reverseOrder());

		if (size > chromosomes.size()) {
			throw new IndexOutOfBoundsException("O tamanho do Selection Rank é superior à quantidade de cromossomos.");
		}

		for (int i = 0; i < size; i++) {
			selectionChromosomes.add(chromosomes.remove(0));
		}

		return selectionChromosomes;

	}

	public List<ChromosomeBinary> tournament(List<ChromosomeBinary> chromosomes, int size, boolean percent,
			int countTournament) {
		
		if (percent) {
			size = (int) (chromosomes.size() * (size / 100.0f));
		}
		
//		List<ChromosomeBinary> chromosomeClone = new ArrayList<>(chromosomes);
		List<ChromosomeBinary> chromosomeReturn = new ArrayList<>();
		
		Random random = new Random();
		
		for (int i = 0; i < size; i++) {
			List<ChromosomeBinary> chromosomeTemp = new ArrayList<>();
			for (int j = 0; j < countTournament; j++) {
				int indexTournament = random.nextInt(chromosomes.size());
				chromosomeTemp.add(chromosomes.remove(indexTournament));
			}
			Collections.sort(chromosomeTemp,Collections.reverseOrder());
			chromosomeReturn.add(chromosomeTemp.remove(0));
			chromosomes.addAll(chromosomeTemp);
		}

		return chromosomeReturn;

	}

}
