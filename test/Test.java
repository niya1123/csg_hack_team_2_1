package test;

import java.io.File;

import Code.CodesReader;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		CodesReader CodesReader = new CodesReader();
		File file = new File(args[0]);
		String filePaths = file.getAbsolutePath();
		CodesReader.codesRead(filePaths);
	}
}
