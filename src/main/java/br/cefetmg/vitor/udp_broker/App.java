package br.cefetmg.vitor.udp_broker;

import java.net.SocketException;

import br.cefetmg.vitor.udp_broker.core.Broker;
import br.cefetmg.vitor.udp_broker.core.IBroker;
import br.cefetmg.vitor.udp_broker.core.Server;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		IBroker broker = new Broker("TOKEN");
		
		try {
			Server server = new Server(broker);
			server.run();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
