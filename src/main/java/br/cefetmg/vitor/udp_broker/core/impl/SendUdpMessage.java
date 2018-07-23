package br.cefetmg.vitor.udp_broker.core.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.SendingStatus;
import br.cefetmg.vitor.udp_broker.models.message.Message;

public class SendUdpMessage implements Runnable {

	private Message message;
	private List<Client> clients;
	private int numberAttempts;
	
	public SendUdpMessage(Message message, List<Client> clients) {
		this.message = message;
		this.clients = clients;
	}
	
	@Override
	public void run() {
		
		try {
			sendMessage(message, clients);
		} catch (IOException e) {
			e.printStackTrace();
			numberAttempts++;
			if (numberAttempts <= Constants.MAXIMUM_ATTEMPTS) {
				this.run();
			}
		}
		
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
		
		System.out.println(client.getAddress().toString());
		socket.send(packet);
	}
}
