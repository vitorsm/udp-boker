package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class Client {

	private String id;
	private String address;
	private int port;
	
}
