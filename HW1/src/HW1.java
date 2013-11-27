import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.awt.List;
import java.io.InputStreamReader;
import com.csvreader.CsvReader; 
import java.util.*;

abstract class Document {
    private String title;
    String getTitle() {
        return title;
    }
    void setTitle(String title) {
        this.title = title;
    }
    abstract void open();
    abstract void save();
    abstract void close();
}

abstract class Editor {
    private List<Document> docs = new ArrayList<Document>();
    
    void open(String file) {
        Document doc = createDocument();
        doc.setTitle(file);
        doc.open();
        docs.add(doc);
    }
   
    void save(Document doc) {
        doc.save();
    }
    
    void close(Document doc) {
        doc.close();
        docs.remove(doc);
    }
    
    void close() {
        for(Document doc : docs) {
            close(doc);
        }
    }
    abstract Document createDocument(); // Factory method
}
class TextEditor extends Editor {
    Document createDocument() {
        return new Document() {
            void open() {
                System.out.println("開啟文字檔案 " + this.getTitle());
            }
            void save() {
                System.out.println("儲存文字檔案 " + this.getTitle());
            }
            void close() {
                System.out.println("關閉文字檔案 " + this.getTitle());
            }            
        };
    }
}
public class HW1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			CsvReader products = new CsvReader(new InputStreamReader(new FileInputStream(new File("women.csv")),"UTF-8"));  
		
			//products.readHeaders();

			while (products.readRecord())
			{
				System.out.println("current record: " + products.getCurrentRecord());  
			    System.out.println("RawRecord:" + products.getRawRecord());  
			    System.out.println("getValues() ");  
                for (String s : products.getValues()) {  
                    System.out.print("--" + s);  
                }  
                System.out.println();
			}
	
			products.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
