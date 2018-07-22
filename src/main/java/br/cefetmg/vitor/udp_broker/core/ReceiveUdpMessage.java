package br.cefetmg.vitor.udp_broker.core;

import java.net.DatagramPacket;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Message;
import br.cefetmg.vitor.udp_broker.models.MessageBody;
import br.cefetmg.vitor.udp_broker.models.MessageBodyPublish;
import br.cefetmg.vitor.udp_broker.models.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.MessageType;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;
import br.cefetmg.vitor.udp_broker.utils.VectorUtils;

public class ReceiveUdpMessage implements IReceiveMessage {

	private IBroker broker;
	private DatagramPacket packet;

	public ReceiveUdpMessage(IBroker broker, DatagramPacket packet) {
		this.broker = broker;
		this.packet = packet;
	}

	@Override
	public void proccessMessage(DatagramPacket packet) {

		Message message = MessageUtils.convertToMessage(packet.getData());

		if (message.getMessageHeader().getMessageType() == MessageType.PUBLISH) {
			proccessPublish(message);
		} else if (message.getMessageHeader().getMessageType() == MessageType.SUBSCRIBE) {
			proccessSubscribe(message, packet);
		} else if (message.getMessageHeader().getMessageType() == MessageType.KEEP_ALIVE) {

		} else if (message.getMessageHeader().getMessageType() == MessageType.UPDATE_PARAM) {

		} else {

		}

	}

	// private void proccessSubscribe(Message message, DatagramPacket packet) {
	// byte[] idBytes = VectorUtils.subvector(message.getMessageBody().getPayload(),
	// 0,
	// Constants.CLIENT_ID_LENGTH);
	//
	// byte[] topicBytes =
	// VectorUtils.subvector(message.getMessageBody().getPayload(),
	// Constants.CLIENT_ID_LENGTH, Constants.MESSAGE_BODY_LENGH);
	//
	// Client client = new Client();
	// client.setAddress(packet.getAddress());
	// client.setId(new String(idBytes));
	// client.setPort(packet.getPort());
	//
	//
	// Topic topic = new Topic();
	// topic.setValue(new String(topicBytes));
	//
	// broker.addClientIntoTopic(client, topic);
	// }

	private void proccessSubscribe(Message message, DatagramPacket packet) {
		// TODO
	}

	private void proccessPublish(Message message) {

		message.convertMessageBodyToPublishMessageBody();
		MessageBodyPublish messagePublish = (MessageBodyPublish) message.getMessageBody();
		
		Topic topicSend = messagePublish.getTopic();
		String messageContentSend = messagePublish.getMessageContent();

		MessageBody messageBody = new MessageBody();
		messageBody.setPayload(messageContentSend.getBytes());
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setAccessToken(broker.getAccessToken());
		messageHeader.setMessageType(MessageType.DATA);

		Message messageSend = new Message();
		messageSend.setMessageHeader(messageHeader);
		messageSend.setMessageBody(messageBody);
		
		broker.sendMessageByTopics(messageSend, topicSend);
	}

	@Override
	public void run() {
		proccessMessage(packet);
	}

}
