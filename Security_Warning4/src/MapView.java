import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.util.*;

public class MapView  {
	static int ImgNum = 0; 
	static String fileName;	
	private String location = ("臺南市");
	
	public MapView() {
		
        JPanel panel1 = new JPanel();
        final ImagePanel panel2 = new ImagePanel("Warning1.png");
        
        final JTextField field = new JTextField(20);
        field.setText("請輸入地址");
        JButton buttonSettings = new JButton("GO");
        
        panel1.add(field);
        panel1.add(buttonSettings);
       
        panel2.setPreferredSize(new Dimension(600, 600));
        
        
        buttonSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	location = field.getText();
            	System.out.print("Your location is "+location+ "!\n");
            	
            	Sql locSql = new Sql();
            	locSql.LocationSql(location);
            	
        		SaveImage place = new SaveImage(locSql.DanStr);
        		
        		
            	fileName = "Warning"+ ImgNum +".png";
            	panel2.changeimg( new ImageIcon(fileName).getImage());
            	ImgNum = ImgNum + 1 ;
            	System.out.println("Mapview: "+location);
            }
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.NORTH);
        frame.pack();

        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class ImagePanel extends JPanel  {

        public Image img;
        
        public ImagePanel(String img) {
            this(Toolkit.getDefaultToolkit().createImage(img));
            System.out.println(this.img);
        }
        
        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setLayout(new GridBagLayout());
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
        
        
        public void changeimg(Image newimg){
        	this.img = newimg;
        	this.repaint();
        }
    }
}