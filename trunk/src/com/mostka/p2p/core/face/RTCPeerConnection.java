package com.mostka.p2p.core.face;

import java.util.ArrayList;

import com.mostka.p2p.core.RTCConfiguration;
import com.mostka.p2p.core.RTCIceCandidateInit;
import com.mostka.p2p.js.VoidFunction;

public interface RTCPeerConnection {
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-createOffer-void-RTCSessionDescriptionCallback-successCallback-RTCPeerConnectionErrorCallback-failureCallback-MediaConstraints-constraints">docs</a>  */
	public void createOffer(RTCSessionDescriptionCallback successCallback, RTCPeerConnectionErrorCallback failureCallback);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-createOffer-void-RTCSessionDescriptionCallback-successCallback-RTCPeerConnectionErrorCallback-failureCallback-MediaConstraints-constraints">docs</a>  */
	public void createOffer(RTCSessionDescriptionCallback successCallback, RTCPeerConnectionErrorCallback failureCallback, MediaConstraints constraints);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-createAnswer-void-RTCSessionDescriptionCallback-successCallback-RTCPeerConnectionErrorCallback-failureCallback-MediaConstraints-constraints">docs</a> */
	public void createAnswer(RTCSessionDescriptionCallback successCallback, RTCPeerConnectionErrorCallback failureCallback);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-createAnswer-void-RTCSessionDescriptionCallback-successCallback-RTCPeerConnectionErrorCallback-failureCallback-MediaConstraints-constraints">docs</a> */
	public void createAnswer(RTCSessionDescriptionCallback successCallback, RTCPeerConnectionErrorCallback failureCallback, MediaConstraints constraints);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-setLocalDescription-void-RTCSessionDescription-description-VoidFunction-successCallback-RTCPeerConnectionErrorCallback-failureCallback">docs</a> */
	public void setLocalDescription(RTCSessionDescription description, VoidFunction successCallback, RTCPeerConnectionErrorCallback failureCallback);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-localDescription//">docs</a> */
	public RTCSessionDescription getLocalDescription();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-setRemoteDescription-void-RTCSessionDescription-description-VoidFunction-successCallback-RTCPeerConnectionErrorCallback-failureCallback">docs</a> */
	public void setRemoteDescription(RTCSessionDescription description, VoidFunction successCallback, RTCPeerConnectionErrorCallback failureCallback);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-remoteDescription">docs</a> */
	public RTCSessionDescription getRemoteDescription();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-signalingState">docs</a> */
	public RTCSignalingState getSignalingState();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-updateIce-void-RTCConfiguration-configuration-MediaConstraints-constraints">docs</a> */
	public void updateIce();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-updateIce-void-RTCConfiguration-configuration-MediaConstraints-constraints">docs</a> */
	public void updateIce(RTCConfiguration configuration);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-updateIce-void-RTCConfiguration-configuration-MediaConstraints-constraints">docs</a> */
	public void updateIce(RTCConfiguration configuration, MediaConstraints constraints);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-addIceCandidate-void-RTCIceCandidate-candidate-VoidFunction-successCallback-RTCPeerConnectionErrorCallback-failureCallback">docs</a> */
	public void addIceCandidate(RTCIceCandidateInit candidate, VoidFunction successCallback, RTCPeerConnectionErrorCallback failureCallback);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-iceGatheringState">docs</a> */
	public RTCIceGatheringState getIceGatheringState();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-iceConnectionState">docs</a> */
	public RTCIceConnectionState getIceConnetcionState();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-getLocalStreams-sequence-MediaStream">docs</a> */
	public ArrayList<MediaStream> getLocalStrams();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-getRemoteStreams-sequence-MediaStream">docs</a> */
	public ArrayList<MediaStream> getRemoteStreams();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-getStreamById-MediaStream-DOMString-streamId">docs</a> */
	public MediaStream getStreamById(String streamId);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-addStream-void-MediaStream-stream-MediaConstraints-constraints">docs</a> */
	public void addStream(MediaStream stream);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-addStream-void-MediaStream-stream-MediaConstraints-constraints">docs</a> */
	public void addStream(MediaStream stream, MediaConstraints constrain);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-removeStream-void-MediaStream-stream">docs</a> */
	public void removeStream(MediaStream stream);
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-close-void">docs</a> */
	public void close();
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-onnegotiationneeded">docs</a> */
	public void addHandler(RTCPeerConnectionHandler handler);
	
	/** @see <a href="http://dev.w3.org/2011/webrtc/editor/webrtc.html#widl-RTCPeerConnection-createDataChannel-RTCDataChannel-DOMString-label-RTCDataChannelInit-dataChannelDict">docs</a> */
	public RTCDataChannel createDataChannel(String label, RTCDataChannelInit dataChannelDict);
}
