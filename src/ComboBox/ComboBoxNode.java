package ComboBox;

import java.util.HashMap;
import java.util.Map;

public class ComboBoxNode {
	private String title;
	private int pos;
	private Map<String, Object> dataMap;

	public ComboBoxNode() {
	}

	public ComboBoxNode(String title) {
		this.title = title;
	}

	public ComboBoxNode(String title, Map<String, Object> dataMap) {
		this.title = title;
		this.dataMap = dataMap;
	}

	/*
	 * for ComboBox show title
	 */
	public String toString() {
		return this.title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	/*
	 * put data into dataMap
	 */
	public void putData(String key, Object value) {
		if (dataMap == null) {
			dataMap = new HashMap<>();
		}
		dataMap.put(key, value);
	}
}
