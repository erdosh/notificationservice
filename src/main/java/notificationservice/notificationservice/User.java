package notificationservice.notificationservice;

import java.io.Serializable;

/**
 * This class is to hold the basic data structure of a user.Once a user is registered to the system all members of this class are serialized and returned.
 * */
public class User implements Serializable{
	private static final long serialVersionUID = -2048454756021502413L;

	private String userName;
	private String accessToken;
	private String creationTime;
	private String numOfNotificationsPushed;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getNumOfNotificationsPushed() {
		return numOfNotificationsPushed;
	}
	public void setNumOfNotificationsPushed(String numOfNotificationsPushed) {
		this.numOfNotificationsPushed = numOfNotificationsPushed;
	}
}
