package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class MessageBodyData extends MessageBody {

	private Topic topic;
	private String messageContent;
	
	@Override
	public byte[] getBytes() {
		if (topic == null) instanceTopic();
		if (messageContent == null) instanceMessage();
		
		String strTopic = topic.getValue();
		
		while (strTopic.length() < Constants.MESSAGE_INPUT_ID_LENGTH) {
			strTopic = "0" + strTopic;
		}
		
		String strValue = messageContent;
		if (strValue.length() > Constants.MESSAGE_VALUE_LENGTH) {
			strValue = strValue.substring(0, Constants.MESSAGE_VALUE_LENGTH);
		} else {
			while (strValue.length() < Constants.MESSAGE_VALUE_LENGTH) {
				strValue = "0" + strValue;
			}
		}
		
		byte[] bytesTopic = strTopic.getBytes();
		byte[] bytesContent = strValue.getBytes();
		
		byte[] bytes = new byte[bytesTopic.length + bytesContent.length];
		for (int i = 0; i < Constants.MESSAGE_INPUT_ID_LENGTH && i < bytesTopic.length; i++) {
			bytes[i] = bytesTopic[i];
		}
		
		for (int i = Constants.MESSAGE_INPUT_ID_LENGTH; 
				i < bytes.length;
				i++) {
			bytes[i] = bytesContent[i - Constants.MESSAGE_INPUT_ID_LENGTH];
		}
		
		this.setPayload(bytes);
		this.messageBodyLength = bytes.length;
		
		return super.getBytes();
	}
	
	public Topic getTopic() {
		if (topic == null) instanceTopic();
		
		return topic;
	}
	
	public String getMessageContent() {
		if (messageContent == null) instanceMessage();
		
		return messageContent;
	}
	
	private void instanceTopic() {
		
		byte[] topicBytes = VectorUtils.subvector(this.getPayload(), 0, Constants.MESSAGE_TOPIC_LENGTH);
		
		String strTopic = MessageUtils.convertBytesToString(topicBytes);
		
		System.out.println("topico recebido: " + strTopic);
		
		topic = new Topic();
		topic.setValue(strTopic);
	}
	
	private void instanceMessage() {
		byte[] topicBytes = VectorUtils.subvector(this.getPayload(), Constants.MESSAGE_TOPIC_LENGTH, Constants.MESSAGE_BODY_LENGTH);
		
		messageContent = MessageUtils.convertBytesToString(topicBytes);
		
		System.out.println("conteudo recebido: " + messageContent);
	}
}
