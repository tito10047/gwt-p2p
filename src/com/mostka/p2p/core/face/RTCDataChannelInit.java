package com.mostka.p2p.core.face;

public interface RTCDataChannelInit {
	public boolean getOrdered();
	public short getMaxRetransmitTime();
	public short getMaxRetransmits();
	public String getProtocol();
	public boolean getNegotiated();
	public short getId();
}
