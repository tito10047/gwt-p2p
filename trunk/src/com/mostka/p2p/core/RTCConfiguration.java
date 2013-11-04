package com.mostka.p2p.core;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class RTCConfiguration extends JavaScriptObject{
	
	protected RTCConfiguration() {}
	
	/***
	 * An array containing STUN and TURN servers available to be used by ICE.
	 */
	public final native JsArray<RTCIceServer> getIceServers()/*-{
		return this.iceServers;
	}-*/;
}
