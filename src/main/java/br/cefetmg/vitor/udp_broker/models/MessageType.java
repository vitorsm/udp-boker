package br.cefetmg.vitor.udp_broker.models;

public enum MessageType {
	
	CONNECT(1), DATA(2), KEEP_ALIVE(3);
	
	private final int value;
	
	MessageType(int v) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
}
