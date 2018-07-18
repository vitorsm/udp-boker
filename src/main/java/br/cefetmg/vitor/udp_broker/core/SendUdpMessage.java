package br.cefetmg.vitor.udp_broker.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Message;
import br.cefetmg.vitor.udp_broker.models.SendingStatus;

public class SendUdpMessage implements ISendMessage {

	private Message message;
	private List<Client> clients;
	private SendingStatus sendingStatus;
	
	public SendUdpMessage(Message message, List<Client> clients) {
		this.message = message;
		this.clients = clients;
	}
	
	@Override
	public void run() {
		
		try {
			sendMessage(message, clients);
			sendingStatus = new SendingStatus(SendingStatus.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			sendingStatus = new SendingStatus(SendingStatus.ERROR, e.getMessage());
		}
		
	}

	@Override
	public SendingStatus getSendingStatus() {
		return sendingStatus;
	}
	
	private void sendMessage(Message message, List<Client> clients) throws IOException {
		
		if (clients != null) {
			for (Client client : clients) {
				sendMessage(message, client);
			}
		}
		
	}
	
	private void sendMessage(Message message, Client client) throws IOException {
		
		byte[] messageBytes = message.getBytes();
		
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, client.getAddress(), client.getPort());
		
		socket.send(packet);
	}
}
