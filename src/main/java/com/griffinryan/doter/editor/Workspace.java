package com.griffinryan.doter.editor;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workspace {

	private File currentFile;
	private String fileName;
	private String fileLocation;
	private String directoryName;
	private String directoryLocation;
	private String fileExtension;
	private Map<String, String> propertyMap;
	private File[] fileGroup;
	private File currentDirectory;

	private JSONObject jsonFile;
	private boolean hasRecent;

	public Workspace() {
		checkHasRecent();
		this.jsonFile = new JSONObject();

		if(!hasRecent){
			/* Create a new /~/.config/doter/doter.json file. */
			createConfig();

		} else {
			/* Parse in existing /~/.config/doter/doter.json file. */
			String toParse = parseJsonConfig();
			this.jsonFile = new JSONObject(toParse);
			this.propertyMap = new HashMap<>(5);

			propertyMap.put("fileName", jsonFile.getString("fileName"));
			propertyMap.put("directoryName", jsonFile.getString("directoryName"));
			propertyMap.put("fileLocation", jsonFile.getString("fileLocation"));
			propertyMap.put("directoryLocation", jsonFile.getString("directoryLocation"));
			propertyMap.put("fileExtension", jsonFile.getString("fileExtension"));
			propertyMap.put("currentDirectory", jsonFile.getString("currentDirectory"));
			this.fileName = propertyMap.get("fileName");
			this.fileLocation = propertyMap.get("fileLocation");
			this.directoryName = propertyMap.get("directoryName");
			this.directoryLocation = propertyMap.get("directoryLocation");
			this.fileExtension = propertyMap.get("fileExtension");
			this.currentFile = new File(this.fileName);
			this.currentDirectory = new File(propertyMap.get("currentDirectory"));

			if(currentDirectory.exists()){
				File[] temp = currentDirectory.listFiles();
				this.fileGroup = temp;
			}
		}

	}

	public void setConfig(){
		propertyMap.put("fileName", this.fileName);
		propertyMap.put("directoryName", this.directoryName);
		propertyMap.put("fileLocation", this.fileLocation);
		propertyMap.put("directoryLocation", this.directoryLocation);
		propertyMap.put("fileExtension", this.fileExtension);
		propertyMap.put("currentDirectory", this.currentDirectory.getName());

		this.jsonFile = new JSONObject(this.propertyMap);

		String home = System.getProperty("user.home");
		File dir = new File(home + "/.config/doter");

		if(!dir.exists()){
			dir.mkdir();
		}
		File file = new File(dir, "doter.json");

		//File file = new File("doter.json");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assert writer != null;
		writer.println(this.jsonFile.toString());

		writer.flush();
		writer.close();
	}

	private void createConfig() {
		propertyMap = new HashMap<>(5);
		propertyMap.put("fileName", "empty");
		propertyMap.put("directoryName", "empty");
		propertyMap.put("fileLocation", "empty");
		propertyMap.put("directoryLocation", "empty");
		propertyMap.put("fileExtension", "empty");
		propertyMap.put("currentDirectory", "empty");
		this.jsonFile = new JSONObject(propertyMap);

		String home = System.getProperty("user.home");
		File dir = new File(home + "/.config/doter");

		if(!dir.exists()){
			dir.mkdir();
		}
		File file = new File(dir, "doter.json");

		//File file = new File("doter.json");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assert writer != null;
		writer.println(this.jsonFile.toString());

		writer.flush();
		writer.close();
	}

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

	public String getFileLocation() {
		return fileLocation;
	}

	public void setCurrentFile(File currentFile) {

		if(currentFile.isDirectory()){

			this.setCurrentDirectory(currentFile);
			File[] files = this.currentDirectory.listFiles();
			assert files != null;

			this.fileGroup = files;
			this.currentFile = files[0];
			this.fileName = this.currentFile.getName();
			int indexForSub = fileName.indexOf('.');
			this.fileExtension = fileName.substring(indexForSub + 1);
			this.fileLocation = this.currentFile.getPath();
			this.directoryName = this.currentFile.getParentFile().getName();
			this.directoryLocation = this.currentFile.getParentFile().getPath();
			setConfig();

		} else {
			this.fileName = currentFile.getName();
			int indexForSub = fileName.indexOf('.');
			this.fileExtension = fileName.substring(indexForSub + 1);
			this.fileLocation = currentFile.getPath();
			this.directoryName = currentFile.getParentFile().getName();
			this.directoryLocation = currentFile.getParentFile().getPath();
			this.currentFile = currentFile;
			setConfig();

		}
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

	public boolean isHasRecent() {
		return hasRecent;
	}

	public String getFileName() {
		return fileName;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public File[] getFileGroup() {
		return fileGroup;
	}

	public void setFileGroup(File[] fileGroup) {
		this.fileGroup = fileGroup;
	}

	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	public File getCurrentDirectory() {
		return currentDirectory;
	}
}
