/***********************************************************************
 * Document   : CsvReader.java                                         *
 * Create on  : 2013 / 11 / 20                                         *
 * Author     : peter610297 , MxmoParis , Neal-liu , chitsutote        *
 * Description: 讀取資料夾內的csv檔，並將csv檔裡的資料全部列出，使用Design Pattern　　　　 * 
 *              Factory Method模式的做法，由子類別完成建立具體的 Document           *
 *                                                                     *
 ************************************************************************/

import java.io.File; 
import java.io.InputStream;
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.util.*;
import com.csvreader.CsvReader; 

abstract class Document {
	
    private String title;   //file name
    private CsvReader file; //csv reader
    
    //get title
    String getTitle() {
        return title;
    }
    
    //get file
    CsvReader getreader(){
    	return file;
    }
    
    //open the file and set title
    void openDoc(String title) {
    	
        this.title = title;  //set title
        
        try{
        	
        	InputStream ios=new FileInputStream(new File(title));
        	byte[] b=new byte[3];  
            ios.read(b);     //read first three bytes of file
            
            //check first three values of word, if values are -17,-69,-65 then open as UTF-8 format
            if(b[0]==-17&&b[1]==-69&&b[2]==-65){   
            	this.file = new CsvReader(new InputStreamReader(new FileInputStream(new File(title)),"UTF-8"));
            }
            //other formats
            else{
            	this.file = new CsvReader(new FileReader(title));
            }
            
            //close inputStream
            ios.close();
            
        }catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    abstract void open();
    abstract void read();
    abstract void close();
}

abstract class Reader {
    private List<Document> docs = new ArrayList<Document>();
    
    //add new file to the list and set fils's title
    void open(String file) {
        Document doc = createDocument();
        doc.openDoc(file);
        doc.open();
        docs.add(doc);
    }
    
    //call Document read() function
    void read(Document doc) {
        doc.read();
    }
    
    //read all Documents in the list
    void read() {
        for(Document doc : docs) {
            read(doc);
        }
    }
    
   //call Document close() function
    void close(Document doc) {
        doc.close();
    }
    
    //close all Documents in the list
    void close() {
        for(Document doc : docs) {
            close(doc);
        }
    }
    
    // Factory method
    abstract Document createDocument(); 
    
}
class Csvreader extends Reader {
    Document createDocument() {
        return new Document() {
        	
            void open() {
                System.out.println("----open CSV file: " + this.getTitle());
            }
            
            //print all data in the csv file
            void read() {
            	System.out.println("----reading CSV file: " + this.getTitle());
            	
            	//get doc csvReader
            	CsvReader readfile = this.getreader();
            	
            	try {     			
            		
            		//read the first record of data as column headers
        			readfile.readHeaders();
        			
        			while (readfile.readRecord())
        			{
        				
        				//gets the index of the current record
        				System.out.println("Current Record: " + readfile.getCurrentRecord());    
        			    System.out.print("Values: ");  
        			    
        			    //print each column of the row
                        for (String s : readfile.getValues()) {  
                            System.out.print(" [" + s +"] ");  
                        }  
                        
                        System.out.println();
                        System.out.println("----"+this.getTitle()+" finished----");
        			}
        			
        		}catch (IOException e) {
        			e.printStackTrace();
        		}            	
            }
            
            void close() {
            	CsvReader readfile = this.getreader();
            	
            	//close files
            	readfile.close();
                System.out.println("----close CSV file: " + this.getTitle());
            }
            
        };
    }
}

public class main {
	public static void main(String[] args) {
		
        Reader CurRead = new Csvreader();
        
        //open file 
        CurRead.open("women.csv");
        CurRead.open("f1365640740030.csv");
        
        CurRead.read();  //read all file
        CurRead.close(); //close all file
        
	}
}