package br.cefetmg.vitor.udp_broker.models;

public enum OperationType {

	EQUALS(1),
	HIGHER(2),
	LESS(3),
	HIGHER_EQUALS(4),
	LESS_EQUALS(5),
	NOT_EQUALS(6);
	
	private final int value;
	
	OperationType(int v) {
		value = v;
	}
	
	
	public int getValue() {
		return value;
	}
}
