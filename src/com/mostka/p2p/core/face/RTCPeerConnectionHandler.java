package com.mostka.p2p.core.face;

import com.mostka.p2p.event.MediaStreamEvent;
import com.mostka.p2p.event.RTCDataChannelEvent;
import com.mostka.p2p.event.RTCPeerConnectionIceEvent;
import com.mostka.p2p.js.JsEvent;

public interface RTCPeerConnectionHandler {
	public void onNegoriarionNeeded(JsEvent event);
	public void onIceCandidate(RTCPeerConnectionIceEvent event);
	public void onSignalingStateChange(JsEvent event);
	public void onAddStream(MediaStreamEvent event);
	public void onRemoveStream(MediaStreamEvent event);
	public void onIceConnectionStateChange(JsEvent event);
	public void onDataChannel(RTCDataChannelEvent event);
}
