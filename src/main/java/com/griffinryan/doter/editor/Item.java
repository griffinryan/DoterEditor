package com.griffinryan.doter.editor;

import javafx.beans.property.SimpleStringProperty;

public class Item {

	private SimpleStringProperty name;
	private SimpleStringProperty folder;

	public Item(String name, String folder){
		this.name = new SimpleStringProperty(name);
		this.folder = new SimpleStringProperty(folder);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getFolder() {
		return folder.get();
	}

	public void setFolder(String folder) {
		this.folder.set(folder);
	}
}
