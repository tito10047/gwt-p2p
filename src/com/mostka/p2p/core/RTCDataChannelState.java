package com.mostka.p2p.core;

public enum RTCDataChannelState {
	/** The user agent is attempting to establish the underlying data transport. This is the initial state of a RTCDataChannel object created with createDataChannel() . */
	connecting,
	/** The underlying data transport is established and communication is possible. This is the initial state of a RTCDataChannel object dispatched as a part of a RTCDataChannelEvent .*/
    open,
    /** The procedure to close down the underlying data transport has started. */
    closing,
    /** The underlying data transport has been closed or could not be established. */
    closed;
	private RTCDataChannelState() {}
	public static RTCDataChannelState getState(String type) {
		if (type.equals("connecting")) return connecting;
		if (type.equals("open")) return open;
		if (type.equals("closing")) return closing;
		if (type.equals("closed")) return closed;
		System.err.println("undefined tyoe "+type);
		return closed;
	}

	@Override
	public String toString() {
		switch (this) {
		case connecting: return "connecting";
		case open:return "open";
		case closing:return "closing";
		case closed:return "closed";
		default:
			throw new IllegalArgumentException();
		}
	}
}
