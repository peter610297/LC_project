import static org.junit.Assert.*;
import org.junit.*;


public class mainTest {
	LC testNum = new LC();
	
	@Before
	public void setup() throws Exception{
	}
	@Test
	public void testTotal(){
		int total = 0;
		int result = 0;
		DocApplication test = new DocApplication();
		test.doc = new UTF8Document();
		test.NewDocument("WomenData.csv");
		try{
			total = test.doc.Read();
			result = testNum.TestSize();
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(total,result);
	}
	@Test
	public void testLink(){
		CatchData data = new CatchData();
		String Link = "";
		String LinkResult = "";
		try{
			LinkResult = new String(data.GetURL());
			Link = "http://www.npa.gov.tw/NPAGip/wSite/public/Attachment/f1365640740030.csv";
		}catch (Exception e){
			e.printStackTrace();
		}
		assertTrue(Link.equals(LinkResult));
	}
}
