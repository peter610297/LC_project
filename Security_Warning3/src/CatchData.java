
import java.io.BufferedInputStream;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class CatchData {
	public final String data1 = "http://data.gov.tw/opendata/addCount?sno=301000000A-00006&" +
			"dataformat=csv&url=http%3a%2f%2fwww.npa.gov.tw%2fNPAGip%2fwSite%2fpublic%2fAttachment%2ff1365640740030.csv";
	public final String data2 = "http://data.gov.tw/opendata/addCount?sno=301000000A-00010&" +
			"dataformat=csv&url=http%3a%2f%2fwww.moi.gov.tw%2fopendata%2fwomen.csv";
	public CatchData(){
	}
	public void loadURLfile(String urlPath,String savePath,String fileName){
		
		try{
			String msg="";
			int len;
			byte[] b = new byte[1024]; //一次取得1024個bytes
			URL zeroFile = new URL(urlPath);
			
			String name = zeroFile.getFile();
			System.out.println("檔案 : "+name);
			// 取得副檔名
			String tmpName = name.substring(name.lastIndexOf("."), name.length());
			msg = fileName+tmpName; //新取的檔名+副檔名
			BufferedInputStream bs = new BufferedInputStream(zeroFile.openStream());
			FileOutputStream fs = new FileOutputStream(savePath+"/"+msg);
			
			while((len=bs.read(b, 0, b.length))!=-1){
				fs.write(b, 0, len);
			}
			System.out.println("done=============");
			bs.close();
			fs.close();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("error of writing when read file!");
		}
	}
	
	public boolean TestTime(){

		File file  = new File("AccidentsData.csv");
		long d1 = file.lastModified();
		long d2 = System.currentTimeMillis();
		long diff = (d2-d1)/(1000*60*60*24);
		
		// if data is download in one day, then false  
		if(diff < 1)   
			return false;
		else 
			return true;
	}
	
}

