package br.cefetmg.vitor.udp_broker.core;

import java.net.DatagramPacket;
import java.util.List;

import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;
import br.cefetmg.vitor.udp_broker.models.message.Message;

public interface IBroker {

	public ISecurity getSecurity();
	public IJoinning getJoinning();
	
	public void setJoinning(IJoinning joinning);
	public void setSecurity(ISecurity security);
	
	public List<Client> getClients();
	public void removeClient(Client client);
	
	public void setKeepAlive(Client client);
	
	public void receiveMessage(DatagramPacket packet);
	public void sendMessage(Message message, List<Client> clients);
	
	public void sendMessageByTopics(Message message, Topic topic);
	public void addClientIntoTopic(Client client, Topic topic);
	
	public void sendParamMessage(Client client, Message message);
	
	public Client findClientByToken(String token);
}
