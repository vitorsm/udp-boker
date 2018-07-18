package br.cefetmg.vitor.udp_broker.core;

public interface ISecurity {

	public boolean hasPublishPermission(String token, String topic);
	public boolean hasSubscribePermission(String token, String topic);
	public boolean hasKeepAlivePermission(String token);
}
