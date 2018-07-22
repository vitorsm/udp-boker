package br.cefetmg.vitor.udp_broker.models;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class MessageBodyPublish extends MessageBody {

	private Topic topic;
	private String messageContent;
	
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
		String payload = new String(topicBytes);
		
		topic = new Topic();
		topic.setValue(payload);
	}
	
	private void instanceMessage() {
		byte[] topicBytes = VectorUtils.subvector(this.getPayload(), Constants.MESSAGE_TOPIC_LENGTH, Constants.MESSAGE_VALUE_LENGTH);
		
		messageContent = new String(topicBytes);
	}
	
}
