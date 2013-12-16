/***********************************************************************
 * 
 * Document   : CsvReader.java                                         
 * Create on  : 2013 / 11 / 20                                         
 * Author     : peter610297 , MxmoParis , Neal-liu , chitsutote        
 * Description: 讀取資料夾內的csv檔，並將csv檔裡的資料全部列出，使用Design Pattern　　　　  
 *              Factory Method模式的做法，由子類別完成建立具體的 Document           
 *                                                                     
 ************************************************************************/

import java.io.File; 
import java.io.InputStream;
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.util.*;
import com.csvreader.CsvReader; 

abstract class Document{
    public abstract void Open(String name) ;
    public abstract int Read() ;
    public abstract int GetSize();
    public abstract void Close() ;
}

abstract class Application{
	
    protected Document doc;
    protected String FileName;
    
    public Application(){
    }
    public abstract Document CreateDocument();
    
    public void NewDocument(String N){
        FileName = N;
        doc.Open(FileName);
    	doc.Read();
//    	doc.GetSize();
    	doc.Close();
    }
}

class UTF8Document extends Document{
	private CsvReader file;
	private int total = 0;
    public UTF8Document(){
        System.out.println("---Open as UTF-8---");
    }
    public void Open(String name){
    	System.out.println("---open file: "+name);
        try{
            this.file = new CsvReader(new InputStreamReader(new FileInputStream(new File(name)),"UTF-8"));
        }	catch (IOException e) {
				e.printStackTrace();
			}        
    }
    public int Read(){
    	CsvReader readfile = file; //get doc csvReader
    //	int total=0;
    	try {     			            		
    		//read the first record of data as column headers
			readfile.readHeaders();
			
			while (readfile.readRecord())
			{   				
				total++;
			}
			System.out.println("---Total Data: "+total);
	//		this.GetSize(total);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}            	
    	return total;
    }
    public int GetSize(){
    	return total;
    }
    
    public void Close(){
 //   	file.close();
        System.out.println("---close File---\n");
    }
}
class ANSIDocument extends Document{
	private CsvReader file;
	private int total = 0;
    public ANSIDocument(){
        System.out.println("---Open as ANSI");
    }
    public void Open(String name){
        System.out.println("---open file: "+name);
        try{
        	this.file = new CsvReader(new FileReader(name));
   //         this.file = new CsvReader(new InputStreamReader(new FileInputStream(new File(name)),"MS950"));            
        }catch (IOException e) {
			e.printStackTrace();
		}        
    }
    public int Read(){
    	CsvReader readfile = file; //get doc csvReader
 //   	int total=0;
    	try {     			            		
    		//read the first record of data as column headers
			readfile.readHeaders();
			
			while (readfile.readRecord())
			{   				
				total++;
			}
			System.out.println("---Total Data: "+total);
			
		}catch (IOException e) {
			e.printStackTrace();
		}          
    	return total;
    }
    public int GetSize(){
    	return total;
    }
    public void Close(){
 //   	file.close();
        System.out.println("---close File---\n");
    }
}

class DocApplication extends Application{
    public DocApplication(){
    }
    public Document CreateDocument(){
        return doc;
    }
}

public class LC {
	
	public static void main(String[] args) {
		
		CatchData data = new CatchData();
		data.loadURLfile(data.data1, "C:/Users/hung/workspace/TestCsv", "AccidentsData");
		data.loadURLfile(data.data2, "C:/Users/hung/workspace/TestCsv", "WomenData");
//		Sql T_Sql = new Sql();
//		T_Sql.TestSql();
		
		DocApplication file1 = new DocApplication();
		file1.doc = new UTF8Document();
		file1.NewDocument("WomenData.csv");
		
		DocApplication file2 = new DocApplication();
		file2.doc = new ANSIDocument();
		file2.NewDocument("AccidentsData.csv");
	}
	
	// unit test
	public int TestSize(){
		DocApplication test = new DocApplication();
		test.doc = new UTF8Document();
		test.NewDocument("WomenData.csv");

        return test.doc.Read();
	}
	//unit test
	public boolean isNumber(String data) {
        try {
            Integer.valueOf(data);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
}