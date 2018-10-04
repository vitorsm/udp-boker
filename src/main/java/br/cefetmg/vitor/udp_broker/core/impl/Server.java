package br.cefetmg.vitor.udp_broker.core.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Logger;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.core.IBroker;

public class Server {

	private DatagramSocket socket;
	private boolean running;
	private byte[] buffer;
	
	private IBroker broker;
	
	private static final Logger LOGGER = Logger.getLogger(Server.class.toString());
	
	public Server(IBroker broker) throws SocketException {
		this.broker = broker;
		
		socket = new DatagramSocket(Constants.SERVER_PORT);
		running = true;
		buffer = new byte[Constants.MESSAGE_LENGTH];
	}


	public void run() {
		
		LOGGER.info("BROKER RODANDO");
		while (running) {
			try {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				
				broker.receiveMessage(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("BROKER PARADO");
		
	}

}
