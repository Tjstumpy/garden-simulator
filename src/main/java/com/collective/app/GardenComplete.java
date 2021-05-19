package com.collective.app;

import java.util.ArrayList;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.GardenPageModel;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the class that creates the page for the GardenComplete page
 * @author Aaron
 *
 */
public class GardenComplete extends Window{
	/**
	 * This is the constuctor for the GardenComplete class
	 * @param con, This is a instance of controller
	 * @author Aaron
	 */
	public GardenComplete(Controller con) {
		super(con);
	}
	
	/**
	 * This is the function that creates and fills the scene that will be shown in the stage
	 * @return Scene, this is the scene that will be shown in the stage
	 * @author Aaron
	 */
	public BorderPane buildPage() {
		GardenPageModel obj = controller.getGardenPageModel();
		String titleString = "Garden Completed";
		BorderPane container = new BorderPane();
		
		//validation text for saving
		Label validationText = new Label("");
		validationText.setId("error");
		
		Button Options = ui.createButton(controller, PageIdentifier.OPTIONS, "Options");
		Button back = ui.createButton(controller, PageIdentifier.GARDEN, "Back");
		Button BackToStart = ui.createRestrictedButton(controller, PageIdentifier.WELCOME, "Finish", validationText);
		Button Save = ui.createBasicButton("Save");
		Save.setOnAction(controller.saveHandler());
		
		HBox buttons = new HBox(back, Options, BackToStart, Save);
		buttons = ui.createHBoxWithButtons(buttons, Pos.CENTER);
		container.setBottom(buttons);
		
		//changing the title based on leps supported
		if(obj.getTotalSupportedLeps() >= 750) {titleString = "You left no lep behind!";}
		else if(obj.getTotalSupportedLeps() >= 500){titleString = "You did leptastically!";}
		else if(obj.getTotalSupportedLeps() >= 250) {titleString = "You Did a Leptastic Job!";}
		
		//DISPLAY PLANTS
		ArrayList<Plant> plants = obj.getGardenPlants();
		ImageView img;
		Label label;
		ArrayList<HBox> hbox = new ArrayList<HBox>();
		HBox display = new HBox();
		VBox col = new VBox();
		for(int i = 0; i < plants.size(); i++) {
			img = new ImageView();
    		img.setImage(new Image(getClass().getResourceAsStream("/imgs/" + plants.get(i).getSciName() + ".png")));
    		label = new Label(plants.get(i).getComName());
    		label.setFont(Font.font(11));
    		img.setPreserveRatio(true);
        	img.setFitHeight(100);
        	VBox textandimage = new VBox(img, label);
        	textandimage.setPadding(new Insets(0,2,0,2));
        	textandimage.setAlignment(Pos.CENTER);
        	col.getChildren().add(textandimage);
        	col.setAlignment(Pos.CENTER);
        	col.setPadding(new Insets(5,5,5,5));
        	if(i % 2 != 0) {
        		HBox row = new HBox(col);
        		row.setAlignment(Pos.CENTER);
        		hbox.add(row);
        		col = new VBox();
        	}else {
        		HBox row = new HBox(col);
        		row.setAlignment(Pos.CENTER);
        		hbox.add(row);
        	}
		}
		for(HBox i: hbox) {
			i.setAlignment(Pos.CENTER);
			display.getChildren().add(i);
		}
		display.setAlignment(Pos.CENTER);
		ScrollPane plantsPane = new ScrollPane(display);
		plantsPane.setMinHeight(height/3);
		plantsPane.setMinWidth(2*width/5);
		plantsPane.setMaxWidth(2*width/5);
		plantsPane.setFitToHeight(true);

		
		
		Label leps = ui.createText("Leps supported: " +  obj.getTotalSupportedLeps() + "\t");
		Label budget = ui.createText("Budget Remaining: " +  obj.getBudget());
		HBox info = new HBox(leps, budget);
		info.setAlignment(Pos.CENTER);
		VBox EndScreen = new VBox(info, plantsPane, validationText);
		EndScreen = ui.createVBoxWithButtons(EndScreen, Pos.CENTER);
		container.setCenter(EndScreen);
		
		HBox title = ui.HBoxWithText(titleString, Pos.CENTER, 75);
		container.setTop(title);
		
		return container;
	}
}
