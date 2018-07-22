package br.cefetmg.vitor.udp_broker.core;

import java.net.DatagramPacket;
import java.util.List;

import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Message;
import br.cefetmg.vitor.udp_broker.models.Topic;

public interface IBroker {

	public String getAccessToken();
	public void receiveMessage(DatagramPacket packet);
	public void sendMessage(Message message, List<Client> clients);
	
	public void sendMessageByTopics(Message message, Topic topic);
	public void addClientIntoTopic(Client client, Topic topic);
	
}
