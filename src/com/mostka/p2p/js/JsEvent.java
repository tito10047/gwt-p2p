package com.mostka.p2p.js;

import com.google.gwt.core.client.JavaScriptObject;

public class JsEvent extends JavaScriptObject {
	
	protected JsEvent() {}

	public final native boolean getBubbles() /*-{
		return this.bubbles;
	}-*/;

	public final native boolean getDefaultPrevented() /*-{
		return this.defaultPrevented;
	}-*/;

	public final native int getEventPhase() /*-{
		return this.eventPhase;
	}-*/;
	
	public final native boolean getCancelBubble() /*-{
		return this.cancelBubble;
	}-*/;

	public final native boolean getCancelable() /*-{
		return this.cancelable;
	}-*/;
	
	public final native int getTimeStamp() /*-{
		return this.timeStamp;
	}-*/;

	public final native String getType() /*-{
		return this.type;
	}-*/;

	public final native void preventDefault() /*-{
		return this.preventDefault();
	}-*/;

	public final native void stopImmediatePropagation() /*-{
		return this.stopImmediatePropagation();
	}-*/;

	public final native void stopPropagation() /*-{
		return this.stopPropagation();
	}-*/;

	public final native boolean getReturnValue() /*-{
		return this.returnValue;
	}-*/;
}
