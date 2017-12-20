package commons;

public class CloseMsg extends Message{

	private String content;
	private String playerName;
	
	public CloseMsg(String playerName,String content) {
		super(MessageType.Close);
		this.content = content;
		this.playerName = playerName;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public String toString() {
		return type.toString() + '|' + playerName + '|' + sanitize(content);
	}
	
	private String sanitize(String in) {
		return in.replace('|', '/');
	}

}
