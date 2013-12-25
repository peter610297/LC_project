
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Random;

public class Sql {
	private int count = 0;
	
	public Sql(){
		System.out.println("This is DBTest");
	}
	public void AccSql(String date, String time, String place){
//		Random rand = new Random();
		
		try{		
			Class.forName("org.postgresql.Driver").newInstance();
			
			//jdbc:postgresql://ip_address:port/database_name
			String url = "jdbc:postgresql://210.61.10.89:9999/Team6";
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			
			if(count == 0){
				String clean = "delete from accident;";
				st.execute(clean);
			}
			count++;
	//		int i = rand.nextInt(100);
			String sql = "insert into accident values ('"+count+"','"+date+"','"+time+"','"+place+"');";
			st.executeQuery(sql);
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void WomSql(String num, String place){
	//	Random rand = new Random();
		int c = 0;
		try{		
			Class.forName("org.postgresql.Driver").newInstance();
			
			//jdbc:postgresql://ip_address:port/database_name
			String url = "jdbc:postgresql://210.61.10.89:9999/Team6";
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			
			if(c == 0){
				String clean = "delete from woman;";
				st.execute(clean);
			}
			c++;
	//		int i = rand.nextInt(100);
			String sql = "insert into woman values ('"+num+"','"+place+"');";
			st.executeQuery(sql);
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

