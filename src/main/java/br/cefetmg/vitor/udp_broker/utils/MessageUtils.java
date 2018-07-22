package br.cefetmg.vitor.udp_broker.utils;

import java.nio.charset.Charset;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBody;

public class MessageUtils {

	public static String convertBytesToString(byte[] bytes) {
		
		if (bytes == null)
			return null;
		
		String str = new String(bytes, Charset.forName("UTF-8"));
		String strReturn = "";
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != 0)
				strReturn += c;
		}
		
		return strReturn;
	}
	
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
