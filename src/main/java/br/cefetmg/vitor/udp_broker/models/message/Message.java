package br.cefetmg.vitor.udp_broker.models.message;

import br.cefetmg.vitor.udp_broker.models.message.body.MessageBody;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyHello;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyPublish;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodySubscribe;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;
import lombok.Data;

@Data
public class Message {

	private MessageHeader messageHeader;
	private MessageBody messageBody;

	public byte[] getBytes() {

		byte[] bytes = VectorUtils.concactByteVector(messageHeader.getBytes(), messageBody.getBytes());
		
		MessageUtils.addEmptyChar(bytes);
		
		return bytes;
	}

	public void convertMessageBodyToPublishMessageBody() {

		if (messageBody == null)
			return;

		MessageBody newMessageBody = new MessageBodyPublish();
		newMessageBody.setPayload(messageBody.getPayload());

		this.messageBody = newMessageBody;
	}

	public void convertMessageBodyToSubscribeMessageBody() {

		if (messageBody == null)
			return;

		MessageBody newMessageBody = new MessageBodySubscribe();
		newMessageBody.setPayload(messageBody.getPayload());
		
		this.messageBody = newMessageBody;
	}
	
	public void convertMessageBodyToHelloMessageBody() {
		
		if (messageBody == null)
			return;
		
		MessageBody newMessageBody = new MessageBodyHello();
		newMessageBody.setPayload(messageBody.getPayload());
		
		this.messageBody = newMessageBody;
	}
	
}
