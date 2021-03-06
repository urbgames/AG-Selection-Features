package ag;

import java.util.ArrayList;
import java.util.List;

public class Crossover {

	private FactoryChromosome factoryChromosome;

	public List<ChromosomeBinary> onePoint(List<ChromosomeBinary> parents, int point) throws Exception {
		this.factoryChromosome = FactoryChromosome.getInstance();
		List<ChromosomeBinary> offspring = new ArrayList<>();
		
		for (int i = 1; i < parents.size(); i += 2) {

			ChromosomeBinary chromosomeOff1 = factoryChromosome.factoryChromosome();
			ChromosomeBinary chromosomeOff2 = factoryChromosome.factoryChromosome();
			for (int j = 0; j < point; j++) {

				chromosomeOff1.setGene(j, parents.get(i - 1).getBinaryGenes()[j]);
				chromosomeOff2.setGene(j, parents.get(i).getBinaryGenes()[j]);

			}

			for (int j = point; j < parents.get(0).getBinaryGenes().length; j++) {

				chromosomeOff1.setGene(j, parents.get(i).getBinaryGenes()[j]);
				chromosomeOff2.setGene(j, parents.get(i - 1).getBinaryGenes()[j]);

			}

			offspring.add(chromosomeOff1);
			offspring.add(chromosomeOff2);

		}

		return offspring;

	}

}
