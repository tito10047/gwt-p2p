package com.mostka.p2p.core;

import com.google.gwt.core.client.JavaScriptObject;

public class RTCIceCandidateInit extends JavaScriptObject{
	
	protected RTCIceCandidateInit() {}
	
	/**
	 * This carries the candidate-attribute as defined in section 15.1 of [ICE].
	 */
	public final native String getCandidate()/*-{
		return this.candidate;
	}-*/;
	/**
	 * If present, this contains the identifier of the "media stream identification" as defined in [RFC 3388] for the m-line this candidate is associated with.
	 */
	public final native String getSdpMid()/*-{
		return this.sdpMid;
	}-*/;
	/**
	 * This indicates the index (starting at zero) of the m-line in the SDP this candidate is associated with.
	 */
	public final native short getSdpMLineIndex()/*-{
		return this.sdpMLineIndex;
	}-*/;
}
