package Lecture;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test; 
 
   
public class LectureBDTest { 
  @Before
	public void setUpStreams(){
		// System.setOut("Before");
    System.out.println("Before");
	}   

	@After  
	public void restoreStream(){
		// System.setOut("After");
    System.out.println("After");
	} 
  @Test 
  public  void mainClient() {  
      LectureBD lecture = new LectureBD();
      assertNotNull(lecture);
      lecture.lecturePersonnes("./data/personnes_utf8.xml");
      // try {
        // clientReader.read();
      // } catch(Exception ex){ 
        // System.out.print("Error: "); 
        // System.out.println(ex.getMessage());
      // }
      assertEquals("test","test");
  } 
}     