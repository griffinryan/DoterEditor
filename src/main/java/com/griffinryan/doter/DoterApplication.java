package com.griffinryan.doter;


import eu.mihosoft.monacofx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

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
		FXMLLoader fxmlLoader = new FXMLLoader(DoterApplication.class.getResource("doter-view.fxml"));

		/* Create a new MonacoFX editor node and SplitPane to use. */
		MonacoFX monaco = createMonacoNode(true);
		SplitPane pane = new SplitPane(monaco);

		// the usual scene & stage setup
		Scene scene = new Scene(pane, 800,600);
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

	public static MonacoFX createMonacoNode(boolean testNewConfig){
		MonacoFX result = new MonacoFX();
		// set initial text.
		result.getEditor().getDocument().setText(
				"""
						#include <stdio.h>
						int main() {
						   // printf() displays the string inside quotation
						   printf("Hello, World!");
						   return 0;
						}""");

		// custom language based on
		// https://microsoft.github.io/monaco-editor/playground.html#extending-language-services-custom-languages
		LanguageSupport language = new LanguageSupport() {
			@Override
			public String getName() {
				return "mylang";
			}

			@Override
			public FoldingProvider getFoldingProvider() {
				// register custom code folds:
				return editor -> new Folding[]{new Folding(1,5),new Folding(7,10)};
			}

			@Override
			public MonarchSyntaxHighlighter getMonarchSyntaxHighlighter() {
				// custom syntax highlighter:
				return () -> """
						tokenizer: {
						    root: [
						      [/\\[error.*/, "custom-error"],
						      [/\\[notice.*/, "custom-notice"],
						      [/\\[info.*/, "custom-info"],
						      [/\\[[a-zA-Z 0-9:]+\\]/, "custom-date"],
						    ]
						}""";
			}
		};

		result.getEditor().registerLanguage(language);
		result.getEditor().registerTheme(new EditorTheme("mylang-theme", "vs-dark", true,
				new Rule("custom-info", "808080"),
				new Rule("custom-error", "ff0000", null, null,null, "bold"),
				new Rule("custom-notice", "ffa500"),
				new Rule("custom-date", "008800")
		));
		result.getEditor().setCurrentLanguage("java");
		result.getEditor().setCurrentTheme("vs-dark");

		Color paint = new Color(0.0, 0.3207, 0.52, 0.7572);
		BackgroundFill bg = new BackgroundFill(paint, null, null);
		//poop.getBackground().getFills().add(bg);

		return result;
	}

	// Color paint = new Color(0.0, 0.3207, 0.52, 0.7572);
}
