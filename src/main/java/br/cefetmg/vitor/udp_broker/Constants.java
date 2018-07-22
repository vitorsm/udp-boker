package br.cefetmg.vitor.udp_broker;

public class Constants {

	
	/***
	 * Server constants
	 */
	
	public final static int SERVER_PORT = 4410;
	
	
	public final static int CLIENT_ID_LENGTH = 10;
	
	/***
	 * Message configuration constants
	 */
	public final static int MESSAGE_TYPE_LENGTH = 1;
	public final static int MESSAGE_TOKEN_LENGTH = 20;
	public final static int MESSAGE_BODY_LENGTH = 20;
	public final static int MESSAGE_TOPIC_LENGTH = 10;
	public final static int MESSAGE_VALUE_LENGTH = MESSAGE_BODY_LENGTH - MESSAGE_TOPIC_LENGTH;
	public final static int MESSAGE_LENGTH = MESSAGE_TYPE_LENGTH + MESSAGE_TOKEN_LENGTH + MESSAGE_BODY_LENGTH;
	
	
	public final static int MAXIMUM_ATTEMPTS = 5;
}
