import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class test {
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private ClassA testA = new ClassA();


	@Before
	public void setUpBeforeClass() {
		 System.setOut(new PrintStream(outContent));
		 System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void testClassName() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("Class: reflective_object_inspector_cpsc_501.ClassA")); 
	}
	
	@Test
	public void testSuperClassName() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("SuperClass: java.lang.Object")); 
		assertTrue(result.contains("\tClass: java.lang.Object")); 
	}
	
	@Test
	public void testSuperClassConstructor() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains(" CONSTRUCTOR\n\t  Name: java.lang.Object")); 
	}
	
	@Test
	public void testInterfaces() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains(" INTERFACE -> Recursively Inspect\n java.io.Serializable"));
		assertTrue(result.contains(" INTERFACE -> Recursively Inspect\n java.lang.Runnable")); 
	}
	
	@Test
	public void testConstructor() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains(" CONSTRUCTOR\n  Name: reflective_object_inspector_cpsc_501.ClassA")); 
	}
	
	@Test
	public void testSuperClassMethods() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("\t METHOD\n\t  Name: finalize\n\t  Exceptions-> \n\t  class java.lang.Throwable\n\t  Parameter types-> NONE")); 
		assertTrue(result.contains("\t METHOD\n\t  Name: wait\n\t  Exceptions-> \n\t  class java.lang.InterruptedException\n\t  Parameter types:\n\t  long")); 
		assertTrue(result.contains("\t METHOD\n\t  Name: equals")); 
		assertTrue(result.contains("\t METHOD\n\t  Name: toString")); 
		assertTrue(result.contains("\t METHOD\n\t  Name: hashCode")); 
		assertTrue(result.contains("\t METHOD\n\t  Name: notifyAll\n\t  Exceptions-> NONE\n\t  Parameter types-> NONE")); 
	}
	
	@Test
	public void testFields() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect(testA, false);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains(" FIELD\n  Name: val\n  Type: int\n  Modifiers: private\n  Value: 3"));
		assertTrue(result.contains(" FIELD\n  Name: val2\n  Type: double\n  Modifiers: private\n  Value: 0.2"));
		assertTrue(result.contains(" FIELD\n  Name: val3\n  Type: boolean\n  Modifiers: private\n  Value: true")); 
	}
	
	@Test
	public void testArrayEntries() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect("Test String", true);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("  Entries-> \n   Value: T\n   Value: e\n   Value: s\n   Value: t\n   Value:  \n   Value: S\n   Value: t\n   Value: r\n   Value: i\n   Value: n\n   Value: g\n"));
	}
	
	@Test
	public void testFieldObject() {
		outContent.reset(); // clear buffer 
		new Inspector().inspect("Test String", true);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("  Value (ref): java.lang.String$CaseInsensitiveComparator@"));
	}
	
	@Test
	public void testNestedFieldObject() throws Exception {
		outContent.reset(); // clear buffer 
		
		ClassB[] x = new ClassB[12];
        x[3] = new ClassB();
		new Inspector().inspect(x, true);
		String result = outContent.toString().trim(); // remove trailing spaces 
		assertTrue(result.contains("\t\t\t  Value (ref): reflective_object_inspector_cpsc_501.ClassA@"));
	}
}
