import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import commonFunctionsPackage.Utility_Common_Functions;
public class DriverClass {
	
public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, 
IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

	ArrayList<String> testcaserun = Utility_Common_Functions.readDataExcel("TestRunner","TestNameToExecute");
	int count =testcaserun.size();
	//System.out.println(count);
	 for (int i=1;i<count;i++)
{
			String testcaseName=testcaserun.get(i);
			//System.out.println(testcasename);
			
			// Call the testcaseclass on runtime by using java.lang.reflect package
			Class<?> testclassName=Class.forName("testClassPackage."+testcaseName);

			// Call the execute method belonging to test class captured in variable testclassname by using java.lang.reflect.method class
			Method executemethod=testclassName.getDeclaredMethod("execute");

			// Set the accessibility of method true 
			executemethod.setAccessible(true);

			// Create the instance of testclass captured in variable name testclassname
			Object instanceoftestclass=testclassName.getDeclaredConstructor().newInstance();

			// Execute the testclass captured in variable name testclass name
			executemethod.invoke(instanceoftestclass);
		} 
	}
} 	
