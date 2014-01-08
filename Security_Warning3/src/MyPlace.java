
import java.util.*;

public class MyPlace {
	private String s = "¹ü¤Æ¿¤¤GªLÂí";
	public void MyLocation(){
		Scanner location = null;
		try{
		location = new Scanner(System.in,"utf-8");
		Sql locSql = new Sql();
		
//		System.out.println("Please input your location : ");
//		s = location.nextLine();
		System.out.printf("Your location is %s !\n",s);
		
		locSql.LocationSql(s);
		view place = new view (s);
		
		
		}finally{
			if(location != null)
				location.close();
		}
	}
}
