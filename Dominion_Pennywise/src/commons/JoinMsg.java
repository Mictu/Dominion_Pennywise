package commons;

public class JoinMsg extends Message{
	private String playerName;
	
	public JoinMsg(String playerName) {
		super(MessageType.Join);
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		return type.toString() + '|' + playerName;
	}
	
}
