package com.griffinryan.doter;


import javafx.application.Application;
import javafx.scene.Scene;
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
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(DoterApplication.class.getResource("doter-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}

	// Color paint = new Color(0.0, 0.3207, 0.52, 0.7572);
}
