package com.collective.app;

import javax.swing.event.ChangeEvent;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.GardenPageModel;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
/**
 * this is the class that bulids the page for the conditions page
 * @author Aaron
 *
 */
public class Conditions extends Window {
//	ChangeEvent sunLevelDropDown;
//	ChangeEvent soilTypeDropDown;
//	ChangeEvent moistureLevelDropDown;
//	ChangeEvent conditionChange;
//	ActionEvent changePage;
	
	/**
	 * this is the contructor for the conditions page
	 * @param con, this is a instance of controller
	 * @author Aaron
	 */
	public Conditions(Controller con) {
		super(con);
	}
	
	/**
	 * this is the function that bulids the scene that will be shown in the stage
	 * @return Scene, this is what will be shown in the stage
	 * @author Aaron
	 */
	public BorderPane buildPage() {
		BorderPane container = new BorderPane();
		GardenPageModel model = controller.getGardenPageModel();
		
		HBox title = ui.HBoxWithText("Garden Conditions", Pos.CENTER, 75);
		container.setTop(title);
		
		//Drop Down Menus
		//sun conditions
		final ComboBox<String> sunBox = new ComboBox<String>();
		sunBox.getItems().addAll("Full Sun","Half Sun","Shade");
		sunBox.setPromptText("Sun Level");
		sunBox.setOnAction(controller.sunLevelDropDownHandler(sunBox));
		
		//soil conditions
		final ComboBox<String> soilBox = new ComboBox<String>();
		soilBox.getItems().addAll("Clay","Sand","Soil");
		soilBox.setPromptText("Soil Level");
		soilBox.setOnAction(controller.soilTypeDropDownHandler(soilBox));
		
		//moisture conditions
		final ComboBox<String> moistureBox = new ComboBox<String>();
		moistureBox.getItems().addAll("Wet","Average","Dry");
		moistureBox.setPromptText("Moisture Level");
		moistureBox.setOnAction(controller.moistureLevelDropDownHandler(moistureBox));
		
		//input validation text
		Label validationCon = new Label("");
		validationCon.setId("error");
		
		HBox dropDowns = new HBox(sunBox, soilBox, moistureBox);
		dropDowns = ui.createHBoxWithButtons(dropDowns, Pos.CENTER);
		VBox center = new VBox(dropDowns, validationCon);
		center.setAlignment(Pos.CENTER);
		container.setCenter(center);
		
		//garden button
		Button gardenButton = ui.createRestrictedButton(controller, PageIdentifier.GARDEN, "Create Garden", validationCon);
		
		//Options button
		Button Options = ui.createButton(controller, PageIdentifier.OPTIONS, "Options");
		
		//back button
		Button backButton = ui.createButton(controller, PageIdentifier.BUDGETANDSIZE, "Back");
		
		HBox buttons = new HBox(backButton, Options, gardenButton);
		buttons = ui.createHBoxWithButtons(buttons, Pos.CENTER);
		container.setBottom(buttons);
		
		//sets text if model has been set already
		if(model.getSunLevel() != null && model.getSoilType() != null && model.getMoistureLevel() != null) {
			sunBox.setPromptText(model.getSunLevel());
			soilBox.setPromptText(model.getSoilType());
			moistureBox.setPromptText(model.getMoistureLevel());
		}
		
		
		return container;	
	}
}
