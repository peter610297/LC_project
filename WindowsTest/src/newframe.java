import java.io.IOException;
import java.net.URL;
/*** For jframe***/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;


public class newframe extends JFrame{
	
	private JButton buttonOpen = new JButton("Find");
	
	public newframe( String Lon, String Lat){
		
		try {
			
			Frame frame = new JFrame("Google maps");
			String destinationFile = "image.jpg";
		    
		    URL url2 = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=10&size=800x800&markers="+Lat+","+Lon+"&sensor=false");
		    frame.add( new JLabel( new ImageIcon( new ImageIcon(url2).getImage().getScaledInstance( 600, 600, java.awt.Image.SCALE_SMOOTH ) ) ) );
			frame.setSize(600, 600);
		    frame.setVisible(true);
		    
		    } catch (IOException e) {
		        e.printStackTrace();
		    System.exit(1);
		    }
		  
	}
	/*
	
	public newframe(String Title){
		 super(Title);
		 setLayout(null);
		 
			try {
				

				String destinationFile = "image.jpg";
			    
			    String imageUrl2 = "http://maps.googleapis.com/maps/api/staticmap?zoom=10&size=800x800&markers=44.10253392,-81.14871575&sensor=true";
			    URL url2 = new URL(imageUrl2);
			    add( new JLabel( new ImageIcon( new ImageIcon(url2).getImage().getScaledInstance( 600, 600, java.awt.Image.SCALE_SMOOTH ) ) ) );
				setSize(600, 600);
			    setVisible(true);
			    } catch (IOException e) {
			        e.printStackTrace();
			    System.exit(1);
			    }
	    
	}
	
	public void createframe(String title){
	    newframe frame = new newframe(title);
	}
	*/
}

