import java.awt.Frame;
import java.io.*;
import java.lang.String;
import org.json.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Scanner;
import java.net.URLEncoder;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;


public class SaveImage {
	
	public String DangerCoor = "";
	static int ImgOrder = 0;
	static String outFileName;
 	
	public SaveImage(String DanStr){
		String[] Danger = DanStr.split("!");
		
		try{
			for(String DangerPlace : Danger){
				System.out.println(DangerPlace);
				
				String RoadAddress = "http://maps.google.com/maps/api/geocode/json?sensor=false&address=";
				RoadAddress += URLEncoder.encode(DangerPlace,"UTF-8");
				URL url = new URL(RoadAddress);

				Scanner scan = new Scanner(url.openStream(),"utf-8");
				String str = new String();
				while (scan.hasNext()){
					str += scan.nextLine();
				}
				scan.close();
				
				JSONObject obj = new JSONObject(str);
				if (! obj.getString("status").equals("OK"))
					continue;
				
				JSONObject res = obj.getJSONArray("results").getJSONObject(0);
				JSONObject loc =res.getJSONObject("geometry").getJSONObject("location");
				DangerCoor += loc.getDouble("lat") + "," + loc.getDouble("lng") + ":";
			}
			newframe(DangerCoor);
		    
		} catch (IOException e) {
	    	e.printStackTrace();
	    	System.out.println("IOerror");
	    } catch (JSONException e) {
	    	e.printStackTrace();
	    	System.out.println("JSONerror");
	    }
	}
	public void newframe(String Coordinates){
		String SubUrl = new String();
		String loc = new String();
		Sql Warnnum = new Sql();
		ImageIcon imageIcon = null;
		try {	
			
			String[] EachCoordinate = Coordinates.split(":");
			
			for(int i=1 ; i<EachCoordinate.length; i++){
				SubUrl += "&markers=icon:http://findicons.com/files/icons/2151/snow/32/skull.png%7C" + EachCoordinate[i];
			}
			
			if(Warnnum.countMatch <= 3)
				loc = "&markers=color:green%7Clabel:S%7C";
			else if(Warnnum.countMatch > 3 && Warnnum.countMatch <= 6)
				loc = "&markers=color:yellow%7Clabel:W%7C";
			else 
				loc = "&markers=color:red%7Clabel:D%7C";
			
		    URL urlMap = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=13&size=800x800&markers=label:W%7C"+ SubUrl +
		    		loc + EachCoordinate[0] + "&sensor=false");
		    imageIcon = new ImageIcon( new ImageIcon(urlMap).getImage().getScaledInstance( 600, 600, java.awt.Image.SCALE_SMOOTH ) );

		    
		    this.SaveImage(imageIcon);
		    
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.exit(1);
		    }
	}
	
	public boolean SaveImage(ImageIcon icon){
		outFileName = "Warning"+ ImgOrder +".png";
		System.out.println(outFileName);
		try{
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_4BYTE_ABGR);
			
			File file = new File(outFileName);
			file.delete();
			
			Graphics gc = bufferedImage.createGraphics();
			gc.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());
			ImageIO.write(bufferedImage, "png", new File(outFileName));
			gc.dispose();
			gc = null;
			
			ImgOrder = ImgOrder + 1;
		
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
