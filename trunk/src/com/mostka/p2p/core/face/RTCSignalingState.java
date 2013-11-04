package com.mostka.p2p.core.face;

public enum RTCSignalingState {
	stable,
    have_local_offer,
    have_remote_offer,
    have_local_pranswer,
    have_remote_pranswer,
    closed;
}
