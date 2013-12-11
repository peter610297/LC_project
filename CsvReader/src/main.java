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
    public abstract void Read() ;
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
    }	
    public void ReadDocument(){
    	doc.Read();
    }	
    public void CloseDocument(){
    	doc.Close();
    }
}

class UTF8Document extends Document{
	private CsvReader file;
    public UTF8Document(){
        System.out.println("---Open as UTF-8---");
    }
    public void Open(String name){
    	System.out.println("---open file: "+name);
        try{
            this.file = new CsvReader(new InputStreamReader(new FileInputStream(new File(name)),"UTF-8"));
            
        }catch (IOException e) {
			e.printStackTrace();
		}        
    }
    public void Read(){
    	CsvReader readfile = file; //get doc csvReader
    	int total=0;
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
    }
    public void Close(){
    	file.close();
        System.out.println("---close File---\n");
    }
}
class ANSIDocument extends Document{
	private CsvReader file;
    public ANSIDocument(){
        System.out.println("---Open as ANSI");
    }
    public void Open(String name){
        System.out.println("---open file: "+name);
        try{
        	this.file = new CsvReader(new FileReader(name));
            
        }catch (IOException e) {
			e.printStackTrace();
		}        
    }
    public void Read(){
    	CsvReader readfile = file; //get doc csvReader
    	int total=0;
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
    }
    public void Close(){
    	file.close();
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

public class main {
	public static void main(String[] args) {
		
		DocApplication file1 = new DocApplication();
		file1.doc = new UTF8Document();
		file1.NewDocument("women.csv");
		file1.ReadDocument();
		file1.CloseDocument();
		
		DocApplication file2 = new DocApplication();
		file2.doc = new ANSIDocument();
		file2.NewDocument("f1365640740030.csv");
		file2.ReadDocument();
		file2.CloseDocument();
	}
}