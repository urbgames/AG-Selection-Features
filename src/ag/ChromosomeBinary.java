package ag;

import java.util.ArrayList;
import java.util.List;

public class ChromosomeBinary implements Comparable<ChromosomeBinary> {

	private List<Float> genes;
	private int ID;
	private boolean isUnique;
	private int maxCountGenes;
	private float fitnessValue;
	private boolean[] binaryGenes;

	public ChromosomeBinary(int ID, boolean isUnique, int maxCountGenes) {
		this.maxCountGenes = maxCountGenes;
		this.genes = new ArrayList<Float>();
		this.ID = ID;
		this.isUnique = isUnique;
	}

	public ChromosomeBinary(int ID, List<Float> genes) {
		this.genes = genes;
		this.ID = ID;
		this.binaryGenes = new boolean[genes.size()];
	}

	public ChromosomeBinary(int ID, int lengthGenes) {
		this.ID = ID;
		this.binaryGenes = new boolean[lengthGenes];
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

	public boolean addGene(List<Float> genes) {
		boolean returnAdd = false;

		for (float gene : genes) {
			if (this.genes.size() < maxCountGenes) {
				if (this.isUnique) {
					if (!this.genes.contains(gene)) {
						this.genes.add(gene);
						returnAdd = true;
					}
				} else {
					this.genes.add(gene);
					returnAdd = true;
				}
			} else
				break;
		}

		return returnAdd;
	}

	public int countGenes() {
		return this.genes.size();
	}

	public float getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(float fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public List<Float> getGenes() {
		return genes;
	}

	public void setGenes(List<Float> genes) {
		this.genes = genes;
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

	public String toStringBinaryGenesToStringNumber() {
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

	public String toStringBinaryGenesToStringBoolean() {
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

	@Override
	public int compareTo(ChromosomeBinary chromosomeBinary) {
		if (this.fitnessValue < chromosomeBinary.getFitnessValue())
			return -1;
		else if (this.fitnessValue > chromosomeBinary.getFitnessValue())
			return 1;
		else
			return 0;
	}

}
