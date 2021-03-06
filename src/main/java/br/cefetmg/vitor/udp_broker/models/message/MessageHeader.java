package br.cefetmg.vitor.udp_broker.models.message;

import br.cefetmg.vitor.udp_broker.Constants;
import lombok.Data;

@Data
public class MessageHeader {

	private MessageType messageType;
	private String accessToken;
	private boolean allLength;

	public MessageHeader() {
		allLength = true;
	}
	
	
	public byte[] getBytes() {
		
		byte[] bytes = null;
		
		if (allLength || accessToken != null) {
			bytes = new byte[Constants.MESSAGE_TOKEN_LENGTH + Constants.MESSAGE_TYPE_LENGTH];
			bytes[0] = (byte) (messageType.getValue() + '0');
			if (accessToken != null) {
				byte[] tokenStr = accessToken.getBytes();
				for (int i = 0; i < bytes.length - 1 && i < tokenStr.length; i++) {
					bytes[i + 1] = tokenStr[i];
				}
			}
		} else {
			bytes = new byte[Constants.MESSAGE_TYPE_LENGTH];
			bytes[0] = (byte) (messageType.getValue() + '0');
		}

		
		return bytes;
	}
}
