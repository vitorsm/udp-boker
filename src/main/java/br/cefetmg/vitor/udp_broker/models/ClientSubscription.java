package br.cefetmg.vitor.udp_broker.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ClientSubscription {

	private Client client;
	private List<Topic> topics;
	
	public static ClientSubscription instance(Client client) {
		
		if (client != null)
			return new ClientSubscription(client);
		else
			throw new IllegalArgumentException("The client cannot be null");
	}
	
	private ClientSubscription(Client client) {
		this.client = client;
	}
	
	public static ClientSubscription getClientSubscriptionByClient(Client client, List<ClientSubscription> clientsSubscription) {
		
		if (clientsSubscription == null) {
			return null;
		}
		
		for (ClientSubscription cs : clientsSubscription) {
			if (cs.getClient().equals(client)) {
				return cs;
			}
		}
		
		return null;
	}
	
	public void addTopic(Topic topic) {
		if (topics == null)
			topics = new ArrayList<Topic>();
		
		if (!topics.contains(topic))
			topics.add(topic);
	}
	
	public boolean verifyClientSubscribeTopic(Topic topic) {
		
		if (topics != null) {
			for (Topic t : topics) {
				if (t.equals(topic)) return true;
			}
		}
		
		return false;
	}
}
