package com.collective.app;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.consts.PlantType;
import com.collective.app.dtos.*;
import com.collective.app.utils.PositionUtils;
import com.collective.app.utils.SaveFile;
import com.collective.app.utils.UI;
import com.collective.app.dtos.GardenPageModel;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * this is the controller for this program
 * @author Aaron
 *
 */
public class Controller extends Application {
	/**
	 * this is the view within the controller
	 */
	private View view;
	/**
	 * this is the model within the controller
	 */
	private Garden model;
	/**
	 * this is the plant Database and where all of the plants will be for the garden
	 */
	private PlantDB plantdb;
	/**
	 * The scale factor to use for this garden based on the width and length
	 */
	public static double scale;
	/**
	 * Flag used for the drag handlers
	 */
	private boolean validDropTile = false;
	
	/**
	 * this is the main function that starts all everything
	 * @param args, not sure what this is used for
	 * @author Aaron
	 */
    public static void main(String[] args) {
        launch();
    }
    
    public String getDebugging() {return model.getDebugging();}
    public double getScale() {return scale;}
    
    public void setScale(int scaleint) {scale = scaleint;}
    @Override
    /**
     * This a overridden start function
     * @param stage, this is the stage that will contain all of the scenes
     * @author Aaron
     */
    public void start(Stage stage) {
    	plantdb = new PlantDB();
    	view = new View(this, stage);
    	//temp change so that muted is default for testing 
    	model = new Garden(null, 0, 0, 0, null, null, null, 0, 10, false, false, false);
    }
    /**
     * this returns a new gardenPageModel which is a data transferring object
     * @return a new gardenPageModel
     * @author Aaron
     */
    public GardenPageModel getGardenPageModel() {
    	return new GardenPageModel(model.getTotalSupportedleps(), model.getBudget(), model.getLength(), 
    			model.getWidth(), model.getName(), model.getSunLevel(), model.getSoilType(), 
    			model.getMoistureLevel(), model.getPlants());
    }
    /**
     * This returns a new optionsPageModel which is a data transferring object
     * @return a new optionsPageModel
     * @author Aaron
     */
    public OptionsPageModel getOptionsPageModel() {
    	return new OptionsPageModel(model.getVolume(), model.getMuted(), model.getFullscreen(), model.getDarkMode());
    }
    
    public PlantDB getPlants(){
    	return plantdb;
    }
    
    public void loadSidebar(VBox popover, ArrayList<VBox> plantView, ImageView img, Label label, HBox hbox, VBox allPlantsBox, TextField search, boolean woody, boolean herb) {
    	plantView.clear();
		allPlantsBox.getChildren().clear();
		hbox = new HBox();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		if((woody && herb) || (!woody && !herb) && (search.getText().length() == 0)) {
			plants = plantdb.getPlants();
			}else if(woody) {
			plants = plantdb.getPlantsByType(PlantType.WOODY);
		}else if(herb){
			plants = plantdb.getPlantsByType(PlantType.HERBACIOUS);
		}else {
			plants = plantdb.getPlants();
		}
		
		//filtering conditions
		int temp = plants.size()-1;
		for(int i = temp; i >= 0; i--) {
			if(!plants.get(i).getMoistureLevel().equals(model.getMoistureLevel()) && !(model.getName().toLowerCase().equals(getDebugging()) && model.getMoistureLevel() == null)) {
				plants.remove(plants.get(i));
			}else if(!plants.get(i).getSoilType().equals(model.getSoilType()) && !(model.getName().toLowerCase().equals(getDebugging()) && model.getSoilType() == null)) {
				plants.remove(plants.get(i));
			}else if(!plants.get(i).getSunLevel().equals(model.getSunLevel()) && !(model.getName().toLowerCase().equals(getDebugging()) && model.getSunLevel() == null)) {
				plants.remove(plants.get(i));
			}
		}
			
		//searching
		if(search.getText().length() != 0) {
			temp = plants.size()-1;

			for(int i = temp; i >= 0; i--){
				if(!plants.get(i).getComName().toLowerCase().contains(search.getText().toLowerCase())) {
					if(!plants.get(i).getSciName().toLowerCase().contains(search.getText().toLowerCase())) {
						if(!plants.get(i).getFamily().toLowerCase().contains(search.getText().toLowerCase())) {
							plants.remove(plants.get(i));
						}
					}
				}
			}
		}
		
		//image rendering
		for(int i = 0; i < plants.size(); i++) {
			Plant p = plants.get(i);
			
    		img = new ImageView();

    		img.setImage(new Image(getClass().getResourceAsStream("/imgs/" + p.getSciName() + ".png")));
    		label = new Label(p.getComName());
    		label.setFont(Font.font(11));
    		img.setPreserveRatio(true);
        	img.setFitHeight(100);
        	img.setOnDragDetected(dragCloneHandler(img, p.getSciName(), p.getSize()));
        	img.setOnMouseEntered(hoverEnterHandler(popover, p));
        	VBox textandimage = new VBox(img, label);
        	textandimage.setPadding(new Insets(0,2,0,2));
        	textandimage.setAlignment(Pos.CENTER);
        	hbox.getChildren().add(textandimage);
        	hbox.setPadding(new Insets(5,5,5,5));
        	//hbox.setId("sidebar");
        	hbox.setAlignment(Pos.CENTER);
        	if(i % 2 != 0) {
        		VBox vbox = new VBox(hbox);
        		vbox.setAlignment(Pos.CENTER);
        		plantView.add(vbox); 
        		hbox = new HBox();
        	}else {
        		VBox vbox = new VBox(hbox);
        		vbox.setAlignment(Pos.CENTER);
        		plantView.add(vbox);
        	}
    	}
		for(int i = 0; i < plantView.size(); i++) {
    		allPlantsBox.getChildren().add(plantView.get(i));
    	}
		allPlantsBox.setAlignment(Pos.CENTER);
		allPlantsBox.setOnMouseExited(hoverExitHandler(popover));
	}
	
    /**
     * this is the eventHandler when a button to change pages is pressed
     * @param pageIdentifier, this is the page that the use wishes to go to
     * @return it returns nothing
     * @author Aaron
     */
	public EventHandler<ActionEvent> changePageHandler(PageIdentifier pageIdentifier){
		 return (e) -> {
			 if(pageIdentifier == PageIdentifier.CONDITIONS && model.getName().toLowerCase().equals(getDebugging())) {
				 model.sunLevel = null;
				 model.soilType = null;
				 model.moistureLevel = null;
			 }
			 view.changePage(pageIdentifier);
		 };
	}
	
	/**
	 * this is the eventhandler for when a button to change page is pressed but the user has not met certain conditions yet
	 * @param Pageidentifer, where the page intends to go
	 * @return nothing
	 * @author Jason
	 */	
	public EventHandler<ActionEvent> restrictedChangePageHandler(PageIdentifier pageIdentifier, Label validation){
		return (e) -> {
			 if(pageIdentifier == PageIdentifier.CONDITIONS) {
				 if(model.getLength() == 0 || model.getName() == null || model.getWidth() == 0 || 
						 model.getBudget() == 0) {
					 validation.setText("Please be sure to enter values for all parameters");
				 }
				 
				 else if(model.getBudget() < 6 || model.getBudget() > 1000000) 
					 validation.setText("Please enter a valid budget, ($6-$1,000,000)");
				 
				 else if(model.getLength() < 3 || model.getLength() > 38)
					 validation.setText("Please enter a valid length, (3-40 feet)");
				 
				 else if(model.getWidth() <3 || model.getWidth() > 21)
					 validation.setText("Please enter a valid width, (3-20 feet)");
				 
				 else if(SaveFile.checkForName(model.getName())) 
					validation.setText("There is already a garden with this name. Please enter a new one");
				 
				 else
					 view.changePage(pageIdentifier);
			 }
			 else if (pageIdentifier == PageIdentifier.GARDEN) {
				 if (!model.getName().toLowerCase().equals(getDebugging()) && ( model.sunLevel == null || model.soilType == null || model.moistureLevel == null)) {
					 validation.setText("Please be sure to choose all conditions");
				 }
				 else {
					 setScale(260/model.getWidth());
					 if (260/model.getWidth() >= 460/model.getLength()) {setScale(460/model.getLength());}
					 view.changePage(pageIdentifier);
				 }
			 }
			 
			 else if(pageIdentifier == PageIdentifier.WELCOME) {
				 if(SaveFile.checkForGarden(model) || validation.getText().equals("Please be sure to save your garden"))
					 view.changePage(pageIdentifier);
				 else
					 validation.setText("Please be sure to save your garden");
			 }
		};
	}


	/**
	 * this is the eventHandler for when someone wants to terminate the program
	 * @return it return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> exitHandler(){
		return (e) -> {
			view.closeView();
		};
	}
	
	/**
	 * This is the eventhandler for when the user changes the volume from the silder
	 * @param slider, this is the slider that the use will be changing to change the volume
	 * @return void 
	 * @author Aaron
	 */
	EventHandler<MouseEvent> volumeSliderHandler(Slider slider){
		return (e) -> {
			double volume = (int)slider.getValue();
			view.player.setVolume(volume/100);
			model.setVolume((int)volume);
		};
	}
	
	/**
	 * this is the eventhandler for when the user changes the application to full screen from the checkbox
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> fullscreenCheckboxHandler(){
		return (e) -> {
			if(view.stage.isFullScreen()) {
				view.stage.setFullScreen(false);
				model.setFullscreen(false);
			}else {
				view.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
				view.stage.setFullScreen(true);
				model.setFullscreen(true);
			}
		};
	}
	
	EventHandler<ActionEvent> darkModeCheckboxHandler(CheckBox check){
		return (e) -> {
			if(check.isSelected()) {
				view.stage.getScene().getStylesheets().remove(getClass().getResource("/styling/light.css").toExternalForm());
				view.stage.getScene().getStylesheets().add(getClass().getResource("/styling/dark.css").toExternalForm());
				model.setDarkMode(true);
			}else {
				view.stage.getScene().getStylesheets().remove(getClass().getResource("/styling/dark.css").toExternalForm());
				view.stage.getScene().getStylesheets().add(getClass().getResource("/styling/light.css").toExternalForm());
				model.setDarkMode(false);
			}
		};
	}
	/**
	 * this is the eventhandler for when the user changes it mute for the application for the application
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> muteCheckboxHandler(){
		return (e) -> {
			if(view.player.isMute()) {
				view.player.setMute(false);
				model.setMuted(false);
			}else {
				view.player.setMute(true);
				model.setMuted(true);
			}
		};
	}
	
	EventHandler<ActionEvent> plantTypeCheckboxHandler(VBox popover, CheckBox woody, CheckBox herb, ArrayList<VBox> plantView, ImageView img, Label label, HBox hbox, VBox allPlantsBox, TextField search){
		return (e) -> {	
			loadSidebar(popover, plantView, img, label, hbox, allPlantsBox, search, woody.isSelected(), herb.isSelected());
		};
	}
	
	EventHandler<KeyEvent> searchHandler(VBox popover, TextField search, CheckBox woody, CheckBox herb,  ArrayList<VBox> plantView, ImageView img, Label label, HBox hbox, VBox allPlantsBox){
		return (e) -> {
			loadSidebar(popover, plantView, img, label, hbox, allPlantsBox, search, woody.isSelected(), herb.isSelected());
		};
	}
	
	public EventHandler<MouseEvent> plantClickedHandler(String sciName, GridPane grid) {
		return (e) -> {
			Plant p = plantdb.findPlantByScentificName(sciName);
			Node plantNode = e.getPickResult().getIntersectedNode();
			
			int x = PositionUtils.calculateXPos(plantNode);
			int y = PositionUtils.calculateYPos(plantNode);
			
			if (e.getButton().equals(MouseButton.SECONDARY)) {
				model.changeBudget(p.getCost());
				model.changeTotalSupportedLeps(-p.getLepSupported());
				model.removePlant(x, y);
				view.triggerPageUpdate();
				
				grid.getChildren().remove(plantNode);
			}
		};
	}
	
	public EventHandler<MouseEvent> hoverEnterHandler(VBox popover, Plant p) {
		return (e) -> {
			// We know the child is the text area
			TextFlow flow = (TextFlow) popover.getChildren().get(0);
			
			if (model.getDarkMode()) {
				flow.setStyle(getClass().getResource("/styling/dark.css").toExternalForm());
			} else {
				flow.setStyle(getClass().getResource("/styling/light.css").toExternalForm());
			}
			
			Text sci = (Text) flow.getChildren().get(1);
			Text com = (Text) flow.getChildren().get(3);
			Text leps = (Text) flow.getChildren().get(5);
			Text cost = (Text) flow.getChildren().get(7);
			
			sci.setText(" " + p.getSciName() + "\n");
			com.setText(" " + p.getComName() + "\n");
			leps.setText(" " + p.getLepSupported() + "\n");
			cost.setText(" " + p.getCost() + "\n");
			
			popover.setVisible(true);
		};
	}
	
	public EventHandler<MouseEvent> hoverExitHandler(VBox popover) {
		return (e) -> {
			popover.setVisible(false);
		};
	}
	
	/**
	 * Drag handler that clones the dragged Plant 
	 * @param img ImageView that will be duplicated
	 * @param sciName Plant scientific name
	 * @param size Plant size
	 * @author Jaydon
	 */
	public EventHandler<MouseEvent> dragCloneHandler(ImageView img, String sciName, int size) {
		return (e) -> {
			Plant p = plantdb.findPlantByScentificName(sciName);
			int budget = model.getBudget();
			
			if ((budget - p.getCost()) < 0) {
				System.out.println("Not enough money!");
				return;
			}
			
	    	Dragboard db = img.startDragAndDrop(TransferMode.COPY);

            ClipboardContent cbContent = new ClipboardContent();
            cbContent.putImage(img.getImage());
            // Metadata for the drag
            cbContent.putString(sciName + "," + size);
            db.setContent(cbContent);
		};
	}
	/**
	 * Drag handler that moves a dragged Plant already placed on the grid
	 * @param img ImageView that will be duplicated
	 * @param sciName Plant scientific name
	 * @param size Plant size
	 * @param pos array with the original x and y the Plant is dragged from
	 * @author Jaydon
	 */
	EventHandler<MouseEvent> dragMoveHandler(ImageView img, String sciName, int size, int[] pos) {
		return (e) -> {
	    	Dragboard db = img.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent cbContent = new ClipboardContent();
            cbContent.putImage(img.getImage());
            // Metadata for the drag
            cbContent.putString(sciName + "," + size + "," + pos[0] + "," + pos[1]);
            db.setContent(cbContent);
		};
	}
	/**
	 * Drag handler that determines if a Plant can be dropped on the hovered Node
	 * @author Jaydon
	 */
	EventHandler<DragEvent> dragOverHandler() {
		return new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	        	Node target = event.getPickResult().getIntersectedNode();
	        	
	        	if(event.getGestureSource() != target && validDropTile && event.getDragboard().hasImage()){
	                event.acceptTransferModes(TransferMode.ANY);
	            }
	            event.consume();
	        }
	    };
	}
	/**
	 * Drag handler that highlights the node currently selected
	 * @param target the grid node
	 * @author Jaydon
	 */
	EventHandler<DragEvent> dragEnterGridHandler() {
		return new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	        	Node target = event.getPickResult().getIntersectedNode();
	        	
	        	int x = PositionUtils.calculateXPos(target);
	        	int y = PositionUtils.calculateYPos(target);
	        	
	        	Dragboard db = event.getDragboard();
	        	String[] metadata = db.getString().split(",");
	        	int size = Integer.parseInt(metadata[1]);
	            
	            if(event.getGestureSource() != target && event.getDragboard().hasImage()){
	                target.setOpacity(0.7);
	                validDropTile = !model.checkForPlant(x, y, size);
	            }
	            event.consume();
	        }
	    };
	}
	/**
	 * Drag handler that removes highlights to the node after leaving that space
	 * @param target the grid node
	 * @author Jaydon
	 */
	EventHandler<DragEvent> dragExitGridHandler(Node target) {
		return new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	            if(event.getGestureSource() != target && event.getDragboard().hasImage()){
	                target.setOpacity(1);
	            }
	            event.consume();
	        }
	    };
	}
	/**
	 * Drag handler that places a plant once it has successfully been dragged
	 * @param grid the garden grid
	 * @author Jaydon
	 */
	EventHandler<DragEvent> dragDroppedHandler(GridPane grid) {
		return new EventHandler<DragEvent>() {
	        public void handle(DragEvent event) {
	            Dragboard db = event.getDragboard();
	            Node target = event.getPickResult().getIntersectedNode();
	            
	            int x = PositionUtils.calculateXPos(target);
	            int y = PositionUtils.calculateYPos(target);	          
	        	String[] metadata = db.getString().split(",");
	        	String sciName = metadata[0];
            	int size = Integer.parseInt(metadata[1]);
	            int offsetX = PositionUtils.calculateOffsetPos(x, size);
	            int offsetY = PositionUtils.calculateOffsetPos(y, size);
	            
	            ImageView newImg = new ImageView(db.getImage());
	            newImg.setFitWidth(getScale() * size);
	            newImg.setFitHeight(getScale() * size);
	            
	            // Add the move handler
	            int[] pos = {x, y};
	            newImg.setOnDragDetected(dragMoveHandler(newImg, sciName, size, pos));
	            newImg.setOnMouseClicked(plantClickedHandler(sciName, grid));
	            
	            grid.add(newImg, offsetX, offsetY);
	            GridPane.setColumnSpan(newImg, size);
	            GridPane.setRowSpan(newImg, size);
	        
	            if (event.getTransferMode().equals(TransferMode.COPY)) {        
		            // Add a new plant of this type to the Garden at this position
		            Plant p = plantdb.findPlantByScentificName(sciName);
		            p.setSize(size);
		            p.setX(x);
		            p.setY(y);
		            model.addPlant(p);
		            
					model.changeBudget(-p.getCost());
					model.changeTotalSupportedLeps(p.getLepSupported());
					view.triggerPageUpdate();
	            } else {
	            	// Move this plant to a new position and update it
		            int[] orgPos = {Integer.parseInt(metadata[2]), Integer.parseInt(metadata[3])};
		            int offsetOrgX = PositionUtils.calculateOffsetPos(orgPos[0], size);
		            int offsetOrgY = PositionUtils.calculateOffsetPos(orgPos[1], size);
		            
		            
		            Plant p = model.getPlantByPos(orgPos[0], orgPos[1]);
		            p.setX(x);
		            p.setY(y);
		            
		            removeNodeFromGrid(grid, offsetOrgX, offsetOrgY);
	            }
	            
	            event.setDropCompleted(true);
	            event.consume();
	        }
	    };
	}
	/**
	 * this saves the current garden with the use is done
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> saveHandler(){
		return (e) -> { 
			model.save();
			view.changePage(PageIdentifier.WELCOME);
		};
	}
	/**
	 * this loads the garden selected by the user 
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> loadHandler(String name){
		return (e) -> {
			model.load(name);
			setScale(260/model.getWidth());
			if(260/model.getWidth() >= 460/model.getLength()) {setScale(460/model.getLength());}
			view.changePage(PageIdentifier.GARDEN);

		};
	}
	/**
	 * this gets the desired sunLevel form the drop down menu
	 * @param sunBox, the object that contains the new sunLevel
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> sunLevelDropDownHandler(ComboBox sunBox){
		return (e) -> {
			int selectedIndexSun = sunBox.getSelectionModel().getSelectedIndex();
		    Object selectedItemSun = sunBox.getSelectionModel().getSelectedItem();
		    if(model.getName().toLowerCase().equals(getDebugging())) {System.out.println(selectedItemSun);}
		    model.setSunLevel((String)selectedItemSun);
		};
	}
	/**
	 * this gets the desired soiltype from the drop down menu
	 * @param soilBox, the object that contains the new soiltype
	 * @return void
	 * @author Aaron
	 */
	
	EventHandler<ActionEvent> soilTypeDropDownHandler(ComboBox soilBox){
		return (e) -> {
			int selectedIndexSoil = soilBox.getSelectionModel().getSelectedIndex();
		    Object selectedItemSoil = soilBox.getSelectionModel().getSelectedItem();
		    if(model.getName().toLowerCase().equals(getDebugging())) {System.out.println(selectedItemSoil);}
		    model.setSoilType((String)selectedItemSoil);
		};
	}
	/**
	 * this gets the desired moisture level from the drop down menu
	 * @param moistureBox, the object that contains the new moisturelevel
	 * @return void
	 * @author Aaron
	 */
	EventHandler<ActionEvent> moistureLevelDropDownHandler(ComboBox moistureBox){
		return (e) -> {
			int selectedIndexMoisture = moistureBox.getSelectionModel().getSelectedIndex();
		    Object selectedItemMoisture = moistureBox.getSelectionModel().getSelectedItem();
		    if(model.getName().toLowerCase().equals(getDebugging())) {System.out.println(selectedItemMoisture);}
		    model.setMoistureLevel((String)selectedItemMoisture);
		};
	}
	/**
	 * this changes the name for the model
	 * @return void
	 * @author Aaron
	 */
	ChangeListener<Boolean> nameChangeListener(TextField name){
		return (observable, oldValue, newValue) -> {
			if(!newValue) 
				model.setName(name.getText());
		};	
	}

	/**
	 * this changes the budget for the model
	 * @return void
	 * @author Aaron
	 */
	ChangeListener<Boolean> budgetChangeListener(TextField budget){
		return (observable, oldValue, newValue) -> {
			if(!newValue)
				model.setBudget(Integer.parseInt(budget.getText()));	
		};	
	}
	/**
	 * this changes the length for the model when provided by the textfield
	 * @return void
	 * @author Aaron
	 */
	ChangeListener<Boolean> lengthChangeListener(TextField length){
		return (observable, oldValue, newValue) -> {
			if(!newValue) {
				model.setLength(Integer.parseInt(length.getText()));
			}
		};	
	}
	/**
	 * this changes the width for the model when provided by the textfield
	 * @return void
	 * @author Aaron
	 */
	
	
	ChangeListener<Boolean> widthChangeListener(TextField width){
		return (observable, oldValue, newValue) -> {
			if(!newValue) 
				model.setWidth(Integer.parseInt(width.getText()));
		};	
	}
	
	/**
	 * this resets the model when creating a new garden, keeps options at the same values
	 * @return EventHandler
	 * @author Jason
	 */
	EventHandler<ActionEvent> createGardenButtonEventHandler(){
		return (e) -> {
	    	model = new Garden(null, 0, 0, 0, null, null, null, 0, model.getVolume(), model.getMuted(), model.getFullscreen(), model.getDarkMode());
	    	view.changePage(PageIdentifier.BUDGETANDSIZE);
		};
	}
	

	/**
	 * Utility function that removes a node from the grid at the specified x and y
	 * @param grid The gridpane
	 * @param x Column of this node
	 * @param y Row of this node
	 * @author Jaydon
	 */
	private void removeNodeFromGrid(GridPane grid, int x, int y) {
		int i = 0;
		boolean found = false;
		
		for(; i < grid.getChildren().size(); i++) {
			Node n = grid.getChildren().get(i);
			if (n instanceof ImageView) {
				if (GridPane.getColumnIndex(n) == x && GridPane.getRowIndex(n) == y) {
        			found = true;
					break;
        		}
			}
		}
		
		if (found) {
			grid.getChildren().remove(i);
		}
	}
	
	
}
