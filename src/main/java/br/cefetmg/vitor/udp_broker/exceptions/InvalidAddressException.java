package br.cefetmg.vitor.udp_broker.exceptions;

public class InvalidAddressException extends Exception {

	public InvalidAddressException() {
		super();
	}
	
	public InvalidAddressException(String msg) {
		super(msg);
	}
	
	public InvalidAddressException(String msg, Throwable t) {
		super(msg, t);
	}
}
