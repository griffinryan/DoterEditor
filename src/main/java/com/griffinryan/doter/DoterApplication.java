package com.griffinryan.doter;

import com.griffinryan.doter.editor.Workspace;
import com.griffinryan.doter.gui.DoterMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoterApplication extends Application {

	private static final int INITIAL_WIDTH = 1920;
	private static final int INITIAL_HEIGHT = 1080;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {
		/* Create a new MonacoFX editor node and SplitPane to use. */
		Workspace applicationWorkspace = new Workspace();
		if(applicationWorkspace.isHasRecent()){

		} else {
			// create first-time startup
		}

		StackPane editorPane = new StackPane();
		StackPane explorerPane = new StackPane();
		BorderPane parentPane = new BorderPane();
		SplitPane pane = new SplitPane();

		DoterMenu appMenu = new DoterMenu(primaryStage, applicationWorkspace);

		explorerPane.setMaxSize(200,600);
		editorPane.getChildren().add(appMenu.getEditor().getMonaco());

		pane.getItems().addAll(explorerPane, editorPane);
		pane.setOpacity(1);

		parentPane.setTop(appMenu.getMenuBar());
		parentPane.setCenter(pane);
		// the usual scene & stage setup
		Scene scene = new Scene(parentPane, INITIAL_WIDTH,INITIAL_HEIGHT);
		primaryStage.setTitle("Doter Editor - example.java");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
