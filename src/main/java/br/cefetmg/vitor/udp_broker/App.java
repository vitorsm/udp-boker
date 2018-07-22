package br.cefetmg.vitor.udp_broker;

import java.io.IOException;
import java.net.SocketException;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.impl.Broker;
import br.cefetmg.vitor.udp_broker.core.impl.Server;
import br.cefetmg.vitor.udp_broker.exceptions.InvalidAddressException;
import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, InvalidAddressException {
		
		IBroker broker = new Broker(null);
		
		Client client = new Client();
		client.setAddress("192.168.0.4");
		client.setId("ID_TEST");
		client.setPort(Constants.CLIENT_PORT);
		
		Topic topic = new Topic();
		topic.setValue("TEST");
		
		broker.addClientIntoTopic(client, topic);
		
		try {
			Server server = new Server(broker);
			server.run();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
