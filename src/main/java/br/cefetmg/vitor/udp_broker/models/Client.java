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
		this.address = InetAddress.getByName(address);
	}
}
