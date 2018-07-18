package br.cefetmg.vitor.udp_broker.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import br.cefetmg.vitor.udp_broker.Constants;

public class Server {

	private DatagramSocket socket;
	private boolean running;
	private byte[] buffer;
	
	private Broker broker;
	
	public Server(Broker broker) throws SocketException {
		this.broker = broker;
		
		socket = new DatagramSocket(Constants.SERVER_PORT);
		running = true;
		buffer = new byte[Constants.MESSAGE_LENGTH];
	}


	public void run() {
		
		while (running) {
			try {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
