package br.cefetmg.vitor.udp_broker;

public class Constants {

	
	public final static int EMPTY_CHAR = 127;
	/***
	 * Server constants
	 */
	
	public final static int SERVER_PORT = 4410;
	public final static int SERVER_PORT_SEND = 4409;
	
	/***
	 * Server constants
	 */
	
	public final static int CLIENT_PORT = 4411;
//	public final static int CLIENT_PORT = 4421;
	
	
	public final static int CLIENT_ID_LENGTH = 10;
	
	/***
	 * Message configuration constants
	 */
	
	public final static int MESSAGE_TYPE_LENGTH = 1;
	public final static int MESSAGE_TOKEN_LENGTH = 20;
	public final static int MESSAGE_HEADER_LENGTH = MESSAGE_TYPE_LENGTH + MESSAGE_TOKEN_LENGTH;
	public final static int MESSAGE_BODY_LENGTH = 30;
	
	
	public final static int MESSAGE_ID_LENGTH = 10;
	public final static int MESSAGE_PASSWORD_LENGTH = 10;
	
	public final static int MESSAGE_TOPIC_LENGTH = 10;
	public final static int MESSAGE_BODY_CONTENT_LENGTH = MESSAGE_BODY_LENGTH - MESSAGE_TOPIC_LENGTH;
	
	public final static int MESSAGE_LENGTH = MESSAGE_HEADER_LENGTH + MESSAGE_BODY_LENGTH;
	
	public final static int MESSAGE_ERROR_NAME_LENGTH = 10;
	public final static int MESSAGE_ERROR_DETAILS_LENGTH = MESSAGE_BODY_LENGTH - MESSAGE_ERROR_NAME_LENGTH; 
	
	public final static int MAXIMUM_ATTEMPTS = 5;
	
	public final static int MESSAGE_K_LENGTH = 5;
	public final static int MESSAGE_SAMPLE_LENGTH = 5;
	public final static int MESSAGE_INPUT_ID_LENGTH = 3;
	public final static int MESSAGE_VALUE_LENGTH = 5;
	
	public final static int PINS_AMOUNT = 3;
	public final static int PIN_1 = 1;
	public final static int PIN_2 = 2;
	public final static int PIN_3 = 3;
	public final static int PIN_4 = 4;
	public final static int PIN_5 = 5;
}
