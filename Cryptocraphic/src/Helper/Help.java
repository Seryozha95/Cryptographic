package Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class Help {
	public Help() {
	}

	public ArrayList<String> readFromFileByList(String filePath) {
		BufferedReader buffer = null;
		ArrayList<String> fileContent = new ArrayList<String>();
		try {
			String currentLine;
			File file = new File(filePath);
			buffer = new BufferedReader(new FileReader(filePath));
			for (int i = 0; i < file.length(); i++) {
				while ((currentLine = buffer.readLine()) != null) {
					fileContent.add(currentLine);
					System.out.println(currentLine);
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR:" + filePath
					+ "- No such file or directory");
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return fileContent;
	}

	public String readFromFileByString(String filePath) {
		BufferedReader buffer = null;
		String fileContent = null;
		try {
			String currentLine;
			buffer = new BufferedReader(new FileReader(filePath));
			boolean contentValue = true;
			while ((currentLine = buffer.readLine()) != null) {
				if (contentValue) {
					fileContent = currentLine;
					contentValue = false;
				} else {
					fileContent += currentLine;
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR:" + filePath
					+ "- No such file or directory");
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("fileContent  " + fileContent);
		return fileContent;
	}

	public void writeInToFile(String filePath, ArrayList<String> fileListContent) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("ERROR:" + filePath
						+ "File does not exit /n Existing the file");
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<String> iterator = fileListContent.iterator();
			boolean lineCount = false;
			while (iterator.hasNext()) {
				if (lineCount) {
					bw.newLine();
				}
				lineCount = true;
				bw.write((iterator.next()));
			}
			bw.close();
			System.out.println("Done - Writed List into " + '"' + filePath
					+ '"' + "file");
		} catch (IOException e) {
			System.out.println("ERROR: Can not write into file");
		}
	}

	public void writeInToFile(String filePath, String fileContent) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("ERROR:" + filePath + "File does not exit");
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileContent);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			System.out.println("ERROR: Can not write to file");
		}
	}

	public String stringToBinary(String content) {
		byte[] infoBin = null;
		String bunaryCode = null;
		try {
			infoBin = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR: Can not convert string");
			e.printStackTrace();
		}
		boolean contentValue = true;
		for (byte b : infoBin) {
			String a = Integer.toBinaryString(b);

			if (contentValue) {
				bunaryCode = a;
				contentValue = false;
			} else {
				bunaryCode += a;
			}
		}
		return bunaryCode;
	}

	public ArrayList<String> stringToBinary(ArrayList<String> content) {
		byte[] infoBin = null;
		ArrayList<String> binaryList = new ArrayList<String>();
		Iterator<String> iterator = content.iterator();
		while (iterator.hasNext()) {
			try {
				infoBin = iterator.next().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("ERROR: Culd not converting the list");
			}
			for (byte b : infoBin) {
				String a = Integer.toBinaryString(b);
				binaryList.add(a);
			}
		}
		return binaryList;
	}
}
