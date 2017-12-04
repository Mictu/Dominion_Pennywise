package commons;

public class StringMsg extends Message{
	private String content;
	
	public StringMsg(String content) {
		super(MessageType.String);
		this.content = content;
	}

//	public String getPlayerName() {
//		return playerName;
//	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return type.toString() + '|' + sanitize(content) /* + '|' + sanitize(content)*/;
	}
	
	private String sanitize(String in) {
		return in.replace('|', '/');
	}

}
