package com.griffinryan.doter;

import com.griffinryan.doter.editor.CodeEditor;
import com.griffinryan.doter.editor.Workspace;
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

	private static int INITIAL_WIDTH = 800;
	private static int INITIAL_HEIGHT = 600;
	public String TEMPEXTENSION = "java";

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {
		/* Create a new MonacoFX editor node and SplitPane to use. */
		CodeEditor editor = new CodeEditor();
		Workspace workspace = new Workspace(TEMPEXTENSION);

		StackPane editorPane = new StackPane();
		StackPane explorerPane = new StackPane();
		BorderPane parentPane = new BorderPane();
		SplitPane pane = new SplitPane();

		explorerPane.setMaxSize(200,600);
		editorPane.getChildren().add(editor.getMonaco());

		pane.getItems().addAll(explorerPane, editorPane);
		pane.setOpacity(1);

		DoterMenu appMenu = new DoterMenu(primaryStage);

		parentPane.setTop(appMenu.menuBar);
		parentPane.setCenter(pane);
		// the usual scene & stage setup
		Scene scene = new Scene(parentPane, INITIAL_WIDTH,INITIAL_HEIGHT);
		primaryStage.setTitle("Doter Editor - example.java");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/*
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
	}	*/

	public static void updateMonacoNode(MonacoFX monaco){

	}

}
