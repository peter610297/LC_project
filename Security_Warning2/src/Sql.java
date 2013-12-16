
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Sql {
	public void TestSql(){
		System.out.println("This is DBTest");
		
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			
			//jdbc:postgresql://ip_address:port/database_name
			String url = "jdbc:postgresql://210.61.10.89:9999/Team6";
			Connection con = DriverManager.getConnection(url,"Team6","2013postgres");
			Statement st = con.createStatement();
			
			String sql = "insert into tablesql values ('9','female','100','222','in cash');";
			st.executeQuery(sql);
			st.close();
			con.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
