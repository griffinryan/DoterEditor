package com.griffinryan.doter.editor;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;

public class Item {

	private SimpleStringProperty name;
	private SimpleStringProperty folder;
	private File currentFile;
	private File currentDirectory;

	public Item(File theCurrentFile, File theCurrentDirectory){
		this.currentFile = theCurrentFile;
		this.currentDirectory = theCurrentDirectory;

		this.name = new SimpleStringProperty(currentFile.getName());
		this.folder = new SimpleStringProperty(currentDirectory.getName());
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

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public File getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
}
