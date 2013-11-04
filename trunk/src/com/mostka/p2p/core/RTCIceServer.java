package com.mostka.p2p.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class RTCIceServer extends JavaScriptObject{
	
	protected RTCIceServer() {}
	
	/**
	 * STUN or TURN URI(s) as defined in [STUN-URI] and [TURN-URI].
	 */
	public final native JsArrayString getUrls()/*-{
		return this.urls;
	}-*/;
	/**
	 * If this RTCIceServer object represents a TURN server, then this attribute specifies the username to use with that TURN server.
	 */
	public final native String getUserName() /*-{
		return this.username;
	}-*/;
	/**
	 * If this RTCIceServer object represents a TURN server, then this attribute specifies the credential to use with that TURN server.
	 */
	public final native String getCredential()/*-{
		return this.credential;
	}-*/;
}
