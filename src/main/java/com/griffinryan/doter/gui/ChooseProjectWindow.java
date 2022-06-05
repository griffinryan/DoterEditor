package com.griffinryan.doter.gui;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChooseProjectWindow {

	private final Stage stage;
	private String type;

	public ChooseProjectWindow(String type){
		this.stage = new Stage(StageStyle.DECORATED);
		this.type = type;

		Text temp = new Text(type);
		Font titleFont = new Font(8); /* TO-DO: Add second args
		for .ttf font type.	*/

		StackPane parentPane = new StackPane();
		parentPane.getChildren().add(temp);

		Scene scene = new Scene(parentPane, 500,500);
		stage.setTitle(this.type);
		stage.sizeToScene();
		//stage.show();
	}

	public void show(){
		this.stage.show();
	}


}
