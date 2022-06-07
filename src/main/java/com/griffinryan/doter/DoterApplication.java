package com.griffinryan.doter;

import com.griffinryan.doter.editor.Workspace;
import com.griffinryan.doter.gui.DoterMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class DoterApplication extends Application {

	private static final int INITIAL_WIDTH = 1000;
	private static final int INITIAL_HEIGHT = 600;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {

		/* Create a new MonacoFX editor node and SplitPane to use. */
		Workspace applicationWorkspace = new Workspace();

		if(applicationWorkspace.isHasRecent()){
			/* Return to previous session. */

		} else {
			/* Create first-time session at startup. */

		}

		SplitPane editorPane = new SplitPane();
		StackPane explorerPane = new StackPane();
		BorderPane parentPane = new BorderPane();
		SplitPane pane = new SplitPane();
		editorPane.setDividerPosition(0, 10);

		DoterMenu appMenu = new DoterMenu(primaryStage, applicationWorkspace);

		explorerPane.setMaxSize(200,600);
		editorPane.getItems().addAll(appMenu.getEditor().getMonaco());

		LinearGradient paint = niceColor();
		BackgroundFill bf = new BackgroundFill(paint,null,null);
		Background bg = new Background(bf);
		explorerPane.setBackground(bg);
		pane.getItems().addAll(explorerPane, editorPane);
		pane.setOpacity(1);

		parentPane.setTop(appMenu.getMenuBar());
		parentPane.setCenter(pane);
		// the usual scene & stage setup
		Scene scene = new Scene(parentPane, INITIAL_WIDTH,INITIAL_HEIGHT);
		//if(appMenu.)
		primaryStage.setTitle("Doter Editor - " + appMenu.getWorkspace().getFileName());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private LinearGradient niceColor() {
		return new LinearGradient(
				0.6152, 0.285, 0.4608, 0.5416, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.29, 0.7054, 1.0, 1.0)),
				new Stop(0.0067, new Color(0.29, 0.7054, 1.0, 1.0)),
				new Stop(1.0, new Color(1.0, 0.29, 0.5406, 1.0)));
	}

}
