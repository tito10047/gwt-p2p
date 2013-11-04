package com.mostka.p2p.event;

import com.mostka.p2p.js.JsEvent;

public class MessageEvent extends JsEvent{
	protected MessageEvent() {}
	
	/**
	 * TODO: I can't find this in w3c specification but is exist
	 */
	public final native String getData()/*-{
		return this.data;
	}-*/;
	
}
