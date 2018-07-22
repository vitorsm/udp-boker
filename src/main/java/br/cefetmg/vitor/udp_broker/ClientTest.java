package br.cefetmg.vitor.udp_broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;
import br.cefetmg.vitor.udp_broker.models.message.MessageHeader;
import br.cefetmg.vitor.udp_broker.models.message.MessageType;
import br.cefetmg.vitor.udp_broker.models.message.body.MessageBodyPublish;

public class ClientTest {

	public static void main(String[] args) throws IOException {
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setAccessToken("TOKEN_CLIENT");
		messageHeader.setMessageType(MessageType.PUBLISH);
		
		Topic topic = new Topic();
		topic.setValue("TEST");
		
		MessageBodyPublish messageBody = new MessageBodyPublish();
		messageBody.setMessageContent("Valor teste");
		messageBody.setTopic(topic);
		
		Message message = new Message();
		message.setMessageHeader(messageHeader);
		message.setMessageBody(messageBody);
		
		byte[] bytes = message.getBytes();
		
		InetAddress address = InetAddress.getByName("192.168.0.101");
		
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, Constants.SERVER_PORT);
		
		System.out.println("Vai enviar a mensagem para " + address.toString());
		socket.send(packet);
	}
	
}
