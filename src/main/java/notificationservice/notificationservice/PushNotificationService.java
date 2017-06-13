package notificationservice.notificationservice;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import notificationservice.notificationservice.input.PushBulletNotificationInput;
import notificationservice.notificationservice.input.RegisterUserInput;
import notificationservice.notificationservice.input.SendNotificationInput;

/**
 * This is the main rest handler database for our application
 * */
@Path("notificationservice")
public class PushNotificationService {
	Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

	private static final String endpoint = "https://api.pushbullet.com";
	private final String NOTIFICATION_TYPE_NOTE = "note"; 
	
	/**This HashMap has the role like being our registered user database.*/
	public static HashMap<String,User> registeredUsers = new HashMap<String, User>(); 

	/**
	 * 
	 * This method is the simple get tester method.*/
	@GET
	@Path("getservice")
	@Produces(MediaType.TEXT_PLAIN)
	public String notificationGetTest() {
		return "Notification service get test!";
	}

	
	/**
	 * Method used for registering a new user.Returns the newly registered user details like create date push count etc.
	 * */
	@POST
	@Path("registeruser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(String jsonString) {
		User newUser = new User();
		Gson gson = new Gson();
		RegisterUserInput input = gson.fromJson(jsonString, RegisterUserInput.class);
		if(input.getUserName() == null || input.getUserName().isEmpty()){
			String resp = "{\"error_1\":\"Provide a valid User-Name!\"}"; 
			logger.info("registerUser is called with input: " + jsonString + " output: " + resp);
			return resp;
		}
		if(input.getAccessToken() == null || input.getAccessToken().isEmpty()){
			String resp = "{\"error_2\":\"Provide a valid Access-Token!\"}"; 
			logger.info("registerUser is called with input: " + jsonString + " output: " + resp);
			return resp;
		}
		if(registeredUsers.containsKey(input.getUserName())){
			String resp = "{\"error_3\":\"User already exists!\"}"; 
			logger.info("registerUser is called with input: " + jsonString + " output: " + resp);
			return resp;
		}
		newUser.setAccessToken(input.getAccessToken());
		newUser.setNumOfNotificationsPushed("0");
		newUser.setUserName(input.getUserName());		
		newUser.setCreationTime(new Date().toString());
		registeredUsers.put(newUser.getUserName(), newUser);
		String result = gson.toJson(newUser);
		logger.info("registerUser is called with input: " + jsonString + " output: " + result);
		return result;
	}

	/**
	 * 
	 * This method gets all users as an array then returns this array's json String representation.*/
	@POST
	@Path("listusers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String listUsers() {
		User [] allUsers= new User[registeredUsers.size()];
		Iterator<String> iter = registeredUsers.keySet().iterator();
		int index=0;
		while(iter.hasNext()){
			String userName = iter.next();
			allUsers[index++] = registeredUsers.get(userName);
		}
		Gson gson = new Gson();
		String result =  gson.toJson(allUsers);
		logger.info("listUsers is called output: " + result);
		return result;
	}

	
	/**
	 * The method responsible for triggering a new push request making use of the push bullet API. 
	 * Assumption here is that the caller of our sendNotifications service sends the userName,body and the title inputs in JSon String format and we trigger the pushbullet.com push servive making use of the access token we registered with the new user registration. 
	 * */
	@POST
	@Path("sendnotification")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String sendNotification(String json) {
		Gson gson = new Gson();
		SendNotificationInput input = gson.fromJson(json, SendNotificationInput.class);
		User user = registeredUsers.get(input.getUserName());
		if(user == null ){
			String resp = "{\"error_4\":\"No such user found!\"}"; 
			logger.info("sendNotification is called with input: " + json + " output: " + resp);
			return resp;
		}
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Access-Token", user.getAccessToken());
		headers.put("Content-Type", "application/json");
		final String serviceName = "/v2/pushes";
		PushBulletNotificationInput pushBulletInput = new PushBulletNotificationInput();
		pushBulletInput.setBody(input.getBody());
		pushBulletInput.setTitle(input.getTitle());
		pushBulletInput.setType(NOTIFICATION_TYPE_NOTE);
		PostClient postClient = new PostClient();
		HttpResponse response = postClient.restPostExecute(endpoint.concat(serviceName), headers, pushBulletInput);
		try {
			String responseContent = EntityUtils.toString(response.getEntity());
			logger.info("sendNotification is called with input: " + json + " output: " + responseContent);
			return responseContent;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("sendNotification is called with input: " + json + " output: " + response.toString());

		return response.toString();
	}
}
