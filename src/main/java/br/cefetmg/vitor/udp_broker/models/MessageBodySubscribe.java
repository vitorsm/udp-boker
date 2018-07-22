package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class MessageBodySubscribe extends MessageBody {

	private Topic topic;

	public Topic getTopic() {
		if (topic == null) instanceTopic();
		
		return topic;
	}

	private void instanceTopic() {

		String message = new String(this.getBytes());

		topic = new Topic();
		topic.setValue(message);

	}
}
