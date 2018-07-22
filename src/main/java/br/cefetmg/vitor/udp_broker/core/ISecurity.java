package br.cefetmg.vitor.udp_broker.core;

import br.cefetmg.vitor.udp_broker.models.Client;

public interface ISecurity {

	public Client getClientByToken(String token);
	public boolean hasPublishPermission(String token, String topic);
	public boolean hasSubscribePermission(String token, String topic);
	public boolean hasKeepAlivePermission(String token);
	
}
