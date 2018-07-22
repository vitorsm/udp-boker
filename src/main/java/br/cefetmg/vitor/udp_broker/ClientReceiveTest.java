package br.cefetmg.vitor.udp_broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientReceiveTest {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket socket = new DatagramSocket(Constants.CLIENT_PORT);
		byte[] buffer = new byte[Constants.MESSAGE_LENGTH];
		
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		System.out.println("recebeu algo");
		
		String text = new String(packet.getData());
		
		System.out.println("recebeu isso " + text);
	}
}
