package com.collective.app;


import java.util.ArrayList;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.GardenPageModel;
import com.collective.app.dtos.UpdateObject;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.VBox;

/**
 * Represents the Garden view of the program, shows the garden grid and plant side bar
 *
 */
public class GardenPage extends Window {
	/**
	 * this is the constructor for the GardenPage
	 * @param con, this is a controller to access some of the functions inside of them
	 * @author Aaron
	 */
	public GardenPage(Controller con) {
		super(con);
	}
	
	Label budgetCounter;
	Label lepsCounter;
	Label validationText;
	
	/**
	 * this is the function that creates and fills in the Scene that will be show in stage
	 * @return Scene, the scene that will be shown on the stage
	 * @author Aaron
	 */
	public BorderPane buildPage() {
		GardenPageModel obj = controller.getGardenPageModel();
		PlantDB db = controller.getPlants();
		ArrayList<Plant> plantDB = db.getPlants();
		ArrayList<Plant> garden = obj.getGardenPlants();
		BorderPane container = new BorderPane();

		Label title = ui.createText(obj.getName());
		title.setPadding(new Insets(0, 5, 0, 5));
		lepsCounter = ui.createText("Leps Supported: " + obj.getTotalSupportedLeps());
		lepsCounter.setPadding(new Insets(0, 5, 0, 5));
		budgetCounter = ui.createText("Budget: $" + obj.getBudget());
		budgetCounter.setPadding(new Insets(0, 5, 0, 5));
		
		Label sunLevel = ui.createText("Sun Level: " + obj.getSunLevel());
		sunLevel.setPadding(new Insets(0, 5, 0, 5));
		Label moistureLevel = ui.createText("Moisture Level: " + obj.getMoistureLevel());
		moistureLevel.setPadding(new Insets(0, 5, 0, 5));
		Label soilType = ui.createText("Soil Type: " + obj.getSoilType());
		soilType.setPadding(new Insets(0, 5, 0, 5));
		
		HBox Title = new HBox(title, sunLevel, moistureLevel, soilType, lepsCounter, budgetCounter);
		Title = ui.createHBoxWithButtons(Title, Pos.CENTER);
		Title.setId("garden-info");
		Title.setMinHeight(70);
		Title.setMaxHeight(70);
		
		// Hover popover
		TextFlow message = ui.buildPopoverMessage();
		message.setTextAlignment(TextAlignment.LEFT);
		VBox popover = new VBox(message);
		message.setPadding(new Insets(5, 5, 5, 5));
		message.setId("popover");
		message.setMinWidth(250);
		message.setMinHeight(300);
		popover.setVisible(false);
		
		
		//back button
		Button back = ui.createSidebarButtons(controller, PageIdentifier.CONDITIONS, "Back");
	
		//options button
		Button options = ui.createSidebarButtons(controller, PageIdentifier.OPTIONS, "Options");

		//finish button
		Button finished = ui.createSidebarButtons(controller, PageIdentifier.COMPLETE, "Finish");

		//grid
		GridPane grid = new GridPane();
		//grid.setStyle("-fx-grid-lines-visible: true");
		grid.setAlignment(Pos.CENTER);
		grid.setPrefHeight(height);
		grid.setMinWidth(4*width/5);
		
		//fills grid with rectangles if no plants are added to garden
		for(int i=0; i < obj.getLength()*3; i++) {
			for(int j=0; j < obj.getWidth()*3; j++) {
				Rectangle node = new Rectangle(controller.getScale(), controller.getScale());
				node.setOnDragOver(controller.dragOverHandler());
				node.setOnDragEntered(controller.dragEnterGridHandler());
				node.setOnDragExited(controller.dragExitGridHandler(node));
				node.setOnDragDropped(controller.dragDroppedHandler(grid));

				node.setStroke(Color.BLACK);
				node.setFill(Color.GREEN);
				node.setStrokeWidth(0.8);
				grid.add(node, i, j);
			}
		}
		
		//fills grid with appropriate plants at the appropriate locations
		if(!(garden.isEmpty())) {
			for(Plant i: garden) {
				ImageView img = new ImageView();
	    		img.setImage(new Image(getClass().getResourceAsStream("/imgs/" + i.getSciName() + ".png")));

	    		img.setFitWidth(controller.getScale() * i.size);
		        img.setFitHeight(controller.getScale() * i.size);

		        int offsetX = i.x - (i.size / 2);
	            int offsetY = i.y - (i.size / 2);
	    		int[] pos = {i.x, i.y};
	            img.setOnDragDetected(controller.dragMoveHandler(img, i.SciName, i.size, pos));
	            img.setOnMouseClicked(controller.plantClickedHandler(i.SciName, grid));
	            grid.add(img, offsetX, offsetY);
	            GridPane.setColumnSpan(img, i.size);
	            GridPane.setRowSpan(img, i.size);
	            
		        img.toFront();
			}
			
		}
			
		VBox test = new VBox(Title, grid);
		test.setAlignment(Pos.CENTER);
		container.setCenter(test);
		
		
		//side bar
		TextField search = ui.createTextField("Search:");
		CheckBox woody = new CheckBox("Woody");
		woody.setFont(new Font(11));
		woody.setPadding(new Insets(0, 25, 0, 0));
		CheckBox herb = new CheckBox("Herbaceous");
		herb.setFont(new Font(11));

		
		//adding plants to side bar
		ArrayList<VBox> plantView = new ArrayList<VBox>();

    	ImageView img = new ImageView();
    	Label label = new Label();
    	HBox hbox = new HBox();
		VBox allPlantsBox = new VBox();
    	controller.loadSidebar(popover, plantView, img, label, hbox, allPlantsBox, search, false, false);
    	
    	//setonkeypressed for real time search
    	search.setOnKeyPressed(controller.searchHandler(popover, search, woody, herb, plantView, img, label, hbox, allPlantsBox));
    	woody.setOnAction(controller.plantTypeCheckboxHandler(popover, woody, herb, plantView, img, label, hbox, allPlantsBox, search));
		herb.setOnAction(controller.plantTypeCheckboxHandler(popover, woody, herb, plantView, img, label, hbox, allPlantsBox, search));
		
		HBox checkBox = new HBox(woody, herb);
		checkBox.setAlignment(Pos.CENTER);
		woody.setPadding(new Insets(0, 25, 0, 0));
		allPlantsBox.setAlignment(Pos.CENTER);
	    ScrollPane scroll = new ScrollPane(allPlantsBox);
	    scroll.setFitToWidth(true);
	    HBox buttons = new HBox(options, finished);
	    if(obj.getName().toLowerCase().equals(controller.getDebugging())) {
	    	buttons = new HBox(back, options, finished);
	    }
		buttons = ui.createHBoxWithButtons(buttons, Pos.CENTER);
	    
	    VBox sidebar = new VBox(search, checkBox, scroll, buttons);
	    container.setLeft(sidebar);
	    scroll.setPrefHeight(height+200);
	    //sidebar.setMinWidth(width/6);
	    sidebar.setMaxWidth(width/6);
	    
		popover.relocate((width/6), 78);
		container.getChildren().add(popover);
	    
	    //STYLING
	    buttons.setId("sidebar");
	    checkBox.setId("sidebar");
	    
		return container;
	}
	
	/**
	 * Feeds the page updated model information in a DTO container and updates page elements accordingly
	 * @param obj The DTO for this specific page
	 * @author Jaydon
	 */
	@Override
	public void updatePage(UpdateObject obj) {
		GardenPageModel dto = (GardenPageModel) obj;
		budgetCounter.setText("Budget: $" + dto.getBudget());
		lepsCounter.setText("Leps Supported: " + dto.getTotalSupportedLeps());
	}

}
