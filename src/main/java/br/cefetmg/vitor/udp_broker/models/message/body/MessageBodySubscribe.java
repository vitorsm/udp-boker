package br.cefetmg.vitor.udp_broker.models.message.body;

import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import lombok.Data;

@Data
public class MessageBodySubscribe extends MessageBody {

	private Topic topic;

	public Topic getTopic() {
		if (topic == null) instanceTopic();
		
		return topic;
	}

	private void instanceTopic() {

		topic = new Topic();
		topic.setValue(MessageUtils.convertBytesToString(this.getBytes()));

	}
}
