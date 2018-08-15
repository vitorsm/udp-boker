package br.cefetmg.vitor.udp_broker.models.message;

public enum MessageType {
	
	PUBLISH(1),
	SUBSCRIBE(2),
	KEEP_ALIVE(3),
	UPDATE_PARAM(4),
	DATA(5),
	HELLO(6),
	NETWORKS(7),
	CREDENTIALS_REQUEST(8),
	ERROR(9);
	
	private final int value;
	
	MessageType(int v) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public static MessageType toMessageType(int value) {
		
		for (MessageType messageType : MessageType.values()) {
			if (messageType.value == value)
				return messageType;
		}
		
		return null;
	}
	
}
