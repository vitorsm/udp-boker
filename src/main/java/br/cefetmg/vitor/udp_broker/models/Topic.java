package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class Topic {

	private String value;

	
	public Topic() {
		
	}
	
	public Topic(String value) {
		this.value = value;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Topic) {
			return this.value != null && this.value.equals(((Topic) obj).value);
		}
		
		return false;
	}
}
