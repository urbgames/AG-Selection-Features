package excelGenerator;

import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import ag.ChromosomeBinary;
import util.MathUtil;

public class ChromosomeToExcel {

	private ExcelGenerator excelGenerator;

	public ChromosomeToExcel(String order) {
		excelGenerator = new ExcelGenerator(order);
	}

	public void insertLabelRows() {
		excelGenerator.insertCellInfo(0, 0, "Generation", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 1, "Max", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 2, "Min", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 3, "Mean", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 4, "Std", Cell.CELL_TYPE_STRING);

		excelGenerator.insertCellInfo(0, 6, "Max-PCT", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 7, "Min-PCT", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 8, "Mean-PCT", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 9, "Std-PCT", Cell.CELL_TYPE_STRING);

		excelGenerator.insertCellInfo(0, 11, "Max-FAR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 12, "Min-FAR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 13, "Mean-FAR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 14, "Std-FAR", Cell.CELL_TYPE_STRING);

		excelGenerator.insertCellInfo(0, 16, "Max-FRR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 17, "Min-FRR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 18, "Mean-FRR", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 19, "Std-FRR", Cell.CELL_TYPE_STRING);
	}

	public void insertLabelRows2() {
		excelGenerator.insertCellInfo(0, 0, "Interaction", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 1, "gBestFitness(%)", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 2, "FAR(%)", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 3, "FRR(%)", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 4, "Feature", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 5, "Enabled Features", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 6, "Disabled Features", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 7, "Feature Reduction(%)", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 8, "Seed", Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(0, 9, "Time(ms)", Cell.CELL_TYPE_STRING);
	}

	public void converterChromosomeToExcelRow2(List<ChromosomeBinary> chromosomes, int generation, long time, int seed) throws Exception {

		Collections.sort(chromosomes, Collections.reverseOrder());

		excelGenerator.insertCellInfo(generation + 1, 0, "" + generation, Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(generation + 1, 1, "" + chromosomes.get(0).getFitnessValue(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 2, "" + chromosomes.get(0).getFAR(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 3, "" + chromosomes.get(0).getFRR(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 4, "" + chromosomes.get(0).toStringBinaryGenesNumbers(), Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(generation + 1, 5, "" + chromosomes.get(0).getEnableFeatures(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 6, "" + chromosomes.get(0).getDisabledFeatures(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 7, "" + chromosomes.get(0).getFeatureReduction(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 8, "" + seed, Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 9, "" + time, Cell.CELL_TYPE_NUMERIC);

	}

	public void converterChromosomeToExcelRow(List<ChromosomeBinary> chromosomes, int generation) {

		try {
			double[] genes = new double[chromosomes.size()];
			double[] PCTCorrent = new double[chromosomes.size()];
			double[] FAR = new double[chromosomes.size()];
			double[] FRR = new double[chromosomes.size()];
			for (int i = 0; i < chromosomes.size(); i++) {
				genes[i] = chromosomes.get(i).getFitnessValue();
				PCTCorrent[i] = chromosomes.get(i).getPctCorrectRate();
				FAR[i] = chromosomes.get(i).getFAR();
				FRR[i] = chromosomes.get(i).getFRR();
			}

			excelGenerator.insertCellInfo(generation + 1, 0, "" + generation, Cell.CELL_TYPE_STRING);
			excelGenerator.insertCellInfo(generation + 1, 1, "" + MathUtil.calcMax(genes), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 2, "" + MathUtil.calcMin(genes), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 3, "" + MathUtil.calcMean(genes), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 4, "" + MathUtil.calcStd(genes), Cell.CELL_TYPE_NUMERIC);

			excelGenerator.insertCellInfo(generation + 1, 6, "" + MathUtil.calcMax(PCTCorrent), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 7, "" + MathUtil.calcMin(PCTCorrent), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 8, "" + MathUtil.calcMean(PCTCorrent),
					Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 9, "" + MathUtil.calcStd(PCTCorrent), Cell.CELL_TYPE_NUMERIC);

			excelGenerator.insertCellInfo(generation + 1, 11, "" + MathUtil.calcMax(FAR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 12, "" + MathUtil.calcMin(FAR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 13, "" + MathUtil.calcMean(FAR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 14, "" + MathUtil.calcStd(FAR), Cell.CELL_TYPE_NUMERIC);

			excelGenerator.insertCellInfo(generation + 1, 16, "" + MathUtil.calcMax(FRR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 17, "" + MathUtil.calcMin(FRR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 18, "" + MathUtil.calcMean(FRR), Cell.CELL_TYPE_NUMERIC);
			excelGenerator.insertCellInfo(generation + 1, 19, "" + MathUtil.calcStd(FRR), Cell.CELL_TYPE_NUMERIC);
		} catch (Exception e) {
			System.out.println("erro");
		}
	}

	public void converterChromosomeToExcelCollumn(List<ChromosomeBinary> chromosomes, int generation) {

		excelGenerator.insertMergeCells(0, 0, generation * 2, generation * 2 + 1, "Generation - " + generation);

		for (int i = 0; i < chromosomes.size(); i++) {
			excelGenerator.insertCellInfo(i + 1, (generation * 2), "Individual - " + chromosomes.get(i).getID(),
					Cell.CELL_TYPE_STRING);
			excelGenerator.insertCellInfo(i + 1, (generation * 2) + 1, "" + chromosomes.get(i).getFitnessValue(),
					Cell.CELL_TYPE_NUMERIC);

		}

	}

	public void closeFile() {
		excelGenerator.saveFile();
	}

}