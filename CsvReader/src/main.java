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

class MyApplication extends Application{
    public MyApplication(){
    }
    public Document CreateDocument(){
        return doc;
    }
}

public class main {
	public static void main(String[] args) {
		
		MyApplication App1 = new MyApplication();
		App1.doc = new UTF8Document();
		App1.NewDocument("women.csv");
		App1.ReadDocument();
		App1.CloseDocument();
		
		MyApplication App2 = new MyApplication();
		App2.doc = new ANSIDocument();
		App2.NewDocument("f1365640740030.csv");
		App2.ReadDocument();
		App2.CloseDocument();
	}
}