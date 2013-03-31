package org.cuwy9.pivot.layout;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.Mouse;

public class SimpleTablePanes extends Window implements Bindable {
	protected final Log log = LogFactory.getLog(getClass());
	private TablePane tablePane = null;

	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		tablePane = (TablePane)namespace.get("tablePane");

		tablePane.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter() {
			@Override
			public boolean mouseClick(Component component, Mouse.Button button, int x, int y, int count) {
				int rowIndex = tablePane.getRowAt(y);
				log.debug("rowIndex = "+rowIndex);
				int columnIndex = tablePane.getColumnAt(x);

				if (rowIndex >= 0
						&& columnIndex >= 0) {
					TablePane.Row row = tablePane.getRows().get(rowIndex);
					TablePane.Column column = tablePane.getColumns().get(columnIndex);

					int rowHeight = row.getHeight();
					int columnWidth = column.getWidth();

					String message = "Registered Click At " + rowIndex + "," + columnIndex;

					Label heightLabel = new Label(String.format("The row's height is %d (%s)",
							rowHeight,
							rowHeight == -1 ? "default" : (row.isRelative() ? "relative" : "absolute")));
					Label widthLabel = new Label(String.format("The column's width is %d (%s)",
							columnWidth,
							columnWidth == -1 ? "default" : (column.isRelative() ? "relative" : "absolute")));

					BoxPane body = new BoxPane(Orientation.VERTICAL);
					body.add(heightLabel);
					body.add(widthLabel);

					Prompt.prompt(MessageType.INFO, message, body, SimpleTablePanes.this);
				}

				return false;
			}
		});
	}
}
