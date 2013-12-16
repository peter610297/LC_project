
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
}

