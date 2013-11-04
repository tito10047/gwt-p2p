package com.mostka.p2p.js;

import com.google.gwt.core.client.JavaScriptObject;

public class MediaConstraints extends JavaScriptObject implements com.mostka.p2p.core.face.MediaConstraints {

	protected MediaConstraints() {}
	

	public static native MediaConstraints create(boolean optionalRtpDataChannels) /*-{
		return {optional: [
                {RtpDataChannels: true}
            ]};
	}-*/;
	
}
