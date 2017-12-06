package commons;

public class StringMsg extends Message{
	private String content;
	private String playerName;
	
	public StringMsg(String playerName, String content) {
		super(MessageType.String);
		this.content = content;
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return type.toString() + '|' + playerName + '|' + sanitize(content);
	}
	
	private String sanitize(String in) {
		return in.replace('|', '/');
	}

}
