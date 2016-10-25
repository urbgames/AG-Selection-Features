package test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.Debug.Random;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;

public class TestClassification {

	private String base1 = "C:\\Users\\Urbgames\\Google Drive\\Mestrado\\_PROJETO\\KEYSTROKE\\Experimentos\\VECTOR - ALL.arff";
	private String currentBase = base1;
	private static Instances dataInitial;

	public TestClassification() throws Exception {

		System.out.println("Início classificação");
		
		dataInitial = new DataSource(currentBase).getDataSet();
		Instances data = getFitnessClafissation();
		Random rand = new Random(1);
		data.randomize(rand);

		BayesNet classifier = new BayesNet();

		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);

		RemovePercentage percentageData = new RemovePercentage();
		percentageData.setInputFormat(data);

		percentageData.setOptions(Utils.splitOptions("-P 90"));
		Instances dataTest = Filter.useFilter(data, percentageData);

		percentageData.setOptions(Utils.splitOptions("-V -P 90"));
		Instances dataTrain = Filter.useFilter(data, percentageData);

		classifier.buildClassifier(dataTrain);
		Evaluation eval = new Evaluation(dataTrain);
		eval.evaluateModel(classifier, dataTest);

		System.out.println(eval.pctCorrect());

	}

	public Instances getFitnessClafissation() throws Exception {

		String optionsSequence = "[1,0,1,1,0,1,1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1]";
		optionsSequence = optionsSequence.replaceAll("\\D", "");
		Instances dataTemp = dataInitial;
		int count = 0;
		String optionsRemove = "";
		
		for (int i = 0; i < optionsSequence.length(); i++) {
			if (optionsSequence.charAt(i) == '0') {
				count++;
				optionsRemove += "" + (i+1);
				optionsRemove += ",";
			}
		}
		
		if (count != 0)
			optionsRemove = optionsRemove.substring(0, optionsRemove.length() - 1);

		String[] options = new String[2];
		options[0] = "-R";
		options[1] = optionsRemove;

		Remove remove = new Remove();
		remove.setOptions(options);
		remove.setInputFormat(dataTemp);

		dataTemp = Filter.useFilter(dataTemp, remove);
		saveFile(dataTemp);

		return dataTemp;
	}

	public void saveFile(Instances instances) throws IOException{
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		saver.setFile(new File("C:\\Users\\Urbgames\\Documents\\selectionGA.arff"));
		saver.writeBatch();
	}
	
	public static void main(String[] args) throws Exception {
		new TestClassification();
	}

}
