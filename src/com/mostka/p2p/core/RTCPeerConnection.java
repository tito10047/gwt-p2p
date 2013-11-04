package com.mostka.p2p.core;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;
import com.mostka.p2p.core.face.MediaConstraints;
import com.mostka.p2p.core.face.MediaStream;
import com.mostka.p2p.core.face.RTCDataChannel;
import com.mostka.p2p.core.face.RTCDataChannelInit;
import com.mostka.p2p.core.face.RTCIceConnectionState;
import com.mostka.p2p.core.face.RTCIceGatheringState;
import com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback;
import com.mostka.p2p.core.face.RTCPeerConnectionHandler;
import com.mostka.p2p.core.face.RTCSessionDescription;
import com.mostka.p2p.core.face.RTCSessionDescriptionCallback;
import com.mostka.p2p.core.face.RTCSignalingState;
import com.mostka.p2p.js.VoidFunction;

public class RTCPeerConnection extends JavaScriptObject implements com.mostka.p2p.core.face.RTCPeerConnection {

	protected RTCPeerConnection(){};
	
	public static native RTCPeerConnection createIfSupported(RTCConfiguration configuration, MediaConstraints constrainrs)/*-{
		if (navigator.mozGetUserMedia) {
			console.log("This appears to be Mozila");
			return new mozRTCPeerConnection(configuration, constrainrs);
		}else if (navigator.webkitGetUserMedia) {
			console.log("This appears to be Chrome");
			return new webkitRTCPeerConnection(configuration, {optional: [
                {RtpDataChannels: true}
            ]});
		}else{
  			console.log("Browser does not appear to be WebRTC-capable");
			return null;
		}
	}-*/;

	public static native boolean isSuported() /*-{
		return (navigator.mozGetUserMedia || navigator.webkitGetUserMedia?true:false);
	}-*/;

	public static native boolean isSCTPSuported() /*-{
		var version = parseInt(navigator.userAgent.match(/Chrom(e|ium)\/([0-9]+)\./)[2], 10);
		console.log("VERSION :"+version);
		return (navigator.webkitGetUserMedia && version>=31);
	}-*/;
	
	@Override
	public final native void createOffer(RTCSessionDescriptionCallback successCallback,
			RTCPeerConnectionErrorCallback failureCallback) /*-{
		this.createOffer(function(sdp){
			successCallback.@com.mostka.p2p.core.face.RTCSessionDescriptionCallback::onRun(Lcom/mostka/p2p/core/face/RTCSessionDescription;)(sdp);
		},function(error){
			console.error(error);
			failureCallback.@com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback::onRun(Lcom/mostka/p2p/js/JsErrorEvent;)(error);
		});
	}-*/;

	@Override
	public final native void createOffer(RTCSessionDescriptionCallback successCallback,
			RTCPeerConnectionErrorCallback failureCallback,
			MediaConstraints constraints) /*-{
		console.error('RTCPeerConnection.createOffer(successCallback,failureCallback,constraints) Not implemented yet');
	}-*/;

	@Override
	public final native void createAnswer(RTCSessionDescriptionCallback successCallback,
			RTCPeerConnectionErrorCallback failureCallback) /*-{
		this.createAnswer(function(sdp){
			successCallback.@com.mostka.p2p.core.face.RTCSessionDescriptionCallback::onRun(Lcom/mostka/p2p/core/face/RTCSessionDescription;)(sdp);
		},failureCallback==null?null:function(error){
			failureCallback.@com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback::onRun(Lcom/mostka/p2p/js/JsErrorEvent;)(error);
		});
	}-*/;

	@Override
	public final native void createAnswer(RTCSessionDescriptionCallback successCallback,
			RTCPeerConnectionErrorCallback failureCallback,
			MediaConstraints constraints) /*-{
		console.error('RTCPeerConnection.createAnswer(successCallback,failureCallback,constraints) Not implemented yet');
	}-*/;

	@Override
	public final native void setLocalDescription(RTCSessionDescription description,
			VoidFunction successCallback,
			RTCPeerConnectionErrorCallback failureCallback) /*-{
		this.setLocalDescription(description,
		successCallback == null?null:function(sdp){
			successCallback.@com.mostka.p2p.core.face.RTCSessionDescriptionCallback::onRun(Lcom/mostka/p2p/core/face/RTCSessionDescription;)(sdp);
		},failureCallback==null?null:function(error){
			failureCallback.@com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback::onRun(Lcom/mostka/p2p/js/JsErrorEvent;)(error);
		});
	}-*/;

	@Override
	public final native RTCSessionDescription getLocalDescription() /*-{
		return this.localDescription;
	}-*/;

	@Override
	public final native void setRemoteDescription(RTCSessionDescription description,
			VoidFunction successCallback,
			RTCPeerConnectionErrorCallback failureCallback) /*-{
		this.setRemoteDescription(description,successCallback==null?null:function(){
			successCallback.@com.mostka.p2p.js.VoidFunction::onRun()();
		},failureCallback==null?null:function(error){
			failureCallback.@com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback::onRun(Lcom/mostka/p2p/js/JsErrorEvent;)(error);
		});
	}-*/;
	@Override
	public final native RTCSessionDescription getRemoteDescription() /*-{
		return this.remoteDescription;
	}-*/;

	@Override
	public final native RTCSignalingState getSignalingState() /*-{
		return this.signalingState;
	}-*/;
	
	@Override
	public final native void updateIce() /*-{
		this.updateIce();
	}-*/;

	@Override
	public final native void updateIce(RTCConfiguration configuration) /*-{
		this.updateIce(configuration);
	}-*/;

	@Override
	public final native void updateIce(RTCConfiguration configuration,
			MediaConstraints constraints) /*-{
		this.updateIce(configuration);
	}-*/;

	@Override
	public final native void addIceCandidate(RTCIceCandidateInit candidate,
			VoidFunction successCallback,
			RTCPeerConnectionErrorCallback failureCallback) /*-{
		this.addIceCandidate(candidate,successCallback==null?undefined:function(){
			successCallback.@com.mostka.p2p.js.VoidFunction::onRun()();
		},failureCallback==null?undefined:function(error){
			failureCallback.@com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback::onRun(Lcom/mostka/p2p/js/JsErrorEvent;)(error);
		});
	}-*/;

	@Override
	public final native RTCIceGatheringState getIceGatheringState() /*-{
		return this.iceGatheringState;;
	}-*/;

	@Override
	public final native RTCIceConnectionState getIceConnetcionState() /*-{
		return this.iceConnectionState;;
	}-*/;

	@Override
	public final native ArrayList<MediaStream> getLocalStrams() /*-{
		console.error('RTCPeerConnection.getLocalStrams() NOT implemented yet');
		return null;
	}-*/;

	@Override
	public final native ArrayList<MediaStream> getRemoteStreams() /*-{
		console.error('RTCPeerConnection.getRemoteStreams() NOT implemented yet');
		return null;
	}-*/;

	@Override
	public final native MediaStream getStreamById(String streamId) /*-{
		console.error('RTCPeerConnection.getStreamById(streamId) NOT implemented yet');
		return null;
	}-*/;

	@Override
	public final native void addStream(MediaStream stream) /*-{
		console.error('RTCPeerConnection.addStream(stream) NOT implemented yet');
	}-*/;

	@Override
	public final native void addStream(MediaStream stream, MediaConstraints constrain) /*-{
		console.error('RTCPeerConnection.addStream(stream,constrain) NOT implemented yet');
	}-*/;

	@Override
	public final native void removeStream(MediaStream stream) /*-{
		console.error('RTCPeerConnection.removeStream(stream) NOT implemented yet');
	}-*/;

	@Override
	public final native void close() /*-{
		this.close();
	}-*/;

	@Override
	public final native void addHandler(RTCPeerConnectionHandler handler) /*-{
		
	    this.onnegotiationneeded = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onNegoriarionNeeded(Lcom/mostka/p2p/js/JsEvent;)(event);
	    };
        this.onicecandidate = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onIceCandidate(Lcom/mostka/p2p/event/RTCPeerConnectionIceEvent;)(event);
	    };
        this.onsignalingstatechange = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onSignalingStateChange(Lcom/mostka/p2p/js/JsEvent;)(event);
	    };
        this.onaddstream = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onAddStream(Lcom/mostka/p2p/event/MediaStreamEvent;)(event);
	    };
        this.onremovestream = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onRemoveStream(Lcom/mostka/p2p/event/MediaStreamEvent;)(event);
	    };
        this.oniceconnectionstatechange = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onIceConnectionStateChange(Lcom/mostka/p2p/js/JsEvent;)(event);
	    };
	    this.ondatachannel = function(event){
	    	handler.@com.mostka.p2p.core.face.RTCPeerConnectionHandler::onDataChannel(Lcom/mostka/p2p/event/RTCDataChannelEvent;)(event);
	    };
	}-*/;

	@Override
	public final native RTCDataChannel createDataChannel(String label,
			RTCDataChannelInit dataChannelDict) /*-{
		try{
			return this.createDataChannel(label, dataChannelDict);
		}catch(e){
			console.error("RTCPeerConnection.createDataChannel",e);
			return null;
		}
	}-*/;

}
