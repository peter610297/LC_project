import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class filedownload {
	public void download(String filename,String link) {
	      try {
	    	  
            URL url = new URL(link);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream =new  FileOutputStream(filename+".csv");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
            
            byte data[] = new byte[1024];
            while(bufferedInputStream.read(data, 0, 1024) >=0 )            {
                    bufferedOutputStream.write(data);
            }

            bufferedOutputStream.close();
            bufferedInputStream.close();
	         

	      } catch (MalformedURLException mue) {

	         System.out.println("Ouch - a MalformedURLException happened.");
	         mue.printStackTrace();
	         System.exit(1);

	      } catch (IOException ioe) {

	         System.out.println("Oops- an IOException happened.");
	         ioe.printStackTrace();
	         System.exit(1);

	      }
	}


}
