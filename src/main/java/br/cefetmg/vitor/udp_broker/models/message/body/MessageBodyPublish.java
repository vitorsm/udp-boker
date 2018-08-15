package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class MessageBodyPublish extends MessageBody {

	private Topic topic;
	private String messageContent;
	
	@Override
	public byte[] getBytes() {
		if (topic == null) instanceTopic();
		if (messageContent == null) instanceMessage();
		
		byte[] bytesTopic = topic.getValue().getBytes();
		byte[] bytesContent = messageContent.getBytes();
		
		byte[] bytes = new byte[Constants.MESSAGE_BODY_LENGTH];
		for (int i = 0; i < Constants.MESSAGE_TOPIC_LENGTH && i < bytesTopic.length; i++) {
			bytes[i] = bytesTopic[i];
		}
		
		for (int i = Constants.MESSAGE_TOPIC_LENGTH; 
				i < Constants.MESSAGE_BODY_LENGTH && i - Constants.MESSAGE_TOPIC_LENGTH < bytesContent.length;
				i++) {
			bytes[i] = bytesContent[i - Constants.MESSAGE_TOPIC_LENGTH];
		}
		
		this.setPayload(bytes);
		
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
		
		topic = new Topic();
		topic.setValue(MessageUtils.convertBytesToString(topicBytes));
	}
	
	private void instanceMessage() {
		byte[] topicBytes = VectorUtils.subvector(this.getPayload(), Constants.MESSAGE_TOPIC_LENGTH, Constants.MESSAGE_BODY_LENGTH);
		
		messageContent = MessageUtils.convertBytesToString(topicBytes);
	}
	
}
