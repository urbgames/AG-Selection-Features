package classificator;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;

public final class Classification {

	private String base1 = "C:\\Users\\Urbgames\\\\Documents\\VECTOR - ALL.arff";
	private String base2 = "C:\\Users\\Urbgames\\Documents\\keystroke_normalized_1.arff";
	private String base3 = "C:\\Users\\Urbgames\\Documents\\keystroke_71features.arff";
	private static Instances dataAll = null;
	private String baseCurrent = base3;

	private static volatile Classification classification;

	public int getLegth() {
		return dataAll.numAttributes() - 1;
	}

	public static Classification getInstance() throws Exception {

		if (classification == null) {
			synchronized (Classification.class) {
				if (classification == null) {
					classification = new Classification();
				}
			}
		}
		return classification;
	}

	public float getFitnessClafissation(boolean[] binaryGenes) throws Exception {

		Instances dataTemp = dataAll;
		int count = 0;
		String optionsRemove = "";

		for (int i = 0; i < binaryGenes.length; i++) {
			if (!binaryGenes[i]) {
				count++;
				optionsRemove += "" + (i + 1);
				optionsRemove += ",";
			}
		}

		String arrayString = "";
		for (int i = 0; i < binaryGenes.length; i++) {
			arrayString += binaryGenes[i] ? 1 : 0;
			if (i + 1 != binaryGenes.length) {
				arrayString += ",";
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

		return classification(dataTemp);
	}

	private float classification(Instances data) throws Exception {

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

//		Evaluation eval = new Evaluation(data);
//		eval.crossValidateModel(classifier, data, 10, new Random(1));

		return (float) eval.pctCorrect();

	}

	public Classification() throws Exception {
		if (dataAll == null) {
			dataAll = new DataSource(baseCurrent).getDataSet();
			dataAll.randomize(new Random());

			ArffSaver arffSaver = new ArffSaver();
			arffSaver.setInstances(dataAll);
			File file = new File("D:/_Experimentos14/dataAll.arff");
			arffSaver.setFile(file);
			arffSaver.writeBatch();
			
		}
	}

}
