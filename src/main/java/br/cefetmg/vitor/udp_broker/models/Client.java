package br.cefetmg.vitor.udp_broker.models;

import java.net.InetAddress;
import java.net.UnknownHostException;

import br.cefetmg.vitor.udp_broker.exceptions.InvalidAddressException;
import lombok.Data;

@Data
public class Client {

	private String id;
//	private String address;
	private InetAddress address;
	private int port;
	private long lastTimeAnswer;
	
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	public void setAddress(String address) throws InvalidAddressException, UnknownHostException {
		
		String[] sAddress = address.split(".");
		byte[] bytes = new byte[4];
		
		if (sAddress.length == 4) {
			for (int i = 0; i < sAddress.length; i++) {
				int number = Integer.parseInt(sAddress[i]);
				bytes[i] = (byte) (number - 128);
			}
		}
		
		this.address = InetAddress.getByAddress(bytes);
	}
}
