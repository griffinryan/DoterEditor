package com.griffinryan.doter.editor;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
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

	public Workspace(String file, String path, String directory, String ext) throws IOException {
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
	}

	private void createConfig() throws IOException {
		this.jsonFile.put("fileName", "void");
		this.jsonFile.put("directoryName", "void");
		this.jsonFile.put("fileLocation", "void");
		this.jsonFile.put("directoryLocation", "void");
		this.jsonFile.put("fileExtension", "void");

		FileWriter writer = new FileWriter("doter.json");
		writer.write(this.jsonFile.toString());

		writer.flush();
		writer.close();
	}

	private JSONObject parsejsonConfig(){
		File file = new File("doter.json");
		String s = saveFileToString(file);
		JSONObject json = new JSONObject(s);

		return json;
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

	private boolean checkHasRecent(){
		File config = new File("doter.json");
		Path path = config.toPath();
		return !Files.notExists(path);
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

	public JSONObject getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(JSONObject jsonFile) {
		this.jsonFile = jsonFile;
	}

	public String getjsonFileName(){
		String name = this.jsonFile.getString("fileName");
		return name;
	}

	public String getjsonFileLocation(){
		String location = this.jsonFile.getString("fileLocation");
		return location;
	}

	public String getjsonDirectoryName(){
		String name = this.jsonFile.getString("directoryName");
		return name;
	}

	public String getjsonDirectoryLocation(){
		String location = this.jsonFile.getString("directoryLocation");
		return location;
	}

	public String getjsonFileExtension(){
		String extension = this.jsonFile.getString("fileExtension");
		return extension;
	}

	public void setjsonFileName(String s){
		this.jsonFile.put("fileName", s);
	}

	public void setjsonFileLocation(String s){
		this.jsonFile.put("fileLocation", s);
	}

	public void setjsonDirectoryName(String s){
		this.jsonFile.put("directoryName", s);
	}

	public void setjsonDirectoryLocation(String s){
		this.jsonFile.put("directoryLocation", s);
	}

	public void setjsonFileExtension(String s){
		this.jsonFile.put("fileExtension", s);
	}

	public boolean isHasRecent() {
		return hasRecent;
	}

	public void setHasRecent(boolean hasRecent) {
		this.hasRecent = hasRecent;
	}
}
