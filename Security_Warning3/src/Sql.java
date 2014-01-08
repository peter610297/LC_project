
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Sql {
	private int countA = 0;
	private int countW = 0;
	private int countMatch = 0;
	public String url = "";
	public Sql(){
//		System.out.println("This is DBTest");
		try{
			Class.forName("org.postgresql.Driver").newInstance();
		
			//jdbc:postgresql://ip_address:port/database_name
			url = "jdbc:postgresql://210.61.10.89:9999/Team6";
			
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void AccSql(String date, String time, String place){
		
		try{		
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			
			if(countA == 0){
				String clean = "delete from accident;";
				st.execute(clean);
			}
			countA++;
	//		int i = rand.nextInt(100);
			String sql = "insert into accident values ('"+countA+"','"+date+"','"+time+"','"+place+"');";
			st.executeQuery(sql);
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void WomSql(String num, String place){

		try{		
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			
			if(countW == 0){
				String clean = "delete from woman;";
				st.execute(clean);
			}
			countW++;
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
	
	public void LocationSql(String location){
		
		try{
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			String sql1 = "select * from accident where place like '%"+ location +"%';";
			String sql2 = "select * from woman where place like '%"+ location +"%';";
			ResultSet rs1 = st.executeQuery(sql1);
			while(rs1.next()){
				countMatch++;
				System.out.print(rs1.getString("place") + "\n");
			}
			ResultSet rs2 = st.executeQuery(sql2);
			while(rs2.next()){
				countMatch++;
				System.out.print(rs2.getString("place") + "\n");
			}
			if(countMatch == 0)
				System.out.println("There is no any result.");
			else 
				System.out.printf("There are %d accidents matched !!\n\n",countMatch);
			st.close();
			con.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

