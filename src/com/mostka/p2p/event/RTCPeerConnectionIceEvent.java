package com.mostka.p2p.event;

import com.mostka.p2p.core.RTCIceCandidateInit;
import com.mostka.p2p.js.JsEvent;

public class RTCPeerConnectionIceEvent extends JsEvent{

	protected RTCPeerConnectionIceEvent() {}
	public final native RTCIceCandidateInit getCandidate()/*-{
		return this.candidate;
	}-*/;
}
