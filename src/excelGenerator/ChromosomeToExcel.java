package excelGenerator;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import ag.ChromosomeBinary;

public class ChromosomeToExcel {

	private ExcelGenerator excelGenerator;

	public ChromosomeToExcel(String order) {
		excelGenerator = new ExcelGenerator(order);
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

	public void converterChromosomeToExcelRow(List<ChromosomeBinary> chromosomes, int generation) {

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
