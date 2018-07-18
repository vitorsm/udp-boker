package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class Topic {

	private String value;
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Topic) {
			return this.value != null && this.value == ((Topic) obj).value;
		}
		
		return false;
	}
}
