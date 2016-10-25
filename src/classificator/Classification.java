package classificator;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;

public final class Classification {

	private String base1 = "C:\\Users\\Urbgames\\Google Drive\\Mestrado\\_PROJETO\\KEYSTROKE\\Experimentos\\VECTOR - ALL.arff";
	private String base2 = "D:\\BACKUP\\Documentos\\_KEYSTROKE\\_BASE 01\\keystroke_normalized_all.arff";
	private static Instances dataAll = null;
	private String baseCurrent = base1;

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

		// percentageData.setOptions(Utils.splitOptions("-P 10"));
		// Instances dataTrain = Filter.useFilter(data, percentageData);
		//
		// percentageData.setOptions(Utils.splitOptions("-V -P 10"));
		// Instances dataTest = Filter.useFilter(data, percentageData);

		percentageData.setOptions(Utils.splitOptions("-P 90"));
		Instances dataTest = Filter.useFilter(data, percentageData);

		percentageData.setOptions(Utils.splitOptions("-V -P 90"));
		Instances dataTrain = Filter.useFilter(data, percentageData);

		classifier.buildClassifier(dataTrain);
		Evaluation eval = new Evaluation(dataTrain);
		eval.evaluateModel(classifier, dataTest);

		return (float) eval.pctCorrect();

	}

	public Classification() throws Exception {
		if (dataAll == null) {
			dataAll = new DataSource(baseCurrent).getDataSet();
			Random rand = new Random();
			dataAll.randomize(rand);
		}
	}

}
