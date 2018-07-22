package br.cefetmg.vitor.udp_broker.models;

import br.cefetmg.vitor.udp_broker.Constants;
import lombok.Data;

@Data
public class MessageBody {

	private byte[] payload;

	public byte[] getBytes() {
		byte[] bytes = new byte[Constants.MESSAGE_BODY_LENGTH];

		if (payload != null) {
			byte[] payloadBytes = payload;

			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = payloadBytes[i];
			}
		}

		return bytes;
	}

}
