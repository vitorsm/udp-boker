package br.cefetmg.vitor.udp_broker.core;

import br.cefetmg.vitor.udp_broker.core.impl.Credentials;
import br.cefetmg.vitor.udp_broker.models.Client;

public interface IJoinning {
	public void joinning(Client client, Credentials credentials);
}
