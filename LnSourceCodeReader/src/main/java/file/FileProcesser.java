package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import db.DBOperation;
import main.Main;

public class FileProcesser {
	private DBOperation dbOper;

	public FileProcesser() {
		dbOper = new DBOperation();
	}

	public void process() {

		try {
			FileReader fr = new FileReader(Main.path);
			BufferedReader br = new BufferedReader(fr);
			String newLineStr;
			boolean isImpl = false;
			String api = null;
			StringBuilder impSB = new StringBuilder();
			while ((newLineStr = br.readLine()) != null) {
				if (newLineStr.startsWith("function ")) {
					isImpl = true;
					int lastSpaceIndex = newLineStr.lastIndexOf(" ");
					api = newLineStr.substring(lastSpaceIndex + 1, newLineStr.length() - 2);
					// api
					// System.out.println("function " + api);
				} else {
					if (isImpl) {
						// impl
						impSB.append(newLineStr + System.lineSeparator());
						if (newLineStr.startsWith("}")) {
							isImpl = false;

							// Save to DB
							dbOper.add(api, impSB.toString());
							impSB.setLength(0);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void list() {
		dbOper.list();
	}

	public void clear() {
		dbOper.clear();
	}

	public String get(String key) {
		return dbOper.get(key);
	}

	public void close() {
		dbOper.close();
	}
}
