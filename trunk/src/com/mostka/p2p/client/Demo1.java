package com.mostka.p2p.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mostka.p2p.core.RTCConfiguration;
import com.mostka.p2p.core.RTCDataChannelState;
import com.mostka.p2p.core.RTCPeerConnection;
import com.mostka.p2p.core.face.RTCDataChannel;
import com.mostka.p2p.core.face.RTCDataChannelHandler;
import com.mostka.p2p.core.face.RTCDataChannelInit;
import com.mostka.p2p.core.face.RTCPeerConnectionErrorCallback;
import com.mostka.p2p.core.face.RTCPeerConnectionHandler;
import com.mostka.p2p.core.face.RTCSessionDescription;
import com.mostka.p2p.core.face.RTCSessionDescriptionCallback;
import com.mostka.p2p.event.MediaStreamEvent;
import com.mostka.p2p.event.MessageEvent;
import com.mostka.p2p.event.RTCDataChannelEvent;
import com.mostka.p2p.event.RTCPeerConnectionIceEvent;
import com.mostka.p2p.js.JsErrorEvent;
import com.mostka.p2p.js.JsEvent;
import com.mostka.p2p.js.MediaConstraints;
import com.mostka.p2p.js.VoidFunction;

public class Demo1 {
	
	private RTCPeerConnection pc1;
	private RTCPeerConnection pc2;
	private RTCDataChannel sendChannel;
	private RTCDataChannel receiveChannel;
	private MediaConstraints pcConstraint;
	private RTCDataChannelInit dataConstraint;
	private Button startButton;
	private Button sendButton;
	private Button closeButton;
	private TextArea dataChannelSend;
	private TextArea dataChannelReceive;
	private RadioButton useSctp;
	private RadioButton useRtp;

	public void disableSendButton(){
		this.sendButton.setEnabled(false);
	}
	public void enableStartButton(){
		this.startButton.setEnabled(true);
	}
	ClickHandler useTypeHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			dataChannelReceive.setText("");
			dataChannelSend.setText("");
			disableSendButton();
			enableStartButton();
		}
	};
	
	public Demo1() {
		VerticalPanel verticalPanel = new VerticalPanel();
		dataChannelSend = new TextArea();
		dataChannelReceive = new TextArea();
		dataChannelReceive.setEnabled(false);
		useSctp = new RadioButton("useSctp", "Use SCTP");
		useSctp.setValue(true);
		useSctp.setName("transportbtn");
		useRtp = new RadioButton("useRtp", "Use RTP");
		useRtp.setName("transportbtn");
		startButton = new Button("Start");
		sendButton = new Button("SendData");
		closeButton = new Button("Stop");
		sendButton.setEnabled(false);
		closeButton.setEnabled(false);
		verticalPanel.add(dataChannelSend);
		verticalPanel.add(dataChannelReceive);
		verticalPanel.add(useSctp);
		verticalPanel.add(useRtp);
		verticalPanel.add(startButton);
		verticalPanel.add(sendButton);
		verticalPanel.add(closeButton);
		RootPanel.get().add(verticalPanel);
		
		final Demo1 self = this;
		
		startButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				self.createConnection();
			}
		});
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				self.sendData();
			}
		});
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				self.closeDataChannels();
			}
		});
		/*useSctp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				self.enableStartButton();
			}
		});
		useRtp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				self.enableStartButton();
			}
		});*/
		
		
		useRtp.addClickHandler(useTypeHandler);
		useSctp.addClickHandler(useTypeHandler);
	}
	

	public void createConnection(){
		dataChannelSend.getElement().setAttribute("placeholder", "");
		RTCConfiguration servers = null;
		pcConstraint = null;
		dataConstraint = null;
		if (useSctp.getValue() && RTCPeerConnection.isSCTPSuported()){
            // SCTP is supported from Chrome M31.
            // No need to pass DTLS constraint as it is on by default in Chrome M31.
            // For SCTP, reliable and ordered is true by default.
			Gwt_p2p.log("Using SCTP based Data Channels");
		}else{
			pcConstraint = MediaConstraints.create(true);
			if (!useRtp.getValue()){
				Gwt_p2p.log("Using RTP based Data Channels,as you are on an older version than M31.");
				Window.alert("Reverting to RTP based data channels as you are on an older version than M31.");
				useRtp.setValue(true);
			}
		}
		pc1 = RTCPeerConnection.createIfSupported(servers, pcConstraint);
		if (pc1==null){
			Window.alert("RTCP is not suported. use new chrome or firefox");
			return;
		}
		Gwt_p2p.log("Created local peer connection object pc1");
		
		sendChannel = pc1.createDataChannel("sendDataChannel2", dataConstraint);
		if (sendChannel==null){
			Window.alert("Failed to create data channel. You need Chrome M25 or later with --enable-data-channels flag");
			Gwt_p2p.log("Create Data channel failed with exception: see console");
			return;
		}
		Gwt_p2p.log("Created send data channel");
		pc1.addHandler(pc1ConnectionHandler);
		sendChannel.setHandler(sendChannelHandler);
		logJson("pc1", pc1);
		logJson("sendChannel", sendChannel);
		
		pc2 = RTCPeerConnection.createIfSupported(servers, pcConstraint);
		Gwt_p2p.log("Created remote peer connection object pc2");
		pc2.addHandler(pc2ConnectionHandler);
		logJson("pc2", pc2);
		
		
		pc1.createOffer(gotDescription,gotDescriptionFailure);
		startButton.setEnabled(false);
		closeButton.setEnabled(true);
		
		test("pc1", pc1);
		test("pc2", pc2);
		test("sendChannel",sendChannel);
		
	}
	public void sendData(){
		String data = dataChannelSend.getText();
		sendChannel.send(data);
		Gwt_p2p.log("Sent Data: " + data);
	}
	public void closeDataChannels(){
		Gwt_p2p.log("Closing data Channels");
		sendChannel.close();
		Gwt_p2p.log("Closed data channel with label: " + sendChannel.getLabel());
		receiveChannel.close();
		Gwt_p2p.log("Closed data channel with label: " + receiveChannel.getLabel());
		pc1.close();
		pc2.close();
		pc1=null;
		pc2=null;
		Gwt_p2p.log("Closed peer connections");
		startButton.setEnabled(true);
		sendButton.setEnabled(false);
		closeButton.setEnabled(false);
		dataChannelSend.setText("");
		dataChannelReceive.setText("");
		dataChannelSend.setEnabled(false);
	}
	
	RTCSessionDescriptionCallback gotDescription = new RTCSessionDescriptionCallback() {
		public void onRun(RTCSessionDescription desc) {
			if (pc1==null){
				Gwt_p2p.log("pc1==null");
			}
			if (pc2==null){
				Gwt_p2p.log("pc2==null");
			}
			pc1.setLocalDescription(desc, null, null);
			Gwt_p2p.log("Offer from pc1 \n" + desc.getSdp());
			Gwt_p2p.log("",desc);
			pc2.setRemoteDescription(desc, null, null);
			pc2.createAnswer(gotDescription2, null);
		}
	};
	RTCSessionDescriptionCallback gotDescription2 = new RTCSessionDescriptionCallback() {
		public void onRun(RTCSessionDescription desc) {
			if (pc1==null){
				Gwt_p2p.log("pc1==null");
			}
			if (pc2==null){
				Gwt_p2p.log("pc2==null");
			}
			pc2.setLocalDescription(desc, null, null);
			Gwt_p2p.log("Answer from pc2 \n" + desc.getSdp());
			Gwt_p2p.log("",desc);
			pc1.setRemoteDescription(desc, null, null);
		}
	};
	
	
	RTCPeerConnectionHandler pc1ConnectionHandler = new RTCPeerConnectionHandler() {
		public void onSignalingStateChange(JsEvent event) {
		}
		public void onRemoveStream(MediaStreamEvent event) {
		}
		public void onNegoriarionNeeded(JsEvent event) {
		}
		public void onIceConnectionStateChange(JsEvent event) {
		}
		public void onIceCandidate(RTCPeerConnectionIceEvent event) {
			// iceCallback1
			Gwt_p2p.log("local ice callback");
			Gwt_p2p.log("",event);
			if (event.getCandidate()!=null){
				pc2.addIceCandidate(event.getCandidate(), new VoidFunction() {
					public void onRun() {
						Gwt_p2p.log("Local ICE candidate SUCCESS");
					}
				}, new RTCPeerConnectionErrorCallback() {
					public void onRun(JsErrorEvent error) {
						Gwt_p2p.log("Local ICE candidate FAILED",error);
					}
				});
				Gwt_p2p.log("Local ICE candidate: \n" + event.getCandidate().getCandidate());
				
			}
		}
		public void onAddStream(MediaStreamEvent event) {
		}
		public void onDataChannel(RTCDataChannelEvent event) {
		}
	};
	
	RTCPeerConnectionHandler pc2ConnectionHandler = new RTCPeerConnectionHandler() {
		public void onSignalingStateChange(JsEvent event) {
		}
		public void onRemoveStream(MediaStreamEvent event) {
		}
		public void onNegoriarionNeeded(JsEvent event) {
		}
		public void onIceConnectionStateChange(JsEvent event) {
		}
		public void onIceCandidate(RTCPeerConnectionIceEvent event) {
			// iceCallback2
			Gwt_p2p.log("remote ice callback");
			if (event.getCandidate()!=null){
				pc1.addIceCandidate(event.getCandidate(), new VoidFunction() {
					public void onRun() {
						Gwt_p2p.log("Remote ICE candidate SUCCESS");
					}
				}, new RTCPeerConnectionErrorCallback() {
					public void onRun(JsErrorEvent error) {
						Gwt_p2p.log("Remote ICE candidate FAILED",error);
					}
				});
				Gwt_p2p.log("Remote ICE candidate: \n" + event.getCandidate().getCandidate());
			}
		}
		public void onAddStream(MediaStreamEvent event) {
		}
		public void onDataChannel(RTCDataChannelEvent event) {
			//receiveChannelCallback
			Gwt_p2p.log("Receive Channel Callback");
			receiveChannel = event.getChannel();
			receiveChannel.setHandler(receiveChannelHandler);
		}
	};
	
	RTCDataChannelHandler receiveChannelHandler = new RTCDataChannelHandler() {
		public void onMessage(MessageEvent event) {	
			Gwt_p2p.log("Received Message");
			dataChannelReceive.setText(event.getData());
		}
		public void onError(JsErrorEvent event) {	
		}
		public void onOpen(JsEvent event) {	
			String readyState = receiveChannel.getReadyState().toString();
			Gwt_p2p.log("Receive channel state is: " + readyState);
		}
		public void onClose(JsEvent event) {	
			String readyState = receiveChannel.getReadyState().toString();
			Gwt_p2p.log("Receive channel state is: " + readyState);
		}
	};
	
	RTCDataChannelHandler sendChannelHandler = new RTCDataChannelHandler() {
		public void onOpen(JsEvent event) {
			this.onSendChannelStateChange();
		}
		public void onMessage(MessageEvent event) {
		}
		public void onError(JsErrorEvent event) {
		}
		public void onClose(JsEvent event) {
			this.onSendChannelStateChange();
		}
		private void onSendChannelStateChange(){
			RTCDataChannelState readyState = sendChannel.getReadyState();
			Gwt_p2p.log("Send channel state is: "+readyState.toString());
			if (readyState == RTCDataChannelState.open){
				dataChannelSend.setEnabled(true);
				dataChannelSend.setFocus(true);
				sendButton.setEnabled(true);
				closeButton.setEnabled(true);
			}else{
				dataChannelSend.setEnabled(false);
				sendButton.setEnabled(false);
				closeButton.setEnabled(false);
			}
			
		}
	};
	
	RTCPeerConnectionErrorCallback gotDescriptionFailure = new RTCPeerConnectionErrorCallback() {
		public void onRun(JsErrorEvent error) {
			Gwt_p2p.log("gotDescriptionFailure",error);
		}
	};
	public native static void test(String name, JavaScriptObject object)/*-{
		$wnd[name]=object;
	}-*/;
	public native static void logJson(String name, JavaScriptObject object)/*-{
        console.log("=="+name+"==",JSON.stringify(object));
	}-*/;
}
