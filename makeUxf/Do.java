package makeUxf;

import java.io.File;

public class Do{
	public static void main(String[] args) {
		CodesReader codesReader = new CodesReader();
		codesReader.codesRead(args);
		MakeUxf mx = new MakeUxf(codesReader);
		int i = 1;

		for(i = 1; new File("uxf/class"+i+".uxf").exists();i++);
		System.out.println("Write: class"+i+".uxf");
		codesReader.arrowsshow();
		//codesReader.allCodesShow();
		System.out.println();
		mx.uxf("uxf/class"+i);
	}
}
