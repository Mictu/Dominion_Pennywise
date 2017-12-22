package commons;

/**
 * Close Message for disconnect clients from server if they close.
 * 
 * @Source Pattern taken from ChatLab SoftwareEngineering2
 * 
 * @author Michael Tu
 * @author Brad Richards
 * 
 */
public class CloseMsg extends Message {

	private String content;
	private String playerName;

	public CloseMsg(String playerName, String content) {
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
