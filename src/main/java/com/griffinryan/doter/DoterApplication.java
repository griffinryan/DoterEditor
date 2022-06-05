package com.griffinryan.doter;

import com.griffinryan.doter.gui.DoterMenu;
import eu.mihosoft.monacofx.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DoterApplication extends Application {

	public static int INITIAL_WIDTH = 800;
	public static int INITIAL_HEIGHT = 600;
	/*
	public Menu fileMenu;
	public Menu editMenu;
	public MenuItem newFile;
	public MenuItem openFile;
	public MenuItem saveFile;
	public MenuItem sep;
	public MenuItem openSettings;
	public MenuItem closeProgram;
	public MenuBar menuBar;	*/

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {
		/* Create a new MonacoFX editor node and SplitPane to use. */
		MonacoFX monaco = createMonacoNode();
		StackPane editorPane = new StackPane();
		StackPane explorerPane = new StackPane();
		BorderPane parentPane = new BorderPane();
		SplitPane pane = new SplitPane();

		explorerPane.setMaxSize(200,600);
		editorPane.getChildren().add(monaco);

		pane.getItems().addAll(explorerPane, editorPane);
		pane.setOpacity(1);

		DoterMenu appMenu = new DoterMenu(parentPane);

		parentPane.setTop(appMenu.menuBar);
		parentPane.setCenter(pane);
		// the usual scene & stage setup
		Scene scene = new Scene(parentPane, INITIAL_WIDTH,INITIAL_HEIGHT);
		primaryStage.setTitle("Doter Editor - example.java");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static MonacoFX createMonacoNode(){
		// set initial text.
		MonacoFX result = new MonacoFX();
		result.getEditor().getDocument().setText(
				"""
						#include <stdio.h>
						int main() {
						   // printf() displays the string inside quotation
						   printf("Hello, World!");
						   return 0;
						}""");

		result.getEditor().setCurrentLanguage("java");
		result.getEditor().setCurrentTheme("vs-dark");
		return result;
	}

	/*
	public static MenuBar createMenuBar(Stage primaryStage){
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");

		MenuItem newFile = new MenuItem("New...");
		MenuItem openFile = new MenuItem("Open...");
		MenuItem saveFile = new MenuItem("Save...");
		MenuItem sep = new SeparatorMenuItem();
		MenuItem openSettings = new MenuItem("Settings...");
		MenuItem closeProgram = new MenuItem("Quit...");

		fileMenu.getItems().addAll(newFile, openFile, saveFile, sep, openSettings, sep, closeProgram);

		FileChooser fileChooser = new FileChooser();
		//newFile.setOnAction(e -> fileChooser.showOpenDialog(primaryStage));

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu);

		menuBar.setUseSystemMenuBar(true);
		return menuBar;
	}	*/


}
