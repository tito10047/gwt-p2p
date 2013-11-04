package com.mostka.p2p.core.face;

import com.mostka.p2p.event.MessageEvent;
import com.mostka.p2p.js.JsErrorEvent;
import com.mostka.p2p.js.JsEvent;

public interface RTCDataChannelHandler {

    public void onOpen(JsEvent event);
    public void onError(JsErrorEvent event);
    public void onClose(JsEvent event);
    public void onMessage(MessageEvent event);
}
