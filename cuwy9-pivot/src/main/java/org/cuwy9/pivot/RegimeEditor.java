package org.cuwy9.pivot;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Span;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewRowListener;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.content.TableViewHeaderData;

public class RegimeEditor  extends Window implements Bindable {
	protected final Log log = LogFactory.getLog(getClass());
	private TextInput drugTextInput = null;
	TableView sortableTableView = null;

	private ArrayList<String> states;
	private SuggestionPopup suggestionPopup = new SuggestionPopup();
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		log.debug(1);
		drugTextInput = (TextInput)namespace.get("drugTextInput");
		
		initTableView(namespace);

		TextInputContentListener.Adapter adapter = new TextInputContentListener.Adapter() {
			public void textInserted(TextInput textInput, int index, int count) {
				log.debug(2);
				String text = textInput.getText();
				ArrayList<String> suggestions = new ArrayList<String>();

				for (String state : states) {
					if (state.toUpperCase().startsWith(text.toUpperCase())) {
						suggestions.add(state);
					}
				}

				if (suggestions.getLength() > 0) {
					suggestionPopup.setSuggestionData(suggestions);
					suggestionPopup.open(textInput);
				}
			}

			public void textRemoved(TextInput textInput, int index, int count) {
				suggestionPopup.close();
			}
		};
		drugTextInput.getTextInputContentListeners().add(adapter);

		suggestionPopup.setListSize(4);
	}
	private void initTableView(Map<String, Object> namespace) {
		sortableTableView = (TableView)namespace.get("sortableTableView");
		TableViewSelectionListener listener=new TableViewSelectionListener(){

			public void selectedRangeAdded(TableView tableView, int rangeStart,
					int rangeEnd) {
				log.debug(1);
			}

			public void selectedRangeRemoved(TableView tableView,
					int rangeStart, int rangeEnd) {
				log.debug(1);
			}

			public void selectedRangesChanged(TableView tableView,
					Sequence<Span> previousSelectedRanges) {
				log.debug(1);
			}

			public void selectedRowChanged(TableView tableView,
					Object previousSelectedRow) {
				HashMap<String, String> selectedRow = (HashMap<String, String>) tableView.getSelectedRow();
				String drug = selectedRow.get("a");
				drugTextInput.setText(drug);
			}};
		sortableTableView.getTableViewSelectionListeners().add(listener);
		sortableTableView.getTableViewRowListeners().add(new TableViewRowListener(){

			public void rowInserted(TableView tableView, int index) {
				log.debug(1);
			}

			public void rowsRemoved(TableView tableView, int index, int count) {
				log.debug(1);
			}

			public void rowUpdated(TableView tableView, int index) {
				log.debug(1);
			}

			public void rowsCleared(TableView tableView) {
				log.debug(1);
			}

			public void rowsSorted(TableView tableView) {
				log.debug(1);
			}} );
		// Set table header data
		TableView.ColumnSequence columns = this.sortableTableView.getColumns();
		columns.get(0).setHeaderData(new TableViewHeaderData("#"));
		columns.get(1).setHeaderData(new TableViewHeaderData("drug"));
		columns.get(2).setHeaderData(new TableViewHeaderData("dose"));
		columns.get(3).setHeaderData(new TableViewHeaderData("C"));
		columns.get(4).setHeaderData(new TableViewHeaderData("D"));
		ArrayList<Object> tableData = new ArrayList<Object>(10);

		addDrug(tableData, "Dexa", "2 mg");
		addDrug(tableData, "NaCl 0.9%", "250 ml");
		this.sortableTableView.setTableData(tableData);

	}
	private void addDrug(ArrayList<Object> tableData, String drug, String dose) {
		HashMap<String, String> tableRow = new HashMap<String, String>();
		tableRow.put("a", drug);
		tableRow.put("b", dose);
		tableData.add(tableRow);
	}
	public void open(Display display, Window owner) {
		super.open(display, owner);
		drugTextInput.requestFocus();
	}
	public RegimeEditor(){
		log.debug(3);
		// Populate the lookup values, ensuring that they are sorted
		states = new ArrayList<String>();
		states.setComparator(String.CASE_INSENSITIVE_ORDER);

		states.add("Alabama");
		states.add("Alaska");
		states.add("Arizona");
		states.add("Arkansas");
		states.add("California");
		states.add("Colorado");
		states.add("Connecticut");
		states.add("Delaware");
		states.add("District of Columbia");
		states.add("Florida");
		states.add("Georgia");
		states.add("Hawaii");
		states.add("Idaho");
		states.add("Illinois");
		states.add("Indiana");
		states.add("Iowa");
		states.add("Kansas");
		states.add("Kentucky");
		states.add("Louisiana");
		states.add("Maine");
		states.add("Maryland");
		states.add("Massachusetts");
		states.add("Michigan");
		states.add("Minnesota");
		states.add("Mississippi");
		states.add("Missouri");
		states.add("Montana");
		states.add("Nebraska");
		states.add("Nevada");
		states.add("New Hampshire");
		states.add("New Jersey");
		states.add("New Mexico");
		states.add("New York");
		states.add("North Carolina");
		states.add("North Dakota");
		states.add("Ohio");
		states.add("Oklahoma");
		states.add("Oregon");
		states.add("Pennsylvania");
		states.add("Rhode Island");
		states.add("South Carolina");
		states.add("South Dakota");
		states.add("Tennessee");
		states.add("Texas");
		states.add("Utah");
		states.add("Vermont");
		states.add("Virginia");
		states.add("Washington");
		states.add("West Virginia");
		states.add("Wisconsin");
		states.add("Wyoming");
	}
}
