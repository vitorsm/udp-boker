package br.cefetmg.vitor.udp_broker;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyHello;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyUpdateParam;

public class JoinningTest implements IJoinning {

	private IBroker broker;
	
	public JoinningTest(IBroker broker) {
		this.broker = broker;
	}
	
	@Override
	public void joinning(Client client, Credentials credentials) {
		
		MessageHeader messageHeader = new MessageHeader();
		if (broker.getSecurity() == null)
			messageHeader.setAccessToken("");
		else
			messageHeader.setAccessToken(broker.getSecurity().getAccessToken());
		
		messageHeader.setMessageType(MessageType.UPDATE_PARAM);
		
		MessageBodyUpdateParam messageBody = new MessageBodyUpdateParam();
		messageBody.setParams("ParametrosTeste");
		
		Message message = new Message();
		message.setMessageHeader(messageHeader);
		message.setMessageBody(messageBody);
		
		broker.sendParamMessage(client, message);
		addClientIntoTestTopics(client);
	}

	private void addClientIntoTestTopics(Client client) {
		Topic topic = new Topic();
		topic.setValue("test1");
		broker.addClientIntoTopic(client, topic);

	}
}
