import java.io.*;
import java.lang.String;
import org.json.*;
import java.util.regex.*;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.net.URLEncoder;
public class view {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			
			//�إ�URL
			String RoadAddress = "http://maps.google.com/maps/api/geocode/json?sensor=false&address=";
			RoadAddress += URLEncoder.encode("�x�W������", "UTF-8");
			URL url = new URL(RoadAddress);
			
			//�qURLŪ�����
			Scanner scan = new Scanner(url.openStream());
			String str = new String();
			while (scan.hasNext())
				str += scan.nextLine();
			scan.close();
			
			//�إ� JSON object
			JSONObject obj = new JSONObject(str);
			if (! obj.getString("status").equals("OK"))
				return;
			
			//��X�Ҧb�ĸg�n��
			JSONObject res = obj.getJSONArray("results").getJSONObject(0);
			System.out.println(res.getString("formatted_address"));
			JSONObject loc =res.getJSONObject("geometry").getJSONObject("location");
			System.out.println("lat: " + loc.getDouble("lat") +", lng: " + loc.getDouble("lng"));
			
			//�N double�� String�ÿ�Xjframe  
			newframe view = new newframe( Double.toString(loc.getDouble("lng")  ) ,Double.toString(loc.getDouble("lat") ));
		    
		} catch (IOException e) {
        	e.printStackTrace();
        	System.out.println("IOerror");
        } catch (JSONException e) {
        	e.printStackTrace();
        	System.out.println("JSONerror");
        }
        
	}

}
