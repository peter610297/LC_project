/*Get the data link from the website and 
 * update the data if it's old           */
import java.io.BufferedInputStream;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class CatchData {
	public String data1 = "";
	public String data2 = "";
	
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
	//		String tmpName = name.substring(name.lastIndexOf("."), name.length());
//			msg = fileName+tmpName; //新取的檔名+副檔名
			msg = fileName+".csv";
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

/*read web, and get link*/
	public String GetURL(){
		int startLink;
		String thisline = "";
		try{
			URL pageUrl1 = new URL("http://data.gov.tw/?q=node/6132");
			URL pageUrl2 = new URL("http://data.gov.tw/?q=node/6247");
			
			BufferedReader br1 = new BufferedReader(new InputStreamReader(pageUrl1.openStream(),"UTF-8"));
			BufferedReader br2 = new BufferedReader(new InputStreamReader(pageUrl2.openStream(),"UTF-8"));
			System.out.println("read web is running!");
			
			while((thisline=br1.readLine()) != null){
				if((startLink = thisline.indexOf("node_metadataset_full_group_data_type")) > 0){
					int endLink = thisline.lastIndexOf("CSV");
					String tmplink = thisline.substring(startLink, endLink);
					String[] lArray = tmplink.split("\"");
					data1 = lArray[8];
				}
			}
			while((thisline=br2.readLine()) != null){
				if((startLink = thisline.indexOf("node_metadataset_full_group_data_type")) > 0){
					int endLink = thisline.lastIndexOf("CSV");
					String tmplink = thisline.substring(startLink, endLink);
					String[] lArray = tmplink.split("\"");
					data2 = lArray[8];
				}
			}
			
			System.out.println("Done");
		}catch (IOException e){
			e.printStackTrace();
		}
		return data1;
	}
}

