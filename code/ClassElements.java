package Code;

import java.util.ArrayList;
/**
 * クラスの構成要素を表すクラス
 * @author tomoe
 *
 */
public class ClassElements {
	ArrayList<String> classType; //classType&comment
	ArrayList<String[]> fileds; //[修飾子,isStatic,型,フィールド名,コメント]
	ArrayList<String[]> methods; //[修飾子,isStatic,型,引数含むメソッド名,コメント]

	public ClassElements() {
		classType = new ArrayList<>();
		fileds = new ArrayList<>();
		methods = new ArrayList<>();
	}

	public void addElemnts(String line) {
		String comment="";
		int counter = 0;
		while(line.length() != 0 && counter <= 0) {
			//コメント削除部
			int a = 0,b,c = 0;
			String tmp;
			while(a !=-1 || c !=-1) {
				a = line.indexOf("//");
				c = line.indexOf("/*");
				if(a!=-1) {
					if(a < (b=line.substring(a).indexOf("\n"))) {
						tmp = line.substring(a,b+1);
						comment +=  tmp;
						line=line.replace(tmp, "");
					}
					else {
						comment += line.substring(a);
						line=line.substring(0,a);
					}
				}
				if(c != -1) {
					tmp = line.substring(c,line.indexOf("*/")+2);
					comment+= tmp;
					line = line.replace(tmp, "");
				}
			}
			//ソースコード分解部
			line = line.replace("\n", "");

			counter++;
		}
		//確認用
		if(true)
			System.out.println("comment"+comment.replace("\n", "\\n"));
		if(true)
			System.out.println(line.replace("\n", "\\n"));
	}

	public void addClassType(String str) {
		classType.add(str);
	}
	public void addfiled(String[] strs) {
		fileds.add(strs);
	}
	public void addMethod(String[] strs) {
		methods.add(strs);
	}

	public ArrayList<String[]> getFileds(){
		return fileds;
	}

	public ArrayList<String> getClassType() {
		return classType;
	}

	public ArrayList<String[]> getMethods() {
		return methods;
	}


}
