package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;

public class SelectionFeatureWeka {
	private String base1 = "C:\\Users\\Urbgames\\Google Drive\\Mestrado\\_PROJETO\\KEYSTROKE\\Experimentos\\VECTOR - ALL.arff";
	private String base2 = "C:\\Users\\Urbgames\\Documents\\vectorreduce.arff";
	private static Instances dataAll;

	public SelectionFeatureWeka() throws Exception{
		
		dataAll = new DataSource(base1).getDataSet();

		CfsSubsetEval cfsSubsetEval = new CfsSubsetEval();
		BestFirst bestFirst = new BestFirst();
		AttributeSelection attributeSelection = new AttributeSelection();
		attributeSelection.setEvaluator(cfsSubsetEval);
		attributeSelection.setSearch(bestFirst);
		attributeSelection.SelectAttributes(dataAll);
		
		System.out.println(attributeSelection.toResultsString());		

		String optionsRemove = "";
		Pattern pattern = Pattern.compile("\\:(.*)\\:( )");
		Matcher matcher = pattern.matcher(attributeSelection.toResultsString());
		if (matcher.find()) {

			optionsRemove = matcher.group(1).trim();
		}
		
		optionsRemove+= "," + dataAll.numAttributes();
		
		String[] options = new String[2];
		options[0] = "-R";
		options[1] = optionsRemove;

		System.out.println(optionsRemove);
		
		Remove remove = new Remove();
		remove.setOptions(options);
		Instances dataTemp = dataAll;
		remove.setInvertSelection(true);
		System.out.println(remove.getAttributeIndices());
		remove.setInputFormat(dataTemp);
		dataTemp = Filter.useFilter(dataTemp, remove);
		
		System.out.println(dataTemp.numAttributes());
		
		System.out.println(classification(dataTemp));
		
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

		return (float) eval.pctCorrect();

	}

	public static void main(String[] args) throws Exception {
		new SelectionFeatureWeka();
	}
	
}
