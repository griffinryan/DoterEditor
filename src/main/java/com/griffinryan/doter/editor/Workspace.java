package com.griffinryan.doter.editor;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Workspace {

	private File currentFile;
	private String fileName;
	private String fileLocation;
	private String directoryName;
	private String directoryLocation;
	private String fileExtension;
	private JSONObject jsonFile;
	private String savedDocument;
	private boolean hasRecent;

	public Workspace(String ext){
		this.fileExtension = ext;
	}

	public Workspace(String file, String path, String directory, String ext){
		this.currentFile = new File(file);
		this.fileName = file;
		this.fileLocation = path;
		this.directoryName = directory;
		this.directoryLocation = path;
		this.fileExtension = ext;
		this.hasRecent = checkHasRecent();
		jsonFile = new JSONObject();

		if(!hasRecent){
			createConfig();
		} else{
			this.fileName = jsonFile.getString("fileName");
			this.fileLocation = jsonFile.getString("fileLocation");
			this.directoryName = jsonFile.getString("directoryName");
			this.directoryLocation = jsonFile.getString("directoryLocation");
			this.fileExtension = jsonFile.getString("fileExtension");
		}
	}

	private void createConfig(){
		String home = System.getProperty("user.home");
		File configDir = new File(home + "/.config/doter");
		File configFile = new File(configDir, "doter.json");

		jsonFile.put("fileName", "void");
		jsonFile.put("directoryName", "void");
		jsonFile.put("fileLocation", "void");
		jsonFile.put("directoryLocation", "void");
		jsonFile.put("fileExtension", "void");
	}

	private boolean checkHasRecent(){
		String home = System.getProperty("user.home");
		Path tempDirectory = null;

		try {
			tempDirectory = Files.createTempDirectory(home + "/.config/doter");
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert tempDirectory != null;
		return !Files.notExists(tempDirectory);
	}

	public void instCurrentFile(String s){
		this.currentFile = new File(s);
	}

	public String getSavedDocument() {

		return savedDocument;
	}

	public void setSavedDocument(String savedDocument) {
		this.savedDocument = savedDocument;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public String getDirectoryLocation() {
		return directoryLocation;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public void setDirectoryLocation(String directoryLocation) {
		this.directoryLocation = directoryLocation;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public JSONObject getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(JSONObject jsonFile) {
		this.jsonFile = jsonFile;
	}
}
