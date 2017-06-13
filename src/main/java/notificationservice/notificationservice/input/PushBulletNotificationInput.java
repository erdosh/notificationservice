package notificationservice.notificationservice.input;

/**
 * This class is used to fill the necessary inputs for the pushbullet.com create notification service. 
 * */
public class PushBulletNotificationInput {
	private String body;
	private String title;
	private String type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PushBulletNotificationInput [body=" + body + ", title=" + title + ", type=" + type + "]";
	}
}
