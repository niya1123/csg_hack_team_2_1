package Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CodesReader {
	public ArrayList<String[]> arrows;//クラス関係
	public HashMap<String, ClassElements> codes;//<クラス名,クラスの要素を持つClassElementsクラス>

	public CodesReader() {
		arrows = new ArrayList<>();
		codes = new HashMap<>();
	}

	public void codesRead(String filePaths){
		String[] filePath = filePaths.split("/");
		String fileName = filePath[filePath.length - 1];
		if(fileName.indexOf(".java") == -1){
			System.out.println("javaクラスでないファイルが読み込まれました");
		}
		else{
			String className = fileName.split(".java")[0];
			System.out.println("ClassName is " + className);
			codes.put(className, new ClassElements());
			makeCodes(filePaths, className);
		}

	}

	public void makeCodes(String filePaths, String className) {
		ClassElements ce = codes.get(className);
		Path path = Paths.get(filePaths);
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line;
			int count = -1;
			while((line = reader.readLine()) != null) {
				String subLine="";
				line = line.replace("\t", "");
				if(line.equals(""))
					continue;
				while(!endKey(line)) {
					line+="\n"+reader.readLine().replace("\t", "");
				}
				if(line.replace(" ","").replace("\n", "").equals("}"))
					break;
				count += search(line, '{');
				if(count < 0)
					continue;
				while(count != 0) {
					subLine += reader.readLine();
					count+=search(subLine,'{');
					count-=search(subLine,'}');
				}
				ce.addElemnts(line);
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 文字列から文字の個数を探索するメソッド
	 * @param str
	 * @param target
	 * @return 文字の個数
	 */
	public int search(String str, char target){
		int count = 0;

		for(char x: str.toCharArray()){
			if(x == target){
				count++;
			}
		}
		return count;
	}

	/**
	 * 文字列にJavaソースコードにおける行の終了コードが含まれているかを返すメソッド
	 * @param line
	 * @return
	 */
	public boolean endKey(String line) {
		boolean b1 = (line.contains(";"));
		boolean b2 = (line.contains("{"));
		boolean b3 = (line.contains("}"));
		return  b1||b2||b3;
	}

	/**
	 * uml図生成じのクラス関係の矢印を追加するメソッド
	 * @param begin
	 * @param end
	 * @param kind
	 */
	private void addArrow(String begin, String end, String kind) {
		String[] a = {begin,end,kind};
		arrows.add(a);
	}
}
