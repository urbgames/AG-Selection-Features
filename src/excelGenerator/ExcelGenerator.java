package excelGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

public class ExcelGenerator {

	private String fileName = "D:/exportInfoGA";
	private HSSFWorkbook workbook;
	private HSSFSheet sheetInfoGA;

	public ExcelGenerator(String order) {
		this.fileName += order+".xls";
		workbook = new HSSFWorkbook();
		sheetInfoGA = workbook.createSheet("InfoGA");
	}

	public void insertCellInfo(int row, int cell, String info, int cellTypeNumeric) {
		Row row2;
		if (sheetInfoGA.getRow(row) == null) {
			row2 = sheetInfoGA.createRow(row);
		}
		row2 = sheetInfoGA.getRow(row);
		sheetInfoGA.autoSizeColumn(cell);
		Cell cell2 = row2.createCell(cell);
		if (cellTypeNumeric == Cell.CELL_TYPE_NUMERIC)
			cell2.setCellValue(Double.parseDouble(info));
		else
			cell2.setCellValue(info);
	}

	public void insertMergeCells(int firstRow, int lastRow, int firstCol, int lastCol, String info) {
		sheetInfoGA.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
		Row row2;
		if (sheetInfoGA.getRow(firstRow) == null) {
			row2 = sheetInfoGA.createRow(firstRow);
		}
		row2 = sheetInfoGA.getRow(firstRow);
		Cell cell = row2.createCell(firstCol);
		cell.setCellValue(info);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
	}

	public void saveFile() {
		try {
			System.out.println(fileName);
			FileOutputStream outputStream = new FileOutputStream(new File(fileName));
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
