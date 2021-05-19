package com.collective.app;

import com.collective.app.consts.PageIdentifier;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * this is the class that creates the welcome page in the beguinning of the project
 * @author Aaron
 *
 */
public class WelcomePage extends Window {
	/**
	 * this is the contructor for the welcomepage
	 * @param con, this is a instance of the controller
	 */
	public WelcomePage(Controller con) {
		super(con);
	}
	/**
	 *  this is the bulidStage for the welcome page
	 * @return the Scene that the stage will be showing
	 * @author Aaron
	 */
	public BorderPane buildPage() {
		BorderPane container = new BorderPane();
		HBox Title = ui.HBoxWithText("Garden Simulator 2021", Pos.CENTER, 75);
		HBox Subtitle = ui.HBoxWithText("v1.0.2 \"Lep Lover\" | © 2021 The Collective", Pos.CENTER, 20);
		Subtitle.setPadding(new Insets(0, 0, 0, 0));
		
		//create button
		Button createButton = ui.createBasicButton("Create");
		createButton.setOnAction(controller.createGardenButtonEventHandler());
		ui.styleWelcomeButtons(createButton);
		

		//options button
		Button optionsButton = ui.createButton(controller, PageIdentifier.OPTIONS, "Options");
		ui.styleWelcomeButtons(optionsButton);


		//load button
		Button loadButton = ui.createButton(controller, PageIdentifier.LOADMENU, "Load");
		ui.styleWelcomeButtons(loadButton);

		//quit button
		Button quitButton = ui.createBasicButton("Quit");
		quitButton.setOnAction(controller.exitHandler());
		quitButton.setCancelButton(true);
		quitButton.setFont(new Font(50));
		quitButton.setId("quit-button");
		
		VBox column = new VBox(createButton, optionsButton, loadButton, quitButton);
		column.setAlignment(Pos.CENTER);
		container.setTop(Title);
		container.setBottom(Subtitle);
		container.setCenter(column);
		
		return container;
	}
}
