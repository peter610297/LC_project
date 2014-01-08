import java.awt.Frame;
import java.io.IOException;
import java.lang.String;
import org.json.*;

import java.net.URL;
import java.util.Scanner;
import java.net.URLEncoder;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class view {
	public view(String place){
		try{
			
			//建立URL
			String RoadAddress = "http://maps.google.com/maps/api/geocode/json?sensor=false&address=";
			RoadAddress += URLEncoder.encode(place,"UTF-8");
			URL url = new URL(RoadAddress);
			
			//從URL讀取資料
			Scanner scan = new Scanner(url.openStream(),"utf-8");
			String str = new String();
			while (scan.hasNext()){
				str += scan.nextLine();
				//System.out.println(scan.nextLine());
			}
			scan.close();
			
			//建立 JSON object
			JSONObject obj = new JSONObject(str);
			if (! obj.getString("status").equals("OK"))
				return;
			
			//輸出所在第經緯度
			JSONObject res = obj.getJSONArray("results").getJSONObject(0);
			System.out.println(res.getString("formatted_address"));
			JSONObject loc =res.getJSONObject("geometry").getJSONObject("location");
			System.out.println("lat: " + loc.getDouble("lat") +", lng: " + loc.getDouble("lng"));
			
			//將 double轉 String並輸出jframe  
			newframe( Double.toString(loc.getDouble("lng")  ) ,Double.toString(loc.getDouble("lat") ));
		    
		} catch (IOException e) {
	    	e.printStackTrace();
	    	System.out.println("IOerror");
	    } catch (JSONException e) {
	    	e.printStackTrace();
	    	System.out.println("JSONerror");
	    }
	}
	public void newframe( String Lon, String Lat){
		
		try {
			
			Frame frame = new JFrame("Google maps");
			String destinationFile = "image.jpg";
		    
		    URL url2 = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=15&size=800x800&markers="+Lat+","+Lon+"&sensor=false");
		    frame.add( new JLabel( new ImageIcon( new ImageIcon(url2).getImage().getScaledInstance( 600, 600, java.awt.Image.SCALE_SMOOTH ) ) ) );
			frame.setSize(600, 600);
		    frame.setVisible(true);
		    frame.setResizable(false);
		    } catch (IOException e) {
		        e.printStackTrace();
		    System.exit(1);
		    }
	}
}
