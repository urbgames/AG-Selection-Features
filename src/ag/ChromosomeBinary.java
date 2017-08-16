package ag;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class ChromosomeBinary implements Comparable<ChromosomeBinary> {

	private int ID;
	private int maxCountGenes;
	private double fitnessValue;
	private double pctCorrectRate;
	private double FAR;
	private double FRR;
	private boolean[] binaryGenes;

	@JsonCreator
	public ChromosomeBinary(@JsonProperty("ID") int ID, @JsonProperty("maxCountGenes") int maxCountGenes,
			@JsonProperty("fitnessValue") double fitnessValue, @JsonProperty("pctCorrectRate") double pctCorrectRate,
			@JsonProperty("FAR") double FAR, @JsonProperty("FRR") double FRR,
			@JsonProperty("binaryGenes") boolean[] binaryGenes) {
		this.ID = ID;
		this.maxCountGenes = maxCountGenes;
		this.fitnessValue = fitnessValue;
		this.pctCorrectRate = pctCorrectRate;
		this.FAR = FAR;
		this.FRR = FRR;
		this.binaryGenes = binaryGenes;
	}

	public ChromosomeBinary(int ID, int maxCountGenes) {
		this.ID = ID;
		this.maxCountGenes = maxCountGenes;
		this.binaryGenes = new boolean[maxCountGenes];
		this.fitnessValue = 0;
		randonInitializeGenesBinary();
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

	public void setID(int iD) {
		ID = iD;
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

	@JsonIgnore
	public int getEnableFeatures() {
		int count = 0;
		for (int i = 0; i < binaryGenes.length; i++)
			if (binaryGenes[i])
				count++;
		return count;
	}

	@JsonIgnore
	public int getDisabledFeatures() {
		int count = 0;
		for (int i = 0; i < binaryGenes.length; i++)
			if (!binaryGenes[i])
				count++;
		return count;
	}

	@JsonIgnore
	public double getFeatureReduction() {
		return (double) (100 * getDisabledFeatures()) / maxCountGenes;
	}

}
