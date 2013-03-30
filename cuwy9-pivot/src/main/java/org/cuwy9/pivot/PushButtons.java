package org.cuwy9.pivot;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Window;

public class PushButtons extends Window implements Bindable {
	private PushButton pushButton;
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		pushButton = (PushButton)namespace.get("pushButton");

		pushButton.getButtonPressListeners().add(new ButtonPressListener() {
			public void buttonPressed(Button button) {
				Alert.alert(MessageType.INFO, "You clicked me!", PushButtons.this);
			}
		});
	}

}
