package util;

import java.text.DecimalFormat;
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

	public TestGA() {
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

		// double[] array = new double[] { 10.11, 10.11, 10.11, 10, 9.99 };
		// double[] arrayNormalize = MathUtil.normalize(array);
		// for (double d : arrayNormalize) {
		// System.out.println(d);
		// }
		//

//		List<Integer> teste = new ArrayList<>();
//		teste.add(5);
//		teste.add(10);
//		teste.add(0);
//		teste.add(112);
//		List<Integer> teste2 = new ArrayList<>(teste);
//		teste2.remove(0);
//		System.out.println(teste);

	}

	public double calcMin(double[] array) {
		double[] array2 = array.clone();
		Arrays.sort(array2);
		return array2[0];
	}

	public double calcMax(double[] array) {
		double[] array2 = array.clone();
		Arrays.sort(array2);
		return array2[array2.length - 1];
	}

	public double calcStd(double[] array) {
		double mean = calcMean(array);
		double std = 0;
		for (double d : array) {
			std += Math.sqrt(Math.pow(d - mean, 2));
		}
		if (array.length > 0) {
			std = std / array.length;
			return std;
		} else
			return 0;
	}

	public double calcMean(double[] array) {
		double mean = 0;
		for (double d : array) {
			mean += d;
		}
		if (array.length > 0) {
			mean = mean / array.length;
			return mean;
		} else
			return 0;
	}

	protected static int geratorID(int IDGenarator) {
		return ++IDGenarator;
	}

	public static void main(String[] args) throws Exception {

		new TestGA();
	}
}