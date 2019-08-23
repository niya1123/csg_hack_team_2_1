package test;

import Code.CodesReader;

public class Test {
	public static void main(String[] args) {
		/*
		ArrayList<String> codes = new ArrayList<>();
		String path = "/Users/tomoe/Documents/workspace/Easyer/src/Code/SReader.java";
		codes.add(SReader.readCodeData(path));
		for(String code: codes) {
			System.out.println(code);
		}
		*/
		CodesReader a = new CodesReader();
		//読み込むソースコードのPath
		String[] s = {"/Users/tomoe/Documents/workspace/PM1/src/baseball/player/Batter.java"};
		a.codesRead(s);
	}
}
