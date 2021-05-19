package com.collective.app;

import javax.swing.event.ChangeEvent;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.GardenPageModel;
import com.collective.app.dtos.OptionsPageModel;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
/**
 * This is the Budget and Size class that will create the scene for that page
 * @author Aaron
 *
 */
public class BudgetAndSize extends Window{
//	MouseEvent hover;
//	MouseEvent clickInBox;
//	ActionEvent changePage;
//	ChangeEvent propertyChange;
	
	/**
	 * This is the constructor for the BudgetAndSize
	 * @param con, this is a instance of controller
	 * @author Aaron
	 */
	public BudgetAndSize(Controller con) {
		super(con);
	}
	/**
	 * This is the buldpage for the budgetAndSize
	 * @return Scene, this is the scene that is shown on the stage
	 * @author Jason (mostly)
	 */
	public BorderPane buildPage() {
		BorderPane container = new BorderPane();
		GardenPageModel model = controller.getGardenPageModel();

		TextField nameField;
		TextField budgetField;
		TextField lengthField;
		TextField widthField;
		
		HBox title = ui.HBoxWithText("Budget and Garden Size", Pos.CENTER, 75);
		container.setTop(title);
		
		//input validation text
		Label validationText = new Label("");
		validationText.setId("error");

		//condition button
		Button conditionsButton = ui.createRestrictedButton(controller, PageIdentifier.CONDITIONS, "Select Conditions", validationText);
		
		//options button
		Button Options = ui.createButton(controller, PageIdentifier.OPTIONS, "Options");

		//back button
		Button backButton = ui.createButton(controller, PageIdentifier.WELCOME, "Back");
		
		HBox buttons = new HBox(backButton, Options, conditionsButton);
		buttons.setMinWidth(width);
		
		buttons = ui.createHBoxWithButtons(buttons, Pos.CENTER);
		
		container.setBottom(buttons);
		
		//sets text if model has not been set
		if(model.getName() == null )
			nameField = ui.createTextField("garden name");
		else //sets text if model has not been set
			nameField = ui.createTextField("garden name", model.getName());

		if(model.getBudget() == 0 )
			budgetField = ui.createTextField("budget");
		else
			budgetField = ui.createTextField("budget", String.valueOf(model.getBudget()));

		if(model.getLength() == 0 )
			lengthField = ui.createTextField("length");
		else
			lengthField = ui.createTextField("length", String.valueOf(model.getLength()));

		if(model.getWidth() == 0) 
			widthField = ui.createTextField("width");
		else 
			widthField = ui.createTextField("width", String.valueOf(model.getWidth()));		

		Label nameText = new Label("Name:   ");
		nameField.focusedProperty().addListener(controller.nameChangeListener(nameField));
		HBox nameCondition = ui.createConditionFields(nameText, nameField);
		
		Label budgetText = new Label("Budget: ");
		budgetField.focusedProperty().addListener(controller.budgetChangeListener(budgetField));
		HBox budgetCondition = ui.createConditionFields(budgetText, budgetField);

		Label lengthText = new Label("   Length:  ");
		lengthField.focusedProperty().addListener(controller.lengthChangeListener(lengthField));
		Label lengthUnits = new Label(" ft");
		HBox lengthCondition = ui.createConditionFields(lengthText, lengthField, lengthUnits);

		Label widthText = new Label("   Width:   ");
		widthField.focusedProperty().addListener(controller.widthChangeListener(widthField));
		Label widthUnits = new Label(" ft");
		HBox widthCondition = ui.createConditionFields(widthText, widthField, widthUnits);

		
		VBox BudgetAndSize = new VBox(nameCondition, budgetCondition, lengthCondition, widthCondition, validationText);
		BudgetAndSize = ui.createVBoxWithButtons(BudgetAndSize, Pos.CENTER);
		container.setCenter(BudgetAndSize);
		
		return container;	
	}
	
}


