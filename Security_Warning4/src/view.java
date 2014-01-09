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


public class view {
	public String DangerCoor = "";
	
	public view(String DanStr){
		String[] Danger = DanStr.split("!");
//		for(String d : Danger){
//			System.out.println(d);
//		}
//		System.out.println("_____________________________");
		
		try{
			for(String DangerPlace : Danger){
				System.out.println(DangerPlace);
				//建立URL
				String RoadAddress = "http://maps.google.com/maps/api/geocode/json?sensor=false&address=";
				RoadAddress += URLEncoder.encode(DangerPlace,"UTF-8");
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
					continue;
				
				//輸出所在第經緯度
				JSONObject res = obj.getJSONArray("results").getJSONObject(0);
	//			System.out.println(res.getString("formatted_address"));
				JSONObject loc =res.getJSONObject("geometry").getJSONObject("location");
		//		System.out.println("lat: " + loc.getDouble("lat") +", lng: " + loc.getDouble("lng"));
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
			JFrame frame = new JFrame("Google maps");
	//		String destinationFile = "image.jpg";
			
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
		    frame.add( new JLabel(imageIcon) );
		    
		    this.SaveImage(imageIcon);
		 //   imageUrl = ImageIO.read(urlMap);
		    
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 600);
		    frame.setVisible(true);
		    frame.setResizable(false);
	//	    frame.pack();
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.exit(1);
		    }
	}
	
	public boolean SaveImage(ImageIcon icon){
		
		try{
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			
			Graphics gc = bufferedImage.createGraphics();
			gc.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());
			ImageIO.write(bufferedImage, "png", new File("Warning.png"));
			gc.dispose();
			gc = null;
		
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
