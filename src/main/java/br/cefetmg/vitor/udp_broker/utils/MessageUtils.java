package br.cefetmg.vitor.udp_broker.utils;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Message;
import br.cefetmg.vitor.udp_broker.models.MessageBody;
import br.cefetmg.vitor.udp_broker.models.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.MessageType;

public class MessageUtils {

	public String getToken(byte[] bytes) {
		
		byte[] accessToken = VectorUtils.subvector(bytes, Constants.MESSAGE_TYPE_LENGTH,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH);
	
		return new String(accessToken);
	}
	
	public static Message convertToMessage(byte[] bytes) {
		
		byte typeByte = bytes[0];
		byte[] accessToken = VectorUtils.subvector(bytes, Constants.MESSAGE_TYPE_LENGTH,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH);
		byte[] messageBodyBytes = VectorUtils.subvector(bytes,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH,
				Constants.MESSAGE_LENGTH);
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setMessageType(MessageType.toMessageType((int) typeByte));
		messageHeader.setAccessToken(new String(accessToken));
		
		MessageBody messageBody = new MessageBody();
		messageBody.setPayload(messageBodyBytes);
		
		Message message = new Message();
		message.setMessageHeader(messageHeader);
		message.setMessageBody(messageBody);
		
		return message;
	}
}
