package Main;

import java.io.UnsupportedEncodingException;

import Helper.Help;

public class Main {
	public static String readFilePath = "src/resurces/input.txt";
	public static String writeFilePath = "src/resurces/output.txt";
	public static String fileCcontent = "This is the content to write into file";
	public static String btnaryString = "This is binary string";

	public static void main(String[] args) {
		Help obj = new Help();
		obj.writeInToFile(writeFilePath,
				obj.stringToBinary(obj.readFromFileByList(readFilePath)));
	}
}
