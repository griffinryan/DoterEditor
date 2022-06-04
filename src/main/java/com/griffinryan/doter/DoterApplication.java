package com.griffinryan.doter;


import eu.mihosoft.monacofx.Rule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import eu.mihosoft.monacofx.CursorSelection;
import eu.mihosoft.monacofx.MonacoFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.griffinryan.doter.editor.CodeEditor;

import java.io.IOException;

/**
 * An example application which demonstrates use of a
 * CodeMirror based JavaScript CodeEditor wrapped in
 * a JavaFX WebView.
 */
public class DoterApplication extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		/*
		FXMLLoader fxmlLoader = new FXMLLoader(DoterApplication.class.getResource("doter-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
		*/

		FXMLLoader fxmlLoader = new FXMLLoader(DoterApplication.class.getResource("doter-view.fxml"));

		// Create a new MonacoFX Editor node.
		MonacoFX monaco = new MonacoFX();
		SplitPane root = new SplitPane(monaco);

		// set initial text.
		monaco.getEditor().getDocument().setText(
				"#include <stdio.h>\n" +
						"int main() {\n" +
						"   // printf() displays the string inside quotation\n" +
						"   printf(\"Hello, World!\");\n" +
						"   return 0;\n" +
						"}");

		// use a predefined language like 'c'
		monaco.getEditor().setCurrentLanguage("c");
		monaco.getEditor().setCurrentTheme("vs-dark");
		monaco.getBackground();
		
		// the usual scene & stage setup
		Scene scene = new Scene(root, 800,600);
		primaryStage.setTitle("MonacoFX Demo (running on JDK " + System.getProperty("java.version") + ")");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Color paint = new Color(0.0, 0.3207, 0.52, 0.7572);
}
