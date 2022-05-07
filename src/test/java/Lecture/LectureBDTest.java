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
      Person person = Person.find(859);
      assertEquals(person.getId(),859);
      assertEquals(person.getName(),"Lionel Barrymore");

      assertEquals(Person.all().count(), 500);

      // List<Person> persons = Person.all();
// 859,Lionel Barrymore,1878-04-12,Philadelphia, Pennsylvania, USA,https://m.media-amazon.com/images/M/MV5BMTc5NzY5MTgwNV5BMl5BanBnXkFtZTYwMjc4NjQ2._V1_UY98_CR1,0,67,98_AL_.jpg

      // try {
        // clientReader.read();
      // } catch(Exception ex){ 
        // System.out.print("Error: "); 
        // System.out.println(ex.getMessage());
      // }
      assertEquals("test","test");  
  } 
}     