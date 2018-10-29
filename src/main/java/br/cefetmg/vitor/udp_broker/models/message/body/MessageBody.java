package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import lombok.Data;

@Data
public class MessageBody {

	private byte[] payload;
	protected int messageBodyLength;

	public byte[] getBytes() {
		int length = messageBodyLength > 0 ? messageBodyLength : Constants.MESSAGE_BODY_LENGTH;
		
		byte[] bytes = new byte[length];

		if (payload != null) {
			byte[] payloadBytes = payload;

			for (int i = 0; i < bytes.length && i < payloadBytes.length; i++) {
				bytes[i] = payloadBytes[i];
			}
		}

		return bytes;
	}

}
