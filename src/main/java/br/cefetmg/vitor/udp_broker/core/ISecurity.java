package br.cefetmg.vitor.udp_broker.core;

import br.cefetmg.vitor.udp_broker.models.Client;
import br.cefetmg.vitor.udp_broker.models.Topic;

public interface ISecurity {

	public String getAccessToken();
//	public Client getClientByToken(String token);
	public boolean hasPublishPermission(String token, Topic topic);
	public boolean hasSubscribePermission(String token, Topic topic);
	public boolean hasKeepAlivePermission(String token);
	public String generateReducedToken(String token);
	
}
