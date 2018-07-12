package br.cefetmg.vitor.udp_broker.models;

import lombok.Data;

@Data
public class Message {
	
	private MessageHeader messageHeader;
	private MessageBody messageBody;
	
}
