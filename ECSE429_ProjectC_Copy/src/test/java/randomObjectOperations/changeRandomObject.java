package randomObjectOperations;
import java.lang.management.ManagementFactory;
import java.util.Random;

import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sun.management.OperatingSystemMXBean;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
public class changeRandomObject {
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	@Test
	public void amendMultipleTodos() {
		for(int i = 2000; i < 3000; i++) {
			String id = Integer.toString(i);
			JSONObject body2 = new JSONObject();
			body2.put("description", "This is nonesense");
			long startTime = System.nanoTime();
			Response response2 = RestAssured.given().contentType(ContentType.JSON)
					.body(body2.toString()).post("http://localhost:4567/todos/" + id);
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration);
//			if(i % 10 == 0) {
//				System.out.println(duration);					
//			}
			//	     // Get the Java runtime
//	        Runtime runtime = Runtime.getRuntime();
//	        // Run the garbage collector
//	        runtime.gc();
//	        // Calculate the used memory
//	        long memory = runtime.totalMemory() - runtime.freeMemory();
//	        System.out.println(memory);
 		}	
	}
	
	@Test
	public void amendSingleTodo() {
		long startTime = System.nanoTime();
		JSONObject body2 = new JSONObject();
		body2.put("description", "This is nonesense");
		Response response2 = RestAssured.given().contentType(ContentType.JSON)
				.body(body2.toString()).post("http://localhost:4567/todos/" + "2");
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
		System.out.println("Amending single todo takes:	" + duration + " ms");		
	}
	
}
