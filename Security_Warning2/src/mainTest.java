import static org.junit.Assert.*;
import org.junit.*;


public class mainTest {
	LC testNum = new LC();

	@Before
	public void setup() throws Exception{
	}
	@Test
	public void test2(){
		int total = 0;
		int result = 0;
		DocApplication test = new DocApplication();
		test.doc = new UTF8Document();
		test.NewDocument("WomenData.csv");
		try{
			total = test.doc.Read();
		}	catch (Exception e){
			e.printStackTrace();
		}
		
		try {
			result = testNum.TestSize();
		} catch (Exception e) {
									// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(total,result);
	}
	@Test
	public void test3(){
		assertEquals(true,testNum.isNumber("792"));
	}
}
