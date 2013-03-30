package org.cuwy9.pivot;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

public class HelloBXML implements Application {
//	String bxml = "/hello2.bxml";
//	String bxml = "/hello.bxml";
//	String bxml = "/hello3.bxml";
//	String bxml = "/pushButton.bxml";
	String bxml = "/lListViews.bxml";
	private Window window = null;
public static void main(String[] args) {
	DesktopApplicationContext.main(HelloBXML.class, args);
}
	//@Override
	public void startup(Display display, Map<String, String> properties)
			throws Exception {
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		window = (Window)bxmlSerializer.readObject(HelloBXML.class, bxml);
		window.open(display);
	}

	//@Override
	public boolean shutdown(boolean optional) {
		if (window != null) {
			window.close();
		}

		return false;
	}

	//    @Override
	public void suspend() {
	}

	//    @Override
	public void resume() {
	}
}