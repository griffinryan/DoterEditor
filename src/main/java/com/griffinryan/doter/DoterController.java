package com.griffinryan.doter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DoterController {
	@FXML
	private Label welcomeText;

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
	}
}
