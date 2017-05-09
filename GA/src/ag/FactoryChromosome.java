package ag;

import classificator.Classification;

public final class FactoryChromosome {

	private static volatile FactoryChromosome instance;
	private int IDGenarator = 0;
	private static int lengthGenes;
	private static Classification classification;

	public static FactoryChromosome getInstance() throws Exception {

		if (instance == null) {
			synchronized (FactoryChromosome.class) {
				if (instance == null) {
					classification = Classification.getInstance();
					lengthGenes = classification.getLegth();
					instance = new FactoryChromosome();
				}
			}
		}
		return instance;
	}

	public ChromosomeBinary factoryChromosome() {
		return new ChromosomeBinary(geratorID(), lengthGenes);
	}

	private int geratorID() {
		return ++this.IDGenarator;
	}

}
