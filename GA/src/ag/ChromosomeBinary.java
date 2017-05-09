package ag;

public class ChromosomeBinary implements Comparable<ChromosomeBinary> {

	private int ID;
	private boolean isUnique;
	private int maxCountGenes;
	private double fitnessValue;
	private double pctCorrectRate;
	private double FAR;
	private double FRR;
	private boolean[] binaryGenes;

	public ChromosomeBinary(int ID, int maxCountGenes) {
		this.ID = ID;
		this.maxCountGenes = maxCountGenes;
		this.binaryGenes = new boolean[maxCountGenes];
	}

	public void randonInitializeGenesBinary() {
		for (int i = 0; i < binaryGenes.length; i++) {
			binaryGenes[i] = Math.random() <= 0.5 ? true : false;
		}
	}

	public void setGene(int gene, boolean value) {
		this.binaryGenes[gene] = value;
	}

	public void addGene(int gene) {
		this.binaryGenes[gene] = true;
	}

	public void removeGene(int gene) {
		this.binaryGenes[gene] = false;
	}

	public void inverterGene(int gene) {
		this.binaryGenes[gene] = !this.binaryGenes[gene];
	}

	public int countGenes() {
		return this.maxCountGenes;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public int getMaxCountGenes() {
		return maxCountGenes;
	}

	public void setMaxCountGenes(int maxCountGenes) {
		this.maxCountGenes = maxCountGenes;
	}

	public int getID() {
		return ID;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean[] getBinaryGenes() {
		return binaryGenes;
	}

	public String toStringBinaryGenesNumbers() {
		String arrayString = "[";
		for (int i = 0; i < binaryGenes.length; i++) {
			arrayString += binaryGenes[i] ? 1 : 0;
			if (i + 1 != binaryGenes.length) {
				arrayString += ",";
			}
		}
		arrayString += "]";
		return arrayString;
	}

	public String toStringBinaryGenesBoolean() {
		String arrayString = "[";
		for (int i = 0; i < binaryGenes.length; i++) {
			arrayString += binaryGenes[i] ? "true" : "false";
			if (i + 1 != binaryGenes.length) {
				arrayString += ",";
			}
		}
		arrayString += "]";
		return arrayString;
	}

	public void setBinaryGenes(boolean[] binaryGenes) {
		this.binaryGenes = binaryGenes;
	}

	public double getPctCorrectRate() {
		return pctCorrectRate;
	}

	public void setPctCorrectRate(double pctCorrectRate) {
		this.pctCorrectRate = pctCorrectRate;
	}

	public double getFAR() {
		return FAR;
	}

	public void setFAR(double fAR) {
		FAR = fAR;
	}

	public double getFRR() {
		return FRR;
	}

	public void setFRR(double fRR) {
		FRR = fRR;
	}

	@Override
	public int compareTo(ChromosomeBinary chromosomeBinary) {
		if (this.fitnessValue < chromosomeBinary.getFitnessValue())
			return -1;
		else if (this.fitnessValue > chromosomeBinary.getFitnessValue())
			return 1;
		else
			return 0;
	}

	public int getEnableFeatures() {
		int count = 0;
		for (int i = 0; i < binaryGenes.length; i++)
			if (binaryGenes[i])
				count++;
		return count;
	}

	public int getDisabledFeatures() {
		int count = 0;
		for (int i = 0; i < binaryGenes.length; i++)
			if (!binaryGenes[i])
				count++;
		return count;
	}

	public double getFeatureReduction() {
		return (double) (100 * getDisabledFeatures()) / maxCountGenes;
	}

}
