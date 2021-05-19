package com.collective.app;


import java.net.URISyntaxException;
import java.util.ArrayList;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.UpdateObject;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 *  this is the main view that calls all of the other helper classes
 * @author Aaron
 *
 */
public class View {
	/**
	 * this is the stage which all of the bulid page's scenes will be shown
	 */
	public Stage stage;
	/**
	 * this is a instance of controller
	 */
	private static Controller controller;
	/**
	 *  this is the arraylist that contains all o the pages within the application
	 */
	private static ArrayList<Window> pages = new ArrayList<>();
	/**
	 * this is the music file that is taken from memory
	 */
	Media media;
	/**
	 * this is what is plays the music from the file taken from memory
	 */
	MediaPlayer player;
	/**
	 * this is the PrevPage of the application
	 */
	private PageIdentifier PrevPage;
	/**
	 * this is the current page of the application
	 */
	private PageIdentifier currentPageType;
	private Window currentPage;
	private static Scene scene;
	/**
	 * this is the view constuctor that also generates all of the other pages
	 * @param controller1, instance of controller
	 * @param stage, a new Stage
	 * @author Aaronss
	 */
	public View(Controller controller1, Stage stage) {
		this.stage = stage;
		controller = controller1;
		stage.setTitle("Legit Amazing Garden Program");
		
		
		// Generate pages
		pages.add(new WelcomePage(controller));
		pages.add(new GardenPage(controller));
		pages.add(new OptionsPage(controller));
		pages.add(new LoadPage(controller));
		pages.add(new BudgetAndSize(controller));
		pages.add(new Conditions(controller));
		pages.add(new GardenComplete(controller));
		
		//Load background music
		try{
			media = new Media(getClass().getResource("/media/music.mp3").toURI().toString());
			player = new MediaPlayer(media);
			player.setCycleCount(MediaPlayer.INDEFINITE);
			player.setVolume(0.1);
			player.play();
		}catch (URISyntaxException e) {
		  e.printStackTrace();
		} 
		
		Window startPage = pages.get(0);
		scene = new Scene(startPage.buildPage(), startPage.getWidth(), startPage.getHeight());
		scene.getStylesheets().add(getClass().getResource("/styling/application.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/styling/light.css").toExternalForm());

		PrevPage = PageIdentifier.WELCOME;
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * this closes the stage
	 * @author Aaron
	 */
	public void closeView() {
		stage.close();
	}
	/**
	 * with all of the other pages already generated and stored, this changes the view to the one desired
	 * @param page, this is the page that the uses wants
	 * @author Aaron
	 */
	public void changePage(PageIdentifier page) {
		BorderPane generatedPage;
		currentPageType = page;
		//hashmap not arraylist
		switch(page) {
		case WELCOME:
			currentPage = pages.get(0);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		case GARDEN:
			currentPage = pages.get(1);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		case OPTIONS:
			currentPage = pages.get(2);
			generatedPage = currentPage.buildPage(PrevPage);
			PrevPage = page;
			break;
		case LOADMENU:
			currentPage = pages.get(3);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		case BUDGETANDSIZE:
			currentPage = pages.get(4);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		case CONDITIONS:
			currentPage = pages.get(5);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		case COMPLETE:
			currentPage = pages.get(6);
			generatedPage = currentPage.buildPage();
			PrevPage = page;
			break;
		default:
			generatedPage = null;
			break;
		}
		
		scene.setRoot(generatedPage);		
		stage.show();
	}
	
	public void triggerPageUpdate() {
		UpdateObject obj;
		
		switch(currentPageType) {
		case GARDEN:
			obj = controller.getGardenPageModel();
			break;
		default:
			// No other page supports DTOs
			obj = null;
		}
		
		currentPage.updatePage(obj);
	}
	
}
