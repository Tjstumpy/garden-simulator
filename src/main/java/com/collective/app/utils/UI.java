package com.collective.app.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.collective.app.Controller;
import com.collective.app.consts.PageIdentifier;
/**
 * This is the class that contains all of the functions most commonly used for the buttons and h/v boxes
 * @author Aaron
 *
 */
public class UI {
	/**
	 * this value is for the padding
	 */
	private final int Padding = 10;
	/**
	 * this value is for the spacing between buttons
	 */
	private final int spacing = 10;
	/**
	 * this is the width of the application window
	 */
	private int width;
	/**
	 * this is the height of the application window
	 */
	private int height;
	/**
	 * this is the size for a lot of the elements within the UI
	 */
	private int size;
	
	/**
	 * this is the constructor for the ui to have current values for the width, height, and size
	 * @param width, the current width
	 * @param height, the current height
	 * @param size, the current size
	 * @author Aaron
	 */
	public UI (int width, int height, int size) {
		this.width = width;
		this.height = height;
		this.size = size;
	}
	
	/**
	 * this is the function that creates the title and the title only
	 * @param Title, this is the name of the button that will be used
	 * @param position, this is the plce the button will be on the new hBox that will contain it
	 * @return HBox, this is the box that contains the title
	 * @author Aaron
	 */
	public HBox HBoxWithText(String Title, Pos position, int fontSize) {
		Label title = new Label(Title);
		title.setFont(Font.font(fontSize));
		HBox center = new HBox(title);
		center = createHBoxWithButtons(center, position);
		return center;
	}
	
	/**
	 * This function is what creates the text
	 * @param Title, this is what will be shown within the text
	 * @return Text, this is the text file that was just created
	 * @author Aaron
	 */
	public Label createText(String Title) {
		Label title = new Label(Title);
		title.setFont(new Font(20));
		//title.setX(size);
		//title.setY(size);
		return title;
	}
	
	/**
	 * this creates the back button only
	 * @param controller, this is a instance of controller
	 * @param page, this is the page identifier for the page that the back button will go to when pressed
	 * @return HBox, this is the HBox that will contain the button
	 */
	public HBox createBackButton(Controller controller, PageIdentifier page) {
		Button button = this.createButton(controller, page, "Back");
		button.setId("navigation-button");
		button.setFont(new Font(50));
		HBox back = new HBox(button);
		back = this.createHBoxWithButtons(back, Pos.CENTER);
		return back;
	}
	
	/**
	 * This creates a basic button with only the name and sizing it to be slightly bigger
	 * @param name, this is the name of the button
	 * @return Button, this is the button that was just created
	 * @author Aaron
	 */
	public Button createBasicButton(String name) {
		Button button = new Button(name);
		button.setFont(new Font(50));
		button.setId("navigation-button");
		button.setMinSize(size/10, size/10);
		return button;
	}
	/**
	 * This Creates the button so that it changes pages
	 * @param controller, instance of the controller
	 * @param page, this is the page that the button will go to
	 * @param Title, this is the name of the button when it is created
	 * @return Button, it returns the button that was just created and now can change between pages
	 * @author Aaron
	 */
	public Button createButton(Controller controller, PageIdentifier page, String Title) {
		Button button = createBasicButton(Title);
		if(Title == "Back"){button.setCancelButton(true);}
		button.setOnAction(controller.changePageHandler(page));
		return button;
	}
	
	public Button createRestrictedButton(Controller controller, PageIdentifier page, String Title, Label validation) {
		Button restrictedButton = createBasicButton(Title);
		restrictedButton.setOnAction(controller.restrictedChangePageHandler(page, validation));
		restrictedButton.setFont(new Font(50));
		restrictedButton.setId("navigation-button");
		return restrictedButton;
	}
	
	/**
	 * this formates the HBox provided
	 * @param button, this is the HBox that is formated to the right size
	 * @param position, this is the position of the elements inside of the HBox
	 * @return HBox, this returns the newly formated HBox
	 * @author Aaron
	 */
	public HBox createHBoxWithButtons(HBox button, Pos position){
		button.setSpacing(spacing);
		button.setPadding(new Insets(Padding, Padding, Padding, Padding));
		button.setAlignment(position);
		return button;
	}
	
	/**
	 * This formates the VBox provided
	 * @param button, this is the VBox that is formated to the right size
	 * @param position, this is the position of the elements inside of the VBox
	 * @return VBox, this returns the newly formated VBox
	 * @author Aaron
	 */
	public VBox createVBoxWithButtons(VBox button, Pos position){
		button.setSpacing(spacing);
		button.setPadding(new Insets(Padding, Padding, Padding, Padding));
		button.setAlignment(position);
		return button;
	}
	
	public Button createSidebarButtons(Controller controller, PageIdentifier page, String Title) {
		Button button = createBasicButton(Title);
		if(Title == "back"){button.setCancelButton(true);}
		button.setFont(new Font(14));
		button.setId("sidebar-button");
		button.setOnAction(controller.changePageHandler(page));
		return button;
	}
	/**
	 * this function creates a text Field
	 * @param Prompt this is the text that will be shown when nothing is inside of the textfield
	 * @return TextField the newly created TextField
	 * @author Aaron
	 */
	public TextField createTextField(String Prompt) {
		TextField search = new TextField();
		search.setPromptText(Prompt);
		search.setMaxWidth(size);
		return search;
	}
	
	/**
	 * Creates a text field with a predefined value
	 * @param Prompt Placeholder text when no other input is entered
	 * @param previousValues The string to fill this textField with
	 * @return The created TextField
	 */
	public TextField createTextField(String Prompt, String previousValues) {
		TextField textfield = new TextField();
		textfield.setPromptText(Prompt);
		textfield.setText(previousValues);
		textfield.setMaxWidth(size);
		return textfield;
	}
	
	/**
	 * Creates the condition field box
	 * @param text Label for this field
	 * @param textField TextField to put into this container
	 * @return HBox container
	 */
	public HBox createConditionFields(Label text, TextField textField) {
		text.setFont(new Font(30));
		textField.setMinWidth(width/4);
		textField.setMaxWidth(width/4);
		HBox condition = new HBox(text, textField);
		condition.setAlignment(Pos.CENTER);
		
		return condition;
	}
	
	/**
	 * Styles the passed buttons to be in the "Welcome" style
	 * @param button Button object to style
	 */
	public HBox createConditionFields(Label label, TextField textField, Label units) {
		label.setFont(new Font(30));
		units.setFont(new Font(30));
		textField.setMinWidth(width/4);
		textField.setMaxWidth(width/4);
		HBox condition = new HBox(label, textField, units);
		condition.setAlignment(Pos.CENTER);
		
		return condition;
	}
	
	public void styleWelcomeButtons(Button button) {
		button.setMinSize(width/5, height/5);
		button.setFont(new Font(75));
		button.setId("navigation-button");
	}
	
	public TextFlow buildPopoverMessage() {
		TextFlow flow = new TextFlow();
		
		Text sciTitle = new Text("Scientific Name\n");
		sciTitle.setId("popover-title");
		
		Text comTitle = new Text("Common Name\n");
		comTitle.setId("popover-title");
		
		Text lepsTitle = new Text("Leps Supported\n");
		lepsTitle.setId("popover-title");
		
		Text costTitle = new Text("Cost\n");
		costTitle.setId("popover-title");
		
		// Actual edit fields
		Text sciData = new Text("");
		sciData.setId("popover-text");
		Text comData = new Text("");
		comData.setId("popover-text");
		Text lepsData = new Text("");
		lepsData.setId("popover-text");
		Text costData = new Text("");
		costData.setId("popover-text");
		
		flow.setLineSpacing(5);
		flow.getChildren().addAll(sciTitle, sciData, comTitle, comData, lepsTitle, lepsData, costTitle, costData);
		
		return flow;
	}

}
