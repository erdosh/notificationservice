package notificationservice.notificationservice.input;
/**
 * This class is used to parse the JSon String of our sendNotification service input published as a Rest service.
 * */
public class SendNotificationInput {
	private String userName;
	private String body;
	private String title;
	public SendNotificationInput(String userName, String body, String title) {
		super();
		this.userName = userName;
		this.body = body;
		this.title = title;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SendNotificationInput [userName=" + userName + ", body=" + body + ", title=" + title + "]";
	}

}
