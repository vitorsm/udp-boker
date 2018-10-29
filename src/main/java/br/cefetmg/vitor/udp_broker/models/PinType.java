package br.cefetmg.vitor.udp_broker.models;

public enum PinType {

	INPUT(1),
	OUTPUT(2),
	BINARY_OUTPUT(3);
	
	private final int value;
	
	PinType(int v) {
		value = v;
	}
	
	
	public int getValue() {
		return value;
	}
}
