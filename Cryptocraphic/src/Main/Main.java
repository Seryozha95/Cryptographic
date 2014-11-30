package Main;

import Helper.Help;

public class Main {
	public static String readFilePath = "src/resurces/input.txt";
	public static String writeFilePath = "src/resurces/output.txt";
	public static String Key = "1011";

	public static void main(String[] args) {
		Help obj = new Help();
		obj.writeInToFile(writeFilePath,
				obj.stringToBinary(obj.readFromFileByList(readFilePath)));
		obj.hexToBinByList(obj.readFromFileByString(readFilePath));
		obj.logicSummaByList(obj.hexToBinByList(obj.readFromFileByString(readFilePath)),
				Key);

	}
}
