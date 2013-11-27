import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStreamReader;
import com.csvreader.CsvReader; 


interface Dog
{
  public void speak ();
}

class Poodle implements Dog
{
  public void speak()
  {
    System.out.println("The poodle says \"arf\"");
  }
}

class Rottweiler implements Dog
{
  public void speak()
  {
    System.out.println("The Rottweiler says (in a very deep voice) \"WOOF!\"");
  }
}

class SiberianHusky implements Dog
{
  public void speak()
  {
    System.out.println("The husky says \"Dude, what's up?\"");
  }
}
class DogFactory
{
  public static Dog getDog(String criteria)
  {
    if ( criteria.equals("small") )
      return new Poodle();
    else if ( criteria.equals("big") )
      return new Rottweiler();
    else if ( criteria.equals("working") )
      return new SiberianHusky();

    return null;
  }
}
public class HW1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			CsvReader products = new CsvReader(new InputStreamReader(new FileInputStream(new File("women.csv")),"UTF-8"));  
		
			//products.readHeaders();

			while (products.readRecord())
			{
				System.out.println("current record: " + products.getCurrentRecord());  
			    System.out.println("RawRecord:" + products.getRawRecord());  
			    System.out.println("getValues() ");  
                for (String s : products.getValues()) {  
                    System.out.print("--" + s);  
                }  
                System.out.println();
			}
	
			products.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
