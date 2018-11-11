package br.cefetmg.vitor.udp_broker.models;

import java.net.InetAddress;
import java.net.UnknownHostException;

import br.cefetmg.vitor.udp_broker.Constants;
import br.cefetmg.vitor.udp_broker.exceptions.InvalidAddressException;
import lombok.Data;

@Data
public class Client {

	private String id;
	private InetAddress address;
	private int port;
	private long lastTimeAnswer;
	private String token;
	
	public Client() {
		port = Constants.CLIENT_PORT;
	}
	
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	public void setAddress(String address) throws InvalidAddressException, UnknownHostException {
		this.address = InetAddress.getByName(address);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Client) {
			Client c = (Client) obj;
			
			return c.address != null && this.address != null && c.address.toString().equals(this.address.toString());
		}
		
		return false;
	}
}
