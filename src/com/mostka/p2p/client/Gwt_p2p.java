package com.mostka.p2p.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Gwt_p2p implements EntryPoint {


	private Demo1 demo1;

	public void onModuleLoad() {
		init();
		demo1 = new Demo1();
	}
	public native void init()/*-{
		$wnd.logcnt=0;
	}-*/;

	public static native void log(String objects ) /*-{
		console.log($wnd.logcnt+"----"+objects);
		$wnd.logcnt++;
	}-*/;
	public static native void log(String str, JavaScriptObject objects ) /*-{
	console.log($wnd.logcnt+"----"+str, objects);
	$wnd.logcnt++;
}-*/;
}
