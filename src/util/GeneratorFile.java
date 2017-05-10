package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneratorFile {

	private File file;
	private FileWriter fileWritter;

	public GeneratorFile(String order) throws IOException {

		//DIRECTORY TO SAVE LOG POPULATION .TXT
		this.file = new File("GA_SELECTION_BASE" + order + ".txt");
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
