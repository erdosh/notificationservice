package notificationservice.notificationservice.input;

/**
 * This class is used the parse the JSon String input of our registerUser service input published as a Rest service.
 * */
public class RegisterUserInput {
	private String userName;
	private String accessToken;
	public RegisterUserInput(String userName, String accessToken) {
		super();
		this.userName = userName;
		this.accessToken = accessToken;
	}
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
	@Override
	public String toString() {
		return "RegisterUserInput [username=" + userName + ", accessToken=" + accessToken + "]";
	}
}
