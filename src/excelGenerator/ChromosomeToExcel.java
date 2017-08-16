package excelGenerator;

import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import ag.ChromosomeBinary;

public class ChromosomeToExcel {

	public static void createLabelExcel(ExcelGenerator excelGenerator) {
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

	public synchronized static void updateExcelByGeneration(ExcelGenerator excelGenerator, List<ChromosomeBinary> chromosomes,
			int generation, long totalTime, int seed) throws Exception {

		Collections.sort(chromosomes, Collections.reverseOrder());

		excelGenerator.insertCellInfo(generation + 1, 0, "" + generation, Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(generation + 1, 1, "" + chromosomes.get(0).getFitnessValue(),
				Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 2, "" + chromosomes.get(0).getFAR(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 3, "" + chromosomes.get(0).getFRR(), Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 4, "" + chromosomes.get(0).toStringBinaryGenesNumbers(),
				Cell.CELL_TYPE_STRING);
		excelGenerator.insertCellInfo(generation + 1, 5, "" + chromosomes.get(0).getEnableFeatures(),
				Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 6, "" + chromosomes.get(0).getDisabledFeatures(),
				Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 7, "" + chromosomes.get(0).getFeatureReduction(),
				Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 8, "" + seed, Cell.CELL_TYPE_NUMERIC);
		excelGenerator.insertCellInfo(generation + 1, 9, "" + totalTime, Cell.CELL_TYPE_NUMERIC);

	}

}