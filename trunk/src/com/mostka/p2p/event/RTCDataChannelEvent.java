package com.mostka.p2p.event;

import com.mostka.p2p.core.face.RTCDataChannel;
import com.mostka.p2p.js.JsEvent;

public class RTCDataChannelEvent extends JsEvent{
	
	protected RTCDataChannelEvent() {}
	
	public final native RTCDataChannel getChannel()/*-{
		return this.channel;
	}-*/;
}
