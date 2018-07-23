package br.cefetmg.vitor.udp_broker.core.impl;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.ISecurity;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.ClientSubscription;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;

public class Broker implements IBroker {

	private List<ClientSubscription> clientsSubscription;
	private ISecurity security;
	private IJoinning joinning;
	
	public Broker(ISecurity security, IJoinning joinning) {
		this.security = security;
		this.joinning = joinning;
		this.clientsSubscription = new ArrayList<ClientSubscription>();
	}

	@Override
	public ISecurity getSecurity() {
		return this.security;
	}
	
	@Override
	public IJoinning getJoinning() {
		return this.joinning;
	}
	
	@Override
	public void receiveMessage(DatagramPacket packet) {
		ReceiveUdpMessage receiveMessage = new ReceiveUdpMessage(this, packet);

		Thread thread = new Thread(receiveMessage);
		thread.start();
	}

	@Override
	public void sendMessage(Message message, List<Client> clients) {
		SendUdpMessage sendMessage = new SendUdpMessage(message, clients);

		Thread thread = new Thread(sendMessage);
		thread.start();
	}

	@Override
	public void sendMessageByTopics(Message message, Topic topic) {

		sendMessage(message, getClientsByTopic(topic));
	}

	@Override
	public void addClientIntoTopic(Client client, Topic topic) {

		if (client == null || topic == null)
			return;
		
		ClientSubscription clientSubscription = ClientSubscription.getClientSubscriptionByClient(client,
				clientsSubscription);

		if (clientSubscription == null) {
			clientSubscription = ClientSubscription.instance(client);
			clientsSubscription.add(clientSubscription);
		}

		clientSubscription.addTopic(topic);
	}

	@Override
	public void setKeepAlive(Client client) {
		
		ClientSubscription clientSubscription = ClientSubscription.getClientSubscriptionByClient(client, clientsSubscription);
		clientSubscription.getClient().setLastTimeAnswer(System.currentTimeMillis());
		
	}
	
	@Override
	public List<Client> getClients() {
		if (clientsSubscription == null)
			return null;

		List<Client> clients = new ArrayList<Client>();
		
		for (ClientSubscription clientSubscription : clientsSubscription) {
			clients.add(clientSubscription.getClient());
		}
		
		return clients;
	}

	@Override
	public void removeClient(Client client) {
		if (clientsSubscription == null)
			return;
		
		ClientSubscription clientSubscription = ClientSubscription.getClientSubscriptionByClient(client, clientsSubscription);
		
		if (clientSubscription != null)
			clientsSubscription.remove(clientSubscription);
	}
	
	@Override
	public void sendParamMessage(Client client, Message message) {
		if (client == null || message == null)
			return;
		
		List<Client> clients = new ArrayList<Client>();
		clients.add(client);
		
		SendUdpMessage sendUdpMessage = new SendUdpMessage(message, clients);
		
		Thread thread = new Thread(sendUdpMessage);
		thread.start();
	}
	
	@Override
	public void setJoinning(IJoinning joinning) {
		this.joinning = joinning;
	}
	
	private List<Client> getClientsByTopic(Topic topic) {

		if (clientsSubscription == null)
			return null;

		List<Client> clients = new ArrayList<Client>();

		for (ClientSubscription clientSubscription : this.clientsSubscription) {

			if (clientSubscription.verifyClientSubscribeTopic(topic))
				clients.add(clientSubscription.getClient());
		}

		return clients;
	}

}
