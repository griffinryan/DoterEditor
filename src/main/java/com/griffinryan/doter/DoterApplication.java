package com.griffinryan.doter;

import com.griffinryan.doter.editor.Workspace;
import com.griffinryan.doter.gui.DoterMenu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.geometry.Side;

import java.io.File;
import java.util.Objects;

public class DoterApplication extends Application {

	private static final int INITIAL_WIDTH = 850;
	private static final int INITIAL_HEIGHT = 500;

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

		Image i = new Image("logo_small.png");
		Image icojava = new Image("ico_java.png");
		ImageView icons = new ImageView(icojava);

		ImageView iv = new ImageView(i);
		iv.fitHeightProperty();
		iv.fitWidthProperty();
		explorerPane.getChildren().add(iv);
		//explorerPane.getChildren().add(appMenu.getExplorer().getTreeView());
		//VBox box = new VBox();
		//explorerPane.getChildren().add(box);
		/*
		TextField[] myFiles = new TextField[appMenu.getWorkspace().getFileGroup().length];

		for(int j = 0; j < myFiles.length; j++){
			File temp = appMenu.getWorkspace().getFileGroup()[j];
			String s = temp.getName();
			myFiles[j] = new TextField(s);
		}

		for (TextField myFile : myFiles) {
			explorerPane.getChildren().add(myFile);
		}	*/

		Color paint = ghost5();
		BackgroundFill bf = new BackgroundFill(paint,null,null);
		Background b = new Background(bf);
		explorerPane.setBackground(b);
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
				0.6152, 0.285, 0.4608, 0.5416, true, CycleMethod.REFLECT,
				new Stop(0.0, new Color(0.29, 0.7054, 1.0, 0.5)),
				new Stop(0.0067, new Color(0.29, 0.7054, 1.0, 0.5)),
				new Stop(1.0, new Color(1.0, 0.29, 0.5406, 0.5)));
	}

	private LinearGradient blue1() {
		LinearGradient paint = new LinearGradient(
				0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(1.0, 1.0, 1.0, 1.0)),
				new Stop(1.0, new Color(0.0, 0.536, 1.0, 0.88)));
		return paint;
	}

	private LinearGradient blue2() {
		LinearGradient paint = new LinearGradient(
				0.1235, 0.5653, 0.8432, 0.848, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(1.0, 1.0, 1.0, 1.0)),
				new Stop(1.0, new Color(0.0, 0.536, 1.0, 0.88)));
		return paint;
	}

	private LinearGradient ghost1(){
		LinearGradient paint = new LinearGradient(
				0.2518, 0.0, 1.0, 0.848, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(1.0, 1.0, 1.0, 0.0)),
				new Stop(1.0, new Color(0.0, 0.7568, 1.0, 1.0)));

		return paint;
	}

	private LinearGradient ghost2() {
		LinearGradient paint = new LinearGradient(
				0.2518, 0.0, 1.0, 0.848, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(1.0, 1.0, 1.0, 0.0)),
				new Stop(1.0, new Color(1.0, 0.0, 0.4483, 1.0)));
		return paint;
	}

	private LinearGradient ghost3() {
		LinearGradient paint = new LinearGradient(
				0.2518, 0.0, 1.0, 0.848, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(1.0, 1.0, 1.0, 1.0)),
				new Stop(1.0, new Color(0.88, 0.0, 0.3667, 1.0)));
		return paint;
	}

	private LinearGradient ghost4() {
		LinearGradient paint = new LinearGradient(
				1.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.0, 0.0, 0.0, 1.0)),
				new Stop(1.0, new Color(1.0, 1.0, 1.0, 1.0)));
		return paint;
	}

	private Color ghost5(){
		Color paint = new Color(0.1176, 0.1176, 0.1176, 1.0);
		return paint;
	}

}
