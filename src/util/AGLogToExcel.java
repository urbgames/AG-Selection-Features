package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;

import excelGenerator.ExcelGenerator;

public class AGLogToExcel {

	private BufferedReader bufferedReader;
	private ExcelGenerator excelGenerator;
	int row=0, column=0;

	public AGLogToExcel() throws IOException {

		excelGenerator = new ExcelGenerator("1");
		
		bufferedReader = new BufferedReader(
				new FileReader("D:\\_Resultado_AG_Selection_Feature\\_Experimentos 03\\GA_SELECTION1.txt"));

		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			if (line.startsWith("---")){
				System.out.println(line);
				row++;
				column=0;
			}
				
			else if (line.startsWith("Fitness")){
//				System.out.println(line.split(":")[1]);
				excelGenerator.insertCellInfo(row, column, line.split(":")[1], Cell.CELL_TYPE_NUMERIC);
				column++;
			}
				
		}
		
		excelGenerator.saveFile();

	}

	public static void main(String[] args) throws IOException {
		new AGLogToExcel();
	}

}
