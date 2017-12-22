package commons;
/**
 * Chat Message for the chat.
 * 
 * @Source Pattern taken from ChatLab SoftwareEngineering2
 * 
 * @author Brad Richards
 */
public class ChatMsg extends Message{
	
	private String playerName;
	private String content;
	
	public ChatMsg(String playerName, String content) {
		super(MessageType.Chat);
		this.playerName = playerName;
		this.content = content;
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
