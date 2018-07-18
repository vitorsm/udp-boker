package br.cefetmg.vitor.udp_broker.core;

import java.net.DatagramPacket;

public interface IReceiveMessage extends Runnable {

	public void proccessMessage(DatagramPacket packet);

}
