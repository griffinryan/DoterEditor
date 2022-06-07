package com.griffinryan.doter.editor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import org.json.JSONArray;
import org.json.JSONWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Workspace {

	private File currentFile;

	private String fileName;
	private String fileLocation;
	private String directoryName;
	private String directoryLocation;
	private String fileExtension;

	private JSONArray jsonFile;
	private String savedDocument;
	private boolean hasRecent;

	public Workspace() {
		checkHasRecent();
		this.jsonFile = new JSONArray();

		if(!hasRecent){
			/* Create a new /~/.config/doter/doter.json file. */
			try {
				createConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			/* Parse in existing /~/.config/doter/doter.json file. */
			String toParse = parseJsonConfig();
			JsonStreamParser parser = new JsonStreamParser(toParse);

			this.fileLocation = parser.next().getAsJsonObject().get("fileLocation").toString();
			this.directoryName = parser.next().getAsJsonObject().get("directoryName").toString();
			this.directoryLocation = parser.next().getAsJsonObject().get("directoryLocation").toString();
			this.fileExtension = parser.next().getAsJsonObject().get("fileExtension").toString();

			this.currentFile = new File(this.fileName);
		}

	}

	private void createConfig() throws IOException {
		this.jsonFile = new JSONArray();
		JSONWriter
		this.jsonFile.put()
		this.jsonFile.addProperty("fileName", "empty");
		this.jsonFile.addProperty("directoryName", "empty");
		this.jsonFile.addProperty("fileLocation", "empty");
		this.jsonFile.addProperty("directoryLocation", "empty");
		this.jsonFile.addProperty("fileExtension", "empty");

		boolean configExists;
		String home = System.getProperty("user.home");
		File dir = new File(home + "/.config/doter");

		if(!dir.exists()){
			configExists = dir.mkdir();
		}
		File file = new File(dir, "doter.json");

		//File file = new File("doter.json");
		PrintWriter writer = new PrintWriter(file);
		writer.println(this.jsonFile.toString());

		writer.flush();
		writer.close();
	}

	/*
	public Workspace(String ext){
		this.fileExtension = ext;
	}	*/

	/*public Workspace(String file, String path, String directory, String ext) throws IOException {
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
			this.jsonFile = parsejsonConfig();
			this.fileName = jsonFile.getString("fileName");
			this.fileLocation = jsonFile.getString("fileLocation");
			this.directoryName = jsonFile.getString("directoryName");
			this.directoryLocation = jsonFile.getString("directoryLocation");
			this.fileExtension = jsonFile.getString("fileExtension");
		}
	}	*/

	private String parseJsonConfig(){
		String home = System.getProperty("user.home");
		File file = new File(home + "/.config/doter/doter.json");

		return saveFileToString(file);
	}

	private String saveFileToString(File file){
		String result = "";
		Path filePath = file.toPath();
		try {
			result = Files.readString(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void checkHasRecent(){
		String home = System.getProperty("user.home");

		File config = new File(home + "/.config/doter/doter.json");
		Path path = config.toPath();
		hasRecent = !Files.notExists(path);
	}

	public void instCurrentFile(String s){
		this.currentFile = new File(s);
	}

	public String getSavedDocument() {
		return this.savedDocument;
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

	public JsonObject getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(JsonObject jsonFile) {
		this.jsonFile = jsonFile;
	}

	public boolean isHasRecent() {
		return hasRecent;
	}

	public void setHasRecent(boolean hasRecent) {
		this.hasRecent = hasRecent;
	}
}
