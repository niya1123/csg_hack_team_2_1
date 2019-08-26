/**
 * FileScanner
 */
public class FileScanner {

   /*入力ファイルのスキャナ*/
	private Scanner sourceFile;
	
	/*行バッファ*/
    private String line;

    private String fileName;
    
    /*行カウンタ*/
    private int lineNumber;
    
    /*列カウンタ*/
    private int columnNumber;
    
    /*読み取り文字*/
    private char currentCharacter;
    
    /*先読み文字*/
    private char nextCharacter;
	
    /**
     * コンストラクタ。ファイルを開くのでしっかりとtry-catchを使って読み込む。
     * ファイルを読み込めなかった場合は、システムを終了するようにする。
     * フィールドを初期化する。
     * @param sourceFileName ファイル名
     */
    public FileScanner (String sourceFileName) {
    	try {
    		sourceFile = new Scanner(new File(sourceFileName));
		} catch (Exception exception) {
			System.out.println(exception);
			System.exit(1);
		}
        
        fileName = sourceFileName.split(".java")[0];
    	lineNumber=0;
    	columnNumber=-1;
    	nextCharacter='\n';
    	nextChar();
    }

    /** 
     * ファイルを閉じるメソッド。
     */
    void closeFile() {
    	try {
    		sourceFile.close();
		} catch (Exception exception) {
			System.out.println(exception);
			System.exit(0);
		}
    
    }

    /**
     * ファイルの次の行を読み込むメソッド。読み込む系は必ずtry-catchする。
     */
    void readNextLine() {
       try {
    	   if(sourceFile.hasNextLine())
               line = sourceFile.nextLine() + "\n";
           else
               line = null;
       } catch (Exception exception) {
    	   System.out.println(exception);
           closeFile();
           System.exit(1);
       }
    }

    /**
     * nextCharacter中の文字を返すメソッド。
     */
    char lookAhead() {
        return nextCharacter;
    }

    /**
     * lineフィールドの文字列を返す。
     */
    String getLine() {
       return line;
    }

    /**
     * 一文字読み進めるメソッド。読み込む際
     * 1.ファイル末の時
     * 2.行末の時
     * 3.行の途中の時
     * 上記3つの場合によって処理を変える。
     */
    char nextChar() {
    	//共通動作
    	currentCharacter = nextCharacter;
        if(nextCharacter != 0)
        	//2.行末の時
            if(nextCharacter == '\n'){
                readNextLine();
                //2.行末かつnullじゃない時
                if(line != null){
                    nextCharacter = line.charAt(0);
                    lineNumber++;
                    columnNumber = 0;
                 //2.行末かつnullの時
                }else{
                    nextCharacter = '\0';
                 }
            }
            else{
            	//3.行の途中の時
            	//前置インクリメントをすること
            	nextCharacter = line.charAt(++columnNumber);
            }
        
        return currentCharacter;
     }
    
}