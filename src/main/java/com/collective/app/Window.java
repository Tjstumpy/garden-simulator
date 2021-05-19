package com.collective.app;

import java.awt.Toolkit;
import java.awt.Dimension;

import com.collective.app.consts.PageIdentifier;
import com.collective.app.dtos.UpdateObject;
import com.collective.app.utils.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This is the superclass for the all of the other views for each page
 * @author Aaron
 *
 */
public class Window {
	/**
	 * This is the height of the application window
	 */
	protected static int height = Toolkit.getDefaultToolkit().getScreenSize().height - 200;
	/**
	 * This is the width of the application window
	 */
	protected static int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
	/**
	 * this is the size of all of the buttons and many other things
	 */
	protected static int size = width/4;
	/**
	 * this is a static instance of the controller
	 */
	protected static Controller controller;
	/**
	 * this is the a static reference to the utilize function UIss
	 */
	protected static UI ui;
	
	
	/**
	 * This is the constructor for the window
	 * @param controller1, this is the controller
	 * @author Aaron
	 */
	public Window(Controller controller1) {
		controller = controller1;
		ui = new UI(width, height, size);
	}
	/**
	 * This is the general delcaration for the bulidpage for all of the pages
	 * @return the scene that will be shown on the stage
	 * @author Aaron
	 */
	public BorderPane buildPage() {
		return null;
	}
	/**
	 * This is the general declaration for the bulidpage for the options page
	 * @param next, this is the page that the back button will be going to
	 * @return the Scene that will be shown on the stage
	 * @author Aaron
	 */
	public BorderPane buildPage(PageIdentifier next) {
		return null;
	}
	
	/**
	 * Can be triggered and passed a update object based on the currently loaded page
	 * @param obj The DTO for this particular page
	 */
	public void updatePage(UpdateObject obj) {
		// By default pages do not have a update function
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
