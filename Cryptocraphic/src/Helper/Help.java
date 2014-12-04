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

	/**
	 * The function read file by line and return readed line's list
	 * 
	 * @param filePath
	 *            - It's the file path which you want to read
	 * @return - readed line's list
	 */
	public ArrayList<String> readFromFileByList(String filePath) {
		ArrayList<String> fileContent = new ArrayList<String>();
		fileContent.add(readFromFileByString(filePath));
		return fileContent;
	}

	/**
	 * The function read file by line and return readed general string
	 * 
	 * @param filePath
	 *            - It's the file path which you want to read
	 * @return - readed general string
	 */
	public String readFromFileByString(String filePath) {
		BufferedReader buffer = null;
		String fileContent = "";
		try {
			String currentLine;
			buffer = new BufferedReader(new FileReader(filePath));
			while ((currentLine = buffer.readLine()) != null) {
				fileContent += currentLine;
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

	/**
	 * This function is writing list in to file. TODO optimize this function
	 * (optimize new line mode)
	 * 
	 * @param filePath
	 *            - it's the file path which you want into write
	 * @param fileListContent
	 *            - it's the file list content which you want to write into the
	 *            file
	 */
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

	/**
	 * This function is writing in to file.
	 * 
	 * @param filePath
	 *            - it's the file path which you want into write
	 * @param fileContent
	 *            - it's the file content which you want to write into the file
	 */
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

	/**
	 * TODO
	 * 
	 * @param content
	 * @return
	 */
	public String stringToBinary(String content) {
		byte[] infoBin = null;
		String bunaryCode = "";
		try {
			infoBin = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR: Can not convert string");
		}
		for (byte b : infoBin) {
			String a = Integer.toBinaryString(b);
			bunaryCode += a;
		}
		return bunaryCode;
	}

	/**
	 * TODO
	 * 
	 * @param content
	 * @return
	 */
	public ArrayList<String> stringToBinary(ArrayList<String> content) {
		ArrayList<String> binaryList = new ArrayList<String>();
		Iterator<String> iterator = content.iterator();
		while (iterator.hasNext()) {
			ArrayList<String> subBinary = subText(iterator.next());
			Iterator<String> binaryiterator = subBinary.iterator();
			while (binaryiterator.hasNext()) {
				binaryList.add(stringToBinary(binaryiterator.next()));
			}
		}
		return binaryList;
	}

	/**
	 * TODO
	 * 
	 * @param hex
	 * @return
	 */
	public ArrayList<String> hexToBinByList(String hex) {
		ArrayList<String> hexList = subText(hex);
		ArrayList<String> binaryList = new ArrayList<String>();
		Iterator<String> hexListIterator = hexList.iterator();
		while (hexListIterator.hasNext()) {
			binaryList.add(hexToBinByString(hexListIterator.next()));
		}
		return binaryList;
	}

	/**
	 * TODO
	 * 
	 * @param hex
	 * @return
	 */
	public String hexToBinByString(String hex) {
		String bin = "";
		String binFragment = "";
		int iHex;
		hex = hex.trim();
		hex = hex.replaceFirst("0x", "");
		for (int i = 0; i < hex.length(); i++) {
			iHex = Integer.parseInt("" + hex.charAt(i), 16);
			binFragment = Integer.toBinaryString(iHex);
			while (binFragment.length() < 4) {
				binFragment = "0" + binFragment;
			}
			bin += binFragment;
		}
		return bin;
	}

	public ArrayList<String> subText(String text) {
		ArrayList<String> subTextList = new ArrayList<String>();
		for (int i = 0; i < text.length(); i++) {
			subTextList.add(text.substring(i, i + 1));
		}
		return subTextList;
	}

	public String logicSumma(int booleanValue1, int booleanValue2) {
		return Integer.toBinaryString(booleanValue1 ^ booleanValue2);
	}

	public ArrayList<String> logicSummaByList(ArrayList<String> bynaryCodeList,
			String key) {
		ArrayList<String> logicNegativeList = new ArrayList<String>();
		ArrayList<String> keySubList = subText(key);
		Iterator<String> iterator = bynaryCodeList.iterator();
		while (iterator.hasNext()) {
			String buffer = "";
			ArrayList<String> textSubList = subText(iterator.next());
			Iterator<String> textIterator = textSubList.iterator();
			Iterator<String> keyIterator = keySubList.iterator();
			while (textIterator.hasNext()) {
				int subText = Integer.parseInt(textIterator.next());
				int subKey = Integer.parseInt(keyIterator.next());
				buffer += logicSumma(subText, subKey);
			}
			logicNegativeList.add(buffer);
		}
		System.out.println("aaaaa" + logicNegativeList);
		return logicNegativeList;
	}

	public void tmp(String binaryCode) {
		String bbbb = binaryCode.substring(1, 4);
		bbbb += binaryCode.substring(0, 1);
		// TODO
		// System.out.println("shrjvacccc   " + bbbb);
		// int bbb = 0010;
		// int a = 0b0 + bbb;
		// a=a>>1;
		// System.out.println("verjnakjab = " + (a));

	}
}