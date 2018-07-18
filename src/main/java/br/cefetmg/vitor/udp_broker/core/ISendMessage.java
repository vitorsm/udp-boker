package br.cefetmg.vitor.udp_broker.core;

import br.cefetmg.vitor.udp_broker.models.SendingStatus;

public interface ISendMessage extends Runnable {
	
	public SendingStatus getSendingStatus();
	
}
