package systemStatusControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class StatusControl {

	private static ObjectMapper mapper = new ObjectMapper();
	private static String filepath = "status.json";

	public static void clearFiles() {
		File[] files = new File(".").listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.equals(filepath)) {
					file.delete();
				}
			}
		}
	}

	public static synchronized List<StatusGA> readStatus() {

		List<StatusGA> readStatus = null;
		if (!new File(filepath).exists())
			return null;
		else
			try {
				readStatus = mapper.readValue(new File(filepath), new TypeReference<List<StatusGA>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		return readStatus;
	}

	public static synchronized void writeStatus(List<StatusGA> statusPSO) {
		try {
			mapper.writeValue(new File(filepath), statusPSO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insertOrUpdate(StatusGA statusPSO) {
		List<StatusGA> statusPSOSaved = readStatus();
		boolean isInsert = true;
		if (statusPSOSaved != null) {
			for (int i = 0; i < statusPSOSaved.size(); i++) {
				if (statusPSOSaved.get(i).getRepetition() == statusPSO.getRepetition()
						&& statusPSOSaved.get(i).getClassifier().equals(statusPSO.getClassifier())) {
					statusPSOSaved.set(i, statusPSO);
					isInsert = false;
				}
			}
			if (isInsert) {
				statusPSOSaved.add(statusPSO);
			}
		} else {
			statusPSOSaved = new ArrayList<>();
			statusPSOSaved.add(statusPSO);
		}
		writeStatus(statusPSOSaved);
	}

}
