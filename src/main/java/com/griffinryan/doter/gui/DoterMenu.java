package com.griffinryan.doter.gui;

import com.griffinryan.doter.editor.CodeEditor;
import com.griffinryan.doter.editor.Workspace;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoterMenu {

	public Menu fileMenu;
	public Menu editMenu;
	public FileChooser fileChooser;
	public DirectoryChooser directoryChooser;

	public MenuItem newFile;
	public MenuItem newProject;
	public MenuItem openFile;
	public MenuItem openProject;
	public MenuItem saveAsFile;
	public MenuItem saveFile;
	public MenuItem openSettings;
	public MenuItem closeProgram;
	public MenuBar menuBar;

	private Workspace workspace;
	private CodeEditor editor;

	String fileExtensionName;
	String TEMPEXTENSION = "java";

	public DoterMenu(Stage stage, Workspace workspace){
		this.fileChooser = new FileChooser();
		this.setFileExtensionName(TEMPEXTENSION);
		this.setWorkspace(workspace);
		this.editor = new CodeEditor();

		createMenuItems();

		this.newFile.setOnAction(e -> openWindow("New File", stage));
		this.newProject.setOnAction(e -> openWindow("New Project", stage));
		this.openFile.setOnAction(e -> openWindow("Open File", stage));
		this.openProject.setOnAction(e -> openWindow("Open Project", stage));
		this.openSettings.setOnAction(e -> openWindow("Open Settings", stage));
		this.saveAsFile.setOnAction(e -> openWindow("Save As", stage));
		this.saveFile.setOnAction(e -> openWindow("Save", stage));
		this.closeProgram.setOnAction(e -> System.exit(0));

		menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
		menuBar.getMenus().addAll(fileMenu, editMenu);
	}

	public void openWindow(String type, Stage stage) {
		this.fileChooser = new FileChooser();
		this.directoryChooser = new DirectoryChooser();
		MonacoFX tempMonaco = this.editor.getMonaco();
		File file = null;

		switch (type) {
			case "Open Settings" -> System.out.println("Haha, there are no settings!");
			case "New File" -> {
				this.fileChooser.setInitialFileName(workspace.getFileExtension());
				this.fileChooser.setTitle("Create New File..."); // set operation title.

				file = this.fileChooser.showSaveDialog(stage);    // save operation.
				this.editor.getMonaco().getEditor().getDocument().setText("");
			}
			case "Open File" -> {
				this.fileChooser.setTitle("Open File..."); // set operation title.
				file = this.fileChooser.showOpenDialog(stage);    // save operation.

				String document = saveFileToString(file);
				setEditorDocument(document);
			}
			case "Save As" -> {
				this.fileChooser.setInitialFileName(workspace.getFileExtension());
				this.fileChooser.setTitle("Save As..."); // set operation title.

				file = this.fileChooser.showSaveDialog(stage);
				String parsed = parseDocument(this.editor);
				saveStringtoFile(parsed, file);
			}
			case "Save" -> {
				this.fileChooser.setInitialFileName(workspace.getFileExtension());
				this.fileChooser.setTitle("Save..."); // set operation title.

				String filePath = this.workspace.getFileLocation();
				file = new File(filePath);
				String parsed = parseDocument(this.editor);
				saveStringtoFile(parsed, file);
			}
			case "Open Project" -> {
				this.directoryChooser.setTitle("Open Project...");
				file = this.directoryChooser.showDialog(stage);
				setEditorDocument("");
			}
			case "New Project" -> {
				this.directoryChooser.setTitle("Create New Project...");
				file = this.directoryChooser.showDialog(stage);
				setEditorDocument("");
			}
		}
		assert file != null;

		this.workspace.setCurrentFile(file);
		this.workspace.setDirectoryName(file.getParent());
		this.workspace.setDirectoryLocation(file.getAbsolutePath());
		this.workspace.setFileLocation(file.getPath());

	}

	public void setEditorDocument(String document){
		this.editor.getMonaco().getEditor().setCurrentLanguage(workspace.getFileExtension());
		this.editor.getMonaco().getEditor().getDocument().setText(document);
	}

	public String parseDocument(CodeEditor editor){
		return editor.getMonaco().getEditor().getDocument().getText();
	}

	private void saveStringtoFile(String parsed, File file){
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.println(parsed);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(DoterMenu.class.getName()).log(Level.SEVERE, null, e);
		}
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

	public void setFileExtensionName(String s){
		this.fileExtensionName = " ." + s; // set to .java
		this.fileChooser.setInitialFileName("App" + fileExtensionName);
	}

	public void createMenuItems(){
		this.fileMenu = new Menu("File"); // TO-DO: Add second args
		this.editMenu = new Menu("Edit"); // for image.
		this.newFile = new MenuItem("New File...");
		this.newProject = new MenuItem("New Project...");
		this.openFile = new MenuItem("Open File...");
		this.openProject = new MenuItem("Open Project...");
		this.saveFile = new MenuItem("Save...");
		this.saveAsFile = new MenuItem("Save As...");
		this.openSettings = new MenuItem("Settings...");
		this.closeProgram = new MenuItem("Quit...");


		this.fileMenu.getItems().add(this.newFile);
		this.fileMenu.getItems().add(this.newProject);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.openFile);
		this.fileMenu.getItems().add(this.openProject);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.saveFile);
		this.fileMenu.getItems().add(this.saveAsFile);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.openSettings);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.closeProgram);
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public CodeEditor getEditor() {
		return editor;
	}

	public void setEditor(CodeEditor editor) {
		this.editor = editor;
	}
}
