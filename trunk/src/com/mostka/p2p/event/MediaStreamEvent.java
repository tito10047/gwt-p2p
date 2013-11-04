package com.mostka.p2p.event;

import com.mostka.p2p.core.face.MediaStream;
import com.mostka.p2p.js.JsEvent;

public class MediaStreamEvent extends JsEvent{
	protected MediaStreamEvent() {}
	
	public final native MediaStream getStream()/*-{
		return this.stream;
	}-*/;
}
