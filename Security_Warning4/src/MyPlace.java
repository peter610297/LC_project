
import java.util.*;
public class MyPlace {
	private String s = ("臺北市大安區");
	public void MyLocation(){
		Scanner location = null;
		try{

		location = new Scanner(System.in,"utf-8");
		Sql locSql = new Sql();
		
//		System.out.println("Please input your location : ");
//		s = location.nextLine();
		System.out.print("Your location is "+s+ "!\n");
		
		locSql.LocationSql(s);
		view place = new view(locSql.DanStr);
		
		
		}finally{
			if(location != null)
				location.close();
		}
	}
}
