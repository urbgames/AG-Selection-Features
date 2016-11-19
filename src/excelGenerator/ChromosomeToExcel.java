package excelGenerator;

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
	}

	public void converterChromosomeToExcelRow(List<ChromosomeBinary> chromosomes, int generation) {

		double[] genes = new double[chromosomes.size()];
		for (int i = 0; i < chromosomes.size(); i++) {
			genes[i] = chromosomes.get(i).getFitnessValue();
		}
		excelGenerator.insertCellInfo(generation + 1, 0, "" + generation, Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(generation + 1, 1, "" + MathUtil.calcMax(genes), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 2, "" + MathUtil.calcMin(genes), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 3, "" + MathUtil.calcMean(genes), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 4, "" + MathUtil.calcStd(genes), Cell.CELL_TYPE_NUMERIC);
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
