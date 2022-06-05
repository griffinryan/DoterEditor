package com.griffinryan.doter.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DoterMenu {

	public Menu fileMenu;
	public Menu editMenu;
	public MenuItem newFile;
	public MenuItem openFile;
	public MenuItem saveFile;
	public MenuItem openSettings;
	public MenuItem closeProgram;
	public MenuBar menuBar;
	public FileChooser fileChooser;
	public ChooseProjectWindow window;

	public DoterMenu(BorderPane parentPane){
		this.fileMenu = new Menu("File"); // TO-DO: Add second args
		this.editMenu = new Menu("Edit"); // for image.

		this.newFile = new MenuItem("New...");
		this.openFile = new MenuItem("Open...");
		this.saveFile = new MenuItem("Save...");
		this.openSettings = new MenuItem("Settings...");
		this.closeProgram = new MenuItem("Quit...");

		this.fileMenu.getItems().add(this.newFile);
		this.fileMenu.getItems().add(this.openFile);
		this.fileMenu.getItems().add(this.saveFile);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.openSettings);
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		this.fileMenu.getItems().add(this.closeProgram);

		this.fileChooser = new FileChooser();
		this.newFile.setOnAction(e -> openWindow("New File"));
		this.openFile.setOnAction(e -> openWindow("Open File"));
		this.saveFile.setOnAction(e -> openWindow("Save File"));
		this.openSettings.setOnAction(e -> openWindow("Open Settings"));
		this.closeProgram.setOnAction(e -> System.exit(0));

		menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu);

		menuBar.setUseSystemMenuBar(true);
	}

	public void openWindow(String type){
		// Stage openStage = new Stage();
		// window = new ChooseProjectWindow(type);
		// window.show();
	}

	public Menu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(Menu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public Menu getEditMenu() {
		return editMenu;
	}

	public void setEditMenu(Menu editMenu) {
		this.editMenu = editMenu;
	}

	public MenuItem getNewFile() {
		return newFile;
	}

	public void setNewFile(MenuItem newFile) {
		this.newFile = newFile;
	}

	public MenuItem getOpenFile() {
		return openFile;
	}

	public void setOpenFile(MenuItem openFile) {
		this.openFile = openFile;
	}

	public MenuItem getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(MenuItem saveFile) {
		this.saveFile = saveFile;
	}

	public MenuItem getOpenSettings() {
		return openSettings;
	}

	public void setOpenSettings(MenuItem openSettings) {
		this.openSettings = openSettings;
	}

	public MenuItem getCloseProgram() {
		return closeProgram;
	}

	public void setCloseProgram(MenuItem closeProgram) {
		this.closeProgram = closeProgram;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

}
