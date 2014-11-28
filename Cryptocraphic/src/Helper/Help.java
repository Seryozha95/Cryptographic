package Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public class Help {
	public Help() {
	}

	public void readFromFile(String filePath) {
		BufferedReader buffer = null;
		try {
			String currentLine;
			buffer = new BufferedReader(new FileReader(filePath));
			while ((currentLine = buffer.readLine()) != null) {
				System.out.println(currentLine);
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
	}

	public void writeInToFile(String filePath, String fileCcontent) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("ERROR:" + filePath + "File does not exit");
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileCcontent);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			System.out.println("ERROR: Can not write to file");
		}
	}

	public void stringToBinary(String content) {
		byte[] infoBin = null;
		try {
			infoBin = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR: Can not convert string");
			e.printStackTrace();
		}
		for (byte b : infoBin) {
			String a = Integer.toBinaryString(b);
			System.out.println("char:" + (char) b + "-> " + a);
		}
	}
}
