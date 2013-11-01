package com.mostka.p2p.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Gwt_p2p implements EntryPoint {


	public void onModuleLoad() {
		RootPanel.get().add(new HTML("Foo"));
	}
}
