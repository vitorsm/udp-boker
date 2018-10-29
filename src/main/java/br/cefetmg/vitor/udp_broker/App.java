package br.cefetmg.vitor.udp_broker;

import java.io.IOException;
import java.net.SocketException;

import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.IJoinning;
import br.cefetmg.vitor.udp_broker.core.ISecurity;
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
	public static void main(String[] args) throws InvalidAddressException {
		
		IBroker broker = new Broker(null, null);
		IJoinning joinning = new JoinningTest(broker);
		broker.setJoinning(joinning);
		
		try {
			Server server = new Server(broker);
			server.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
