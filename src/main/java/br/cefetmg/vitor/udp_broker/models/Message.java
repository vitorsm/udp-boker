package br.cefetmg.vitor.udp_broker.models;

import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class Message {
	
	private MessageHeader messageHeader;
	private MessageBody messageBody;
	
	public byte[] getBytes() {
		
		byte[] bytes = VectorUtils.concactByteVector(messageHeader.getBytes(), messageBody.getBytes());
		
		return bytes;
	}
}
