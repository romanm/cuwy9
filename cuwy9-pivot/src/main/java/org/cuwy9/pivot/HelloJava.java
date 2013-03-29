package org.cuwy9.pivot;

import java.awt.Color;
import java.awt.Font;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.Window;

public class HelloJava implements Application {
	private Window window = null;

	public void startup(Display display, Map<String, String> properties) {
		window = new Window();

		Label label = new Label();
		label.setText("Hello World!");
		label.getStyles().put("font", new Font("Arial", Font.BOLD, 24));
		label.getStyles().put("color", Color.RED);
		label.getStyles().put("horizontalAlignment",
				HorizontalAlignment.CENTER);
		label.getStyles().put("verticalAlignment",
				VerticalAlignment.CENTER);

		window.setContent(label);
		window.setTitle("Hello World!");
		window.setMaximized(true);

		window.open(display);
	}

	public boolean shutdown(boolean optional) {
		if (window != null) {
			window.close();
		}

		return false;
	}

	public void suspend() {
	}

	public void resume() {
	}
	public static void main(String[] args) {
		DesktopApplicationContext.main(HelloJava.class, args);
	}
}
