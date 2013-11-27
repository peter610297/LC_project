


import java.io.File; 
import java.io.InputStream;
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStreamReader;
import com.csvreader.CsvReader; 
import java.util.*;
import java.util.List;

abstract class Document {
    private String title;
    private CsvReader file;
    String getTitle() {
        return title;
    }
    CsvReader getreader(){
    	return file;
    }
    void openDoc(String title) {
        this.title = title;
        File csvfile=new File(title); 
        try{
        	InputStream ios=new FileInputStream(csvfile);
        	byte[] b=new byte[3];  
            ios.read(b);    
            if(b[0]==-17&&b[1]==-69&&b[2]==-65)   
            	this.file = new CsvReader(new InputStreamReader(new FileInputStream(csvfile),"UTF-8"));
            else
            	this.file = new CsvReader(new FileReader(title));
            ios.close();
        }catch (IOException e) {
			e.printStackTrace();
		}
    }
    abstract void open();
    abstract void read();
    abstract void close();
}

abstract class Editor {
    private List<Document> docs = new ArrayList<Document>();
    void open(String file) {
        Document doc = createDocument();
        doc.openDoc(file);
        doc.open();
        docs.add(doc);
    }
   
    void read(Document doc) {
        doc.read();
    }
    void read() {
        for(Document doc : docs) {
            read(doc);
        }
    }
    void close(Document doc) {
        doc.close();
    }
    
    void close() {
    	Document doc;
        for(int i = 0; i < docs.size(); i++) {
        	doc = docs.get(i);
            close(doc);
        }
    }
    abstract Document createDocument(); // Factory method
}
class TextEditor extends Editor {
    Document createDocument() {
        return new Document() {
            void open() {
                System.out.println("----開啟CSV檔 " + this.getTitle());
            }
            void read() {
            	System.out.println("----讀取CSV檔 " + this.getTitle());
            	CsvReader readfile = this.getreader();
            	try {     			
        			readfile.readHeaders();
        			
        			while (readfile.readRecord())
        			{
        				System.out.println("Current Record: " + readfile.getCurrentRecord());    
        			    System.out.print("Values: ");  
                        for (String s : readfile.getValues()) {  
                            System.out.print(" [" + s +"] ");  
                        }  
                        System.out.println();
        			}
        			
        		}catch (IOException e) {
        			e.printStackTrace();
        		}            	
                System.out.println("----"+this.getTitle()+"讀取完畢----");
            }
            void close() {
            	CsvReader readfile = this.getreader();
            	readfile.close();
                System.out.println("----關閉CSV檔 " + this.getTitle());
            }            
        };
    }
}
public class main {
	public static void main(String[] args) {
        Editor editor = new TextEditor();
        editor.open("women.csv");
        editor.open("f1365640740030.csv");
        editor.read();
        editor.close();
	}

}
