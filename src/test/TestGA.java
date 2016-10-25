package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.xml.parsers.FactoryConfigurationError;

import ag.ChromosomeBinary;
import ag.FactoryChromosome;
import ag.Fitness;
import ag.Selection;

public class TestGA {

	public static void main(String[] args) throws Exception {
		
		// ArrayList<Integer> arrayList = new ArrayList<>();
		// int value = 10000;
		// arrayList.add(1);
		// arrayList.add(2);
		// arrayList.add(3);
		// arrayList.add(4);
		// arrayList.add(5);
		// arrayList.add(6);
		// arrayList.add(value);
		//
		// arrayList.subList(0, 7).forEach(s->System.out.println(s));
		//
		// System.out.println(new Random().nextDouble());

		// int IDGenarator = 0;
		//
		// List<Chromosome> population = new ArrayList<>();
		// for (int i = 0; i < 10; i++) {
		// population.add(new Chromosome(geratorID(IDGenarator), true, 20));
		// }
		// new Fitness().fitnessGenerator1(population);
		// new Selection().rouletteSelect(population, 10,true).forEach(x ->
		// System.out.println(x.getGenes()));

		// List<String> set3 = new ArrayList<>();
		// List<String> set4 = new ArrayList<>();
		//
		// set3.addAll(Arrays.asList(new String[] { "A", "G", "C" }));
		// set4.addAll(Arrays.asList(new String[] { "A", "B", "C", "D", "E" }));
		//
		// boolean contains = false;
		//
		// for (String string : set4) {
		// if (set3.contains(string)) {
		// contains = true;
		// break;
		// }
		// }
		//

		// Set<String> list = new HashSet<>();
		// list.addAll(set3);
		// list.addAll(set4);
		//
		// set4.addAll(set3);
		// System.out.println(list);
		//
		// System.out.println(contains);

		// System.out.println(Math.random() < 0.5 ? true : false);

		// System.out.println((float) new Random().nextInt(100));

		// ChromosomeBinary chromosomeBinary =
		// FactoryChromosome.getInstance().factoryChromosome();
		// System.out.println(chromosomeBinary.getBinaryGenesToStringNumber());
		// System.out.println(chromosomeBinary.getBinaryGenesToStringBoolean());
		//
		// ChromosomeBinary chromosomeBinary2 =
		// FactoryChromosome.getInstance().factoryChromosome();
		// System.out.println(chromosomeBinary2.getBinaryGenesToStringNumber());
		// System.out.println(chromosomeBinary2.getBinaryGenesToStringBoolean());

		// System.out.println(new Random().nextInt(1));

		// FactoryChromosome factoryChromosome =
		// FactoryChromosome.getInstance();
		// List<ChromosomeBinary> population = new ArrayList<>();
		//
		// for (int i = 0; i < 3; i++) {
		// ChromosomeBinary chromosome = factoryChromosome.factoryChromosome();
		// chromosome.randonInitializeGenesBinary();
		// population.add(chromosome);
		// }
		//
		// // EVALUATION FITNESS TO PARENTS
		// Fitness fitness = new Fitness();
		// fitness.fitnessGeneratorClassificator(population);
		//
		// population.forEach(x -> System.out.println("Individuo: " + x.getID()
		// + " - Fitness: " + x.getFitnessValue()));
		//
		// population = new Selection().rank(population, 4);
		//
		// fitness.fitnessGeneratorClassificator(population);
		//
		// population.forEach(x -> System.out.println("Individuo: " + x.getID()
		// + " - Fitness: " + x.getFitnessValue()));

	}

	protected static int geratorID(int IDGenarator) {
		return ++IDGenarator;
	}

}
