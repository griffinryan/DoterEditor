package com.griffinryan.doter;

import eu.mihosoft.monacofx.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoterApplication extends Application {

	public static int INITIAL_WIDTH = 800;
	public static int INITIAL_HEIGHT = 600;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {
		/* Create a new MonacoFX editor node and SplitPane to use. */
		MonacoFX monaco = createMonacoNode();
		Background cooler = new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, Insets.EMPTY));

		StackPane editorPane = new StackPane();
		StackPane explorerPane = new StackPane();
		editorPane.setBackground(cooler);
		explorerPane.setBackground(cooler);
		explorerPane.setMaxSize(200,600);
		explorerPane.setOpacity(0.9);

		editorPane.getChildren().add(monaco);
		// explorerPane.getChildren().add(fileExplorer());

		SplitPane pane = new SplitPane();
		pane.getItems().addAll(explorerPane, editorPane);
		pane.setOpacity(1);

		// the usual scene & stage setup
		Scene scene = new Scene(pane, INITIAL_WIDTH,INITIAL_HEIGHT);
		primaryStage.setTitle("Doter Editor - example.java");
		primaryStage.setOpacity(1);
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


}
