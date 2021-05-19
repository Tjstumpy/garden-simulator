package com.collective.app;

import java.util.ArrayList;


import com.collective.app.consts.PageIdentifier;
import com.collective.app.utils.SaveFile;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * this is the class that creates the LoadPage within the project
 * @author Aaron
 *
 */
public class LoadPage extends Window {
//	MouseEvent hover;
//	MouseEvent scroll;
//	ActionEvent load;
//	ActionEvent changePage;
	
	/**
	 * This is the constructor for the loadpage
	 * @param con, this is a instance of controller given to the loadpage
	 * @author Aaron
	 */
	public LoadPage(Controller con) {
		super(con);
	}

	/**
	 * this is the function that is going to create and fill the scene for the LoadPage
	 * @return Scene, this is the scene that will be shown on the stage
	 * @author Aaron and Jason(mostly)
	 */
	public BorderPane buildPage() {
		BorderPane container = new BorderPane();
		HBox title = ui.HBoxWithText("Load", Pos.CENTER, 75);
		container.setTop(title);
		
		
		//load menu scrollpane
		HBox hbox = new HBox();
		ArrayList<VBox> gardenCollection = new ArrayList<VBox>();
		ArrayList<Garden> readOnlyGardenFile = SaveFile.getGardens();
		
		for(int i=0; i < readOnlyGardenFile.size(); i++) {
			Button garden = ui.createBasicButton(readOnlyGardenFile.get(i).getName());
//			System.out.println("Name: " + readOnlyGardenFile.get(i).getName() + " Size: "+ readOnlyGardenFile.size());
			garden.setId("navigation-button");
			garden.setOnAction(controller.loadHandler(readOnlyGardenFile.get(i).getName()));
		//	garden.setOnMouseClicked(controller.deleteSavedGarden(garden));
			HBox gardenHBox = new HBox(garden);
			gardenHBox.setPadding(new Insets(0, 10, 0, 10));
			hbox.getChildren().add(gardenHBox);
			hbox.setAlignment(Pos.CENTER);
			hbox.setPadding(new Insets(10, 10, 10, 10));
			
			if(i % 2 != 0) {
        		VBox vbox = new VBox(hbox);
        		vbox.setAlignment(Pos.CENTER);
        		gardenCollection.add(vbox); 
        		hbox = new HBox();
        	}else {
        		VBox vbox = new VBox(hbox);
        		vbox.setAlignment(Pos.CENTER);
        		gardenCollection.add(vbox);
        	}
		}
		
		VBox savedGardens = new VBox();
		savedGardens.getChildren().clear();
		
		for(VBox i: gardenCollection) {
			i.setAlignment(Pos.CENTER);
			savedGardens.getChildren().add(i);
		}
		savedGardens.setAlignment(Pos.CENTER);
		savedGardens.setMinWidth(3*width/5);
		ScrollPane loadMenu = new ScrollPane(savedGardens);
		loadMenu.setFitToWidth(true);
		container.setCenter(loadMenu);
		loadMenu.setMinWidth(4*width/5);
	    loadMenu.setMaxWidth(4*width/5);
		
		//back button
		HBox back = ui.createBackButton(controller, PageIdentifier.WELCOME);
		
		//options button
		Button options = ui.createButton(controller, PageIdentifier.OPTIONS, "Options");
		options.setId("navigation-button");
		
		HBox buttons = new HBox(back,options);
		buttons = ui.createHBoxWithButtons(buttons, Pos.CENTER);
		container.setBottom(buttons);
		
		return container;	
	}
}
