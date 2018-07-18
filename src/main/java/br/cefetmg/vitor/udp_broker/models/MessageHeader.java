package br.cefetmg.vitor.udp_broker.models;

import br.cefetmg.vitor.udp_broker.Constants;
import lombok.Data;

@Data
public class MessageHeader {

	private MessageType messageType;
	private String accessToken;

	
	public byte[] getBytes() {
		byte[] bytes = new byte[Constants.MESSAGE_TOKEN_LENGTH + Constants.MESSAGE_TYPE_LENGTH];
		
		bytes[0] = (byte) messageType.getValue();
		if (accessToken != null) {
			byte[] tokenStr = accessToken.getBytes();
			for (int i = 0; i < bytes.length - 1; i++) {
				bytes[i + 1] = tokenStr[i];
			}
		}
		
		return bytes;
	}
}
