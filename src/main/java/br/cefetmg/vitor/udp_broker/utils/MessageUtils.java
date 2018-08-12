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
			if (c != 0 && c != Constants.EMPTY_CHAR)
				strReturn += c;
		}
		
		return strReturn;
	}
	
	public String getToken(byte[] bytes) {
		
		byte[] accessToken = VectorUtils.subvector(bytes, Constants.MESSAGE_TYPE_LENGTH,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH);
	
		return convertBytesToString(accessToken);
	}
	
	public static Message convertToMessage(byte[] bytes) {
		
		byte typeByte = bytes[0];
		byte[] accessToken = VectorUtils.subvector(bytes, Constants.MESSAGE_TYPE_LENGTH,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH);
		byte[] messageBodyBytes = VectorUtils.subvector(bytes,
				Constants.MESSAGE_TYPE_LENGTH + Constants.MESSAGE_TOKEN_LENGTH,
				Constants.MESSAGE_LENGTH);
		
		removeEmptyChar(messageBodyBytes);
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setMessageType(MessageType.toMessageType((int) typeByte));
		messageHeader.setAccessToken(convertBytesToString(accessToken));
		
		MessageBody messageBody = new MessageBody();
		messageBody.setPayload(messageBodyBytes);
		
		Message message = new Message();
		message.setMessageHeader(messageHeader);
		message.setMessageBody(messageBody);
		
		return message;
	}
	
	public static void removeEmptyChar(byte[] bytes) {
		
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == Constants.EMPTY_CHAR)
				bytes[i] = 0;
		}
	}
	
	public static void addEmptyChar(byte[] bytes) {
		
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0)
				bytes[i] = Constants.EMPTY_CHAR;
		}
	}
}
