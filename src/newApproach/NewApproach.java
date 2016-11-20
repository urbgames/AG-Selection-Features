package newApproach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ag.ChromosomeBinary;
import ag.FactoryChromosome;
import ag.Fitness;
import util.GeneratorFile;

public class NewApproach {

	private List<ChromosomeBinary> population;
	private FactoryChromosome factoryChromosome;
	private Fitness fitness;
	private Evolution evolution;
	private GeneratorFile generatorFile;

	public NewApproach(int sizeGeneration, int sizePopulation) throws Exception {

		this.population = new ArrayList<>();
		this.factoryChromosome = FactoryChromosome.getInstance();
		this.fitness = new Fitness();
		this.evolution = new Evolution();
		this.generatorFile = new GeneratorFile("0");

		for (int i = 0; i < sizePopulation; i++) {
			ChromosomeBinary chromosome = factoryChromosome.factoryChromosome();
			chromosome.randonInitializeGenesBinary();
			population.add(chromosome);
		}

		fitness.fitnessGeneratorClassificator(population);
		
		for (int i = 0; i < sizeGeneration; i++) {
			System.out.println("Geração: " + (i + 1));
			
			Collections.sort(population,Collections.reverseOrder());
			generatorLogChromosome(population);
			evolution.evolutionBestRank(population);
			
			generatorFile
					.insertLog("----------------------------Generação: (" + (i + 1) + ") --------------------------");
			
			fitness.fitnessGeneratorClassificator(population);
			Collections.sort(population,Collections.reverseOrder());
			generatorLogChromosome(population);
			

			
		}
		System.out.println("FIM DA EXECUÇÃO.");
		generatorFile.closeLog();

	}

	private void generatorLogChromosome(List<ChromosomeBinary> chromosomes) throws IOException {

		for (ChromosomeBinary chromosome : chromosomes) {

			generatorFile.insertLog("Individuo: " + chromosome.getID());
			generatorFile.insertLog(chromosome.toStringBinaryGenesNumbers());
			generatorFile.insertLog("" + chromosome.getFitnessValue());
			generatorFile.insertLog("");
		}

	}

	public static void main(String[] args) throws Exception {
		//sizeGeneration, sizePopulation
		new NewApproach(5, 20);
	}
}
