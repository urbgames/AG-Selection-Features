package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneratorFile {

	private File file;
	private FileWriter fileWritter;

	public GeneratorFile() throws IOException {

		this.file = new File("D:/GA_SELECTION.txt");
		new PrintWriter(this.file).close();
		fileWritter = new FileWriter(this.file, true);

	}

	public void insertLog(String log) throws IOException {
		this.fileWritter.write(log);
		this.fileWritter.write(System.getProperty("line.separator"));

	}

	public void closeLog() throws IOException {
		this.fileWritter.close();

	}

}
