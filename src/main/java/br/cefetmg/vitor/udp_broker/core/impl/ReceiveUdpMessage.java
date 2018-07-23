package br.cefetmg.vitor.udp_broker.core.impl;

import java.net.DatagramPacket;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IReceiveMessage;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBody;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyHello;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyPublish;
import br.cefetmg.vitor.udp_broker.utils.MessageUtils;

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
			proccessKeepAlive(message);
		} else if (message.getMessageHeader().getMessageType() == MessageType.UPDATE_PARAM) {

		} else if (message.getMessageHeader().getMessageType() == MessageType.HELLO) {
			proccessJoin(message, packet);
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

	private void proccessKeepAlive(Message message) {

		Client client = broker.getSecurity().getClientByToken(message.getMessageHeader().getAccessToken());
		broker.setKeepAlive(client);

	}

	private void proccessJoin(Message message, DatagramPacket packet) {
		if (broker.getJoinning() == null)
			return;

		message.convertMessageBodyToHelloMessageBody();

		Client client = new Client();
		client.setAddress(packet.getAddress());

		MessageBodyHello messageBodyHello = (MessageBodyHello) message.getMessageBody();
		broker.getJoinning().joinning(client, messageBodyHello.getCredentials());
	}

	private void proccessSubscribe(Message message, DatagramPacket packet) {
		// TODO
	}

	private void proccessPublish(Message message) {

		message.convertMessageBodyToPublishMessageBody();
		MessageBodyPublish messagePublish = (MessageBodyPublish) message.getMessageBody();

		Topic topicSend = messagePublish.getTopic();
		String messageContentSend = messagePublish.getMessageContent();

		if (broker.getSecurity() != null
				&& !broker.getSecurity().hasPublishPermission(message.getMessageHeader().getAccessToken(), topicSend))
			return;

		MessageBody messageBody = new MessageBody();
		messageBody.setPayload(messageContentSend.getBytes());

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setMessageType(MessageType.DATA);
		if (broker.getSecurity() != null)
			messageHeader.setAccessToken(broker.getSecurity().getAccessToken());
		else
			messageHeader.setAccessToken("");

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