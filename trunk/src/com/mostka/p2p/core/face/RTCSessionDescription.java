package com.mostka.p2p.core.face;

import com.google.gwt.core.client.JavaScriptObject;

public class RTCSessionDescription extends JavaScriptObject{
	
	protected RTCSessionDescription(){};
	
	public final native RTCSdpType getType() /*-{
		console.log('RTCSessionDescription.getType',this.type);
		return this.type;
	}-*/;
	
	public final native String getSdp()/*-{
		return this.sdp;
	}-*/;
}
