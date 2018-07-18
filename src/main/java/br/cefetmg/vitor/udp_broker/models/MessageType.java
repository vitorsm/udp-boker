package br.cefetmg.vitor.udp_broker.models;

public enum MessageType {
	
	PUBLISH(1), SUBSCRIBE(2), KEEP_ALIVE(3), UPDATE_PARAM(4);
	
	private final int value;
	
	MessageType(int v) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public static MessageType toMessageType(int value) {
		
		if (value == PUBLISH.value) {
			return PUBLISH;
		} else if (value == SUBSCRIBE.value) {
			return SUBSCRIBE;
		} else if (value == KEEP_ALIVE.value) {
			return KEEP_ALIVE;
		} else if (value == UPDATE_PARAM.value) {
			return UPDATE_PARAM;
		} else {
			return null;
		}
	}
	
//	@Override
//	public String toString() {
//		
//		if (value == CONNECT.value) {
//			return "CONNECT";
//		} else if (value == DATA.value) {
//			return "DATA";
//		} else if (value == KEEP_ALIVE.value) {
//			return "KEEP_ALIVE";
//		} else {
//			return "NO TYPE";
//		}
//	}
}
