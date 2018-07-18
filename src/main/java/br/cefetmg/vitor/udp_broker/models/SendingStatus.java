package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class SendingStatus {

	public final static int SUCCESS = 1;
	public final static int ERROR = 2;
	
	private int value;
	private String description;
	
	public SendingStatus(int value) {
		this.value = value;
	}
	
	public SendingStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}
