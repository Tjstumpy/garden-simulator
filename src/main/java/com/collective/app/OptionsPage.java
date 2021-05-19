package com.collective.app;

import javax.swing.event.ChangeEvent;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.OptionsPageModel;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * This is the class for the options page
 * @author Aaron
 *
 */
public class OptionsPage extends Window {
	/**
	 * this is the constuctor for the OptionsPage
	 * @param con, this is a instance of the controller
	 * @author Aaron
	 */
	public OptionsPage(Controller con) {
		super(con);
	}
	/**
	 * This is the bulid page for the option page that has the volume slider, the mute button and the full screen integration
	 * @param next, this is the page that the back button will change to
	 * @return Scene, this what is going to be displayed in the stage
	 * @author Aaron
	 */
	public BorderPane buildPage(PageIdentifier next) {
		OptionsPageModel model = controller.getOptionsPageModel();
		BorderPane container = new BorderPane();
		HBox title = ui.HBoxWithText("Options", Pos.CENTER, 75);
		container.setTop(title);
		
		//volume option setup
		Label volume = new Label("Volume:");
		volume.setFont(new Font(30));
		HBox volumeText = new HBox(volume);

		volumeText.setAlignment(Pos.CENTER);
		Slider volumeSlider = new Slider(0, 100, 10);
	    volumeSlider.setPrefWidth(width/4);
		volumeSlider.setShowTickLabels(true);
		volumeSlider.setId("options");
		HBox slider = new HBox(volumeSlider);
		slider.setAlignment(Pos.CENTER);
		volumeSlider.setValue(model.getVolume());
		
		VBox volumeTextSlider = new VBox(volumeText, slider);
		
		//check boxes
		CheckBox mute = new CheckBox(new Label("Mute").getText());
		mute.setId("options");
		CheckBox fullScreen = new CheckBox("Fullscreen ");
		fullScreen.setId("options");
		CheckBox darkMode = new CheckBox("Dark Mode");
		darkMode.setId("options");
		mute.setSelected(model.isMute());
		fullScreen.setSelected(model.isFullscreen());
		darkMode.setSelected(model.isDarkMode());
		
		HBox volumeOption = new HBox(volumeTextSlider, mute);
		volumeOption.setAlignment(Pos.CENTER);
		//vertical alignment
		VBox vbox = new VBox(fullScreen, darkMode, volumeOption);
		vbox = ui.createVBoxWithButtons(vbox, Pos.CENTER);
		container.setCenter(vbox);
		
		//functionality
		volumeSlider.setOnMouseDragged((controller.volumeSliderHandler(volumeSlider)));
		volumeSlider.setOnMouseClicked((controller.volumeSliderHandler(volumeSlider)));
		fullScreen.setOnAction(controller.fullscreenCheckboxHandler());
		mute.setOnAction(controller.muteCheckboxHandler());
		darkMode.setOnAction(controller.darkModeCheckboxHandler(darkMode));

		//back button
		HBox back = ui.createBackButton(controller, next);
		container.setBottom(back);
		
		return container;	
	}
}
