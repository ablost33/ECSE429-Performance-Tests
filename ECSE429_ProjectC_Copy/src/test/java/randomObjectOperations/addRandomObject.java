package randomObjectOperations;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;
import org.json.XML;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
public class addRandomObject {

	@Test
	public static void createMultipleRandomTodos() {
		for(int i = 0; i < 1000; i++) {
			// Create JSON body
			JSONObject body = new JSONObject();
			String title = getSaltString();
			String description = getSaltString();
			body.put("title", title);
			body.put("doneStatus", false);
			body.put("description", description);
			//	Create random Object
			long startTime = System.nanoTime();
			//System.out.println(startTime/1000000);
			Response response = RestAssured.given().contentType(ContentType.JSON)
					.body(body.toString()).post("http://localhost:4567/todos");
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration);
//	        // Get the Java runtime
//	        Runtime runtime = Runtime.getRuntime();
//	        // Run the garbage collector
//	        runtime.gc();
//	        // Calculate the used memory
//	        long memory = runtime.totalMemory() - runtime.freeMemory();
//	        System.out.println(memory);
		}
	}
	
	@Test
	public static void createSingleRandomTodo() {
		long startTime = System.nanoTime();
		// Create JSON body
		JSONObject body = new JSONObject();
		String title = getSaltString();
		String description = getSaltString();
		body.put("title", title);
		body.put("doneStatus", false);
		body.put("description", description);
		//	Create random Object
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.body(body.toString()).post("http://localhost:4567/todos");
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
		System.out.println("Single todo creation takes:	" + duration + " ms");	
	}
	
	protected static String getSaltString() {
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
}
