package notificationservice.notificationservice.output;

/**
 * This class is to parse the output of the createNotification service that our application trigger on the pushbullet.com API.
 * */
public class PushBulletNotificationOutput {
	private String active;
	private String body;
	private String created;
	private String direction;
	private String dismissed;
	private String iden;
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDismissed() {
		return dismissed;
	}
	public void setDismissed(String dismissed) {
		this.dismissed = dismissed;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String iden) {
		this.iden = iden;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getReceiver_email() {
		return receiver_email;
	}
	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}
	public String getReceiver_email_normalized() {
		return receiver_email_normalized;
	}
	public void setReceiver_email_normalized(String receiver_email_normalized) {
		this.receiver_email_normalized = receiver_email_normalized;
	}
	public String getReceiver_iden() {
		return receiver_iden;
	}
	public void setReceiver_iden(String receiver_iden) {
		this.receiver_iden = receiver_iden;
	}
	public String getSender_email() {
		return sender_email;
	}
	public void setSender_email(String sender_email) {
		this.sender_email = sender_email;
	}
	public String getSender_email_normalized() {
		return sender_email_normalized;
	}
	public void setSender_email_normalized(String sender_email_normalized) {
		this.sender_email_normalized = sender_email_normalized;
	}
	public String getSender_iden() {
		return sender_iden;
	}
	public void setSender_iden(String sender_iden) {
		this.sender_iden = sender_iden;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
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
	private String modified;
	private String receiver_email;
	private String receiver_email_normalized;
	private String receiver_iden;
	private String sender_email;
	private String sender_email_normalized;
	private String sender_iden;
	private String sender_name;
	private String title;
	private String type;
}
