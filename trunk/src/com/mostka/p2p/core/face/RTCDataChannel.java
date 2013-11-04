package com.mostka.p2p.core.face;

import com.google.gwt.core.client.JavaScriptObject;
import com.mostka.p2p.core.RTCDataChannelState;

public class RTCDataChannel extends JavaScriptObject implements RTCDataChannelInit{
	
	protected RTCDataChannel() {}
	
	public final native String   getLabel()/*-{
		return this.label;
	}-*/;
    public final native boolean  getOrdered()/*-{
		return this.ordered;
	}-*/;
    public final native short    getMaxRetransmitTime()/*-{
		return this.maxRetransmitTime;
	}-*/;
    public final native short    getMaxRetransmits()/*-{
		return this.maxRetransmits;
	}-*/;
    public final native String   getProtocol()/*-{
		return this.protocol;
	}-*/;
    public final native boolean  getNegotiated()/*-{
		return this.negotiated;
	}-*/;
    public final native short    getId()/*-{
    	return this.id;
    }-*/;
    public final native RTCDataChannelState getReadyState()/*-{
		return @com.mostka.p2p.core.RTCDataChannelState::getState(Ljava/lang/String;)(this.readyState);
	}-*/;
    public final native int getBufferedAmount()/*-{
		return this.bufferedAmount;
	}-*/;
    
    /**
     * onopen,onerror,onclose,onmessage
     */
    public final native void setHandler(RTCDataChannelHandler handler)/*-{
	    this.onopen = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCDataChannelHandler::onOpen(Lcom/mostka/p2p/js/JsEvent;)(event)
	    };
	    this.onerror = function(event){
	    	console.error(error);
	    	handler.@com.mostka.p2p.core.face.RTCDataChannelHandler::onError(Lcom/mostka/p2p/js/JsErrorEvent;)(event)
	    };
	    this.onclose = function(event){
	    	console.error(event);
	    	handler.@com.mostka.p2p.core.face.RTCDataChannelHandler::onClose(Lcom/mostka/p2p/js/JsEvent;)(event)
	    };
	    this.onmessage = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCDataChannelHandler::onMessage(Lcom/mostka/p2p/event/MessageEvent;)(event)
	    };
	}-*/;
    public final native String getBinaryType()/*-{
		return this.binaryType;
	}-*/;
    public final native void	  setBinaryType(String type)/*-{
		this.binaryType = type;
	}-*/;
    
    public final native void close ()/*-{
		this.close();
	}-*/;
    public final native void send (String data)/*-{
    	console.trace(data);
    	console.trace(this);
    	try{
			this.send("test");
    	}catch(e){
    		console.error(e);
    	}
    	console.log("-----");
	}-*/;
    public final native void send (JavaScriptObject /*Blob*/ data)/*-{
		this.send(data);
	}-*/;
    //TODO:
    //public void send (JavaScriptObject /*ArrayBuffer*/ data);
    //public void send (JavaScriptObject /*ArrayBufferView*/ data);
}
