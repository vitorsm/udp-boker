package br.cefetmg.vitor.udp_broker.core;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.ClientSubscription;
import br.cefetmg.vitor.udp_broker.models.Message;
import br.cefetmg.vitor.udp_broker.models.Topic;

public class Broker implements IBroker {

	private List<ClientSubscription> clientsSubscription;
	private ISendMessage sendMessage;
	
	
	public void receiveMessage(DatagramPacket packet) {
		
	}
	
	public void sendMessage(Message message, List<Client> clients) {
		ISendMessage sendMessage = new SendUdpMessage(message, clients);
		
		Thread thread = new Thread(sendMessage);
		thread.start();
	}
	
	public void sendMessageByTopics(Message message, Topic topic) {
		
		sendMessage(message, getClientsByTopic(topic));
	}
	
	public void addClientIntoTopic(Client client, Topic topic) {
		
		ClientSubscription clientSubscription = ClientSubscription.
				getClientSubscriptionByClient(client, clientsSubscription);
		
		if (clientSubscription == null) {
			clientSubscription = ClientSubscription.instance(client);
		}
		
		clientSubscription.addTopic(topic);
	}
	
	private List<Client> getClientsByTopic(Topic topic) {
		
		if (clientsSubscription == null) return null;
		
		List<Client> clients = new ArrayList<Client>();
		
		for (ClientSubscription clientSubscription : this.clientsSubscription) {
			
			if (clientSubscription.verifyClientSubscribeTopic(topic))
				clients.add(clientSubscription.getClient());
		}
		
		return clients;
	}
}
