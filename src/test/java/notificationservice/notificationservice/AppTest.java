package notificationservice.notificationservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import notificationservice.notificationservice.input.RegisterUserInput;
import notificationservice.notificationservice.input.SendNotificationInput;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private String url = "http://localhost:8282/notificationservice/";
	private static Map<String,String> headerMap = new HashMap<String,String>();
	private String registerNewUserServiceName = "registeruser";
	private String sendNotificationServiceName = "sendnotification";
	private String listUsersServiceName = "listusers";
	private TestPostClient client = new TestPostClient(); 


	@BeforeClass
	public static void setUp(){
		headerMap.put("Content-Type", "application/json");
		Thread th = new Thread( 
				new Runnable() {
					public void run() {
						NotificationServer.startServer();

					}
				});
		th.start();

	}
	@AfterClass
	public static void tearDown(){
		NotificationServer.shutDownServer();
	}
	@Test
	public void test() throws ParseException, IOException, InterruptedException{
		/*wait till we are sure that server has been started*/
		while(!NotificationServer.isRunning()){
			Thread.sleep(5000);
		}
		
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(registerNewUserServiceName), headerMap, new RegisterUserInput("erdosh", "o.X2Uz26MBlTle5elag1hefqSqtqJiFm3t"))
				.getEntity()).startsWith("{\"userName\":"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(registerNewUserServiceName), headerMap, new RegisterUserInput("", "o.X2Uz26MBlTle5elag1hefqSqtqJiFm3t"))
				.getEntity()).startsWith("{\"error_1\":\"Provide a valid User-Name!\"}"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(registerNewUserServiceName), headerMap, new RegisterUserInput("erdosh", ""))
				.getEntity()).startsWith("{\"error_2\":\"Provide a valid Access-Token!\"}"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(registerNewUserServiceName), headerMap, new RegisterUserInput("erdosh", "o.X2Uz26MBlTle5elag1hefqSqtqJiFm3t"))
				.getEntity()).startsWith("{\"error_3\":\"User already exists!\"}"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(sendNotificationServiceName), headerMap, new SendNotificationInput("erdosh","TEST Body","TEST TITLE"))
				.getEntity()).contains("{\"active\": true,"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(sendNotificationServiceName), headerMap, new SendNotificationInput("erd","TEST Body","TEST TITLE"))
				.getEntity()).startsWith("{\"error_4\": \"No such user found!\"}"));
		Assert.assertTrue(EntityUtils.toString(client.restPostExecute(url.concat(listUsersServiceName), headerMap, null)
				.getEntity()).startsWith("[{\"userName\":"));
		
		
	}




}
