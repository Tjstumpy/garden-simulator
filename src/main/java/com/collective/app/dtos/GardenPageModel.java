package com.collective.app.dtos;

import java.util.ArrayList;

import com.collective.app.Plant;
import com.collective.app.consts.PageIdentifier;
/**
 * This is a data transfering object that allows for multiply bits of information to be transfered at once
 * @author Aaron
 *
 */
public class GardenPageModel implements UpdateObject {
	/**
	 * this is identifies that the page is the garden page
	 */
	public PageIdentifier pageIdentifier = PageIdentifier.GARDEN;
	/**
	 * This is the total leps supported by the current garden
	 */
	private int totalSupportedLeps;
	/**
	 * this is the remaining budget of the current garden
	 */
	private int budget;
	/**
	 * this is the length of the current garden
	 */
	private int length;
	/**
	 * this is the width of the current garden
	 */
	private int width;
	/**
	 * this is the name of the current garden
	 */
	private String name;
	/**
	 * this is the sunlevel of the current garden
	 */
	public String sunLevel;
	/**
	 * this is the SoilType of the current garden
	 */
	public String soilType;
	/**
	 * this is the moistureLevel of the current garden
	 */
	public String moistureLevel;
	
	public ArrayList<Plant> plants;
	
	/**
	 * This is the constuctor for the data transfering object
	 * @param totalSupportedLeps, this is the total about of leps that are supported on the garden
	 * @param budget, this is the total amount of money that the use has
	 * @param length, this is the length of the garden
	 * @param width, this is the width of the garden
	 * @param name, this is the name of the user wants to use to save the garden
	 * @param sunLevel, this is the sulevel of the garden
	 * @param soilType, this is the soiltpe of the garden
	 * @param moistureLevel, this is the moisture level of the garden
	 * @author Aaron
	 */
	public GardenPageModel(int totalSupportedLeps, int budget, int length, int width, String name, 
			String sunLevel, String soilType, String moistureLevel, ArrayList<Plant> plants) {
		this.totalSupportedLeps = totalSupportedLeps;
		this.budget = budget;
		this.length = length;
		this.width = width;
		this.name = name;
		this.sunLevel = sunLevel;
		this.moistureLevel = moistureLevel;
		this.soilType = soilType;
		this.plants = plants;
	}
	/**
	 * This is the getter for the total supported leps 
	 * @return int, total supported leps
	 */
	public int getTotalSupportedLeps() {return totalSupportedLeps;}
	/**
	 * this is the getter for the total budget left in the model
	 * @return int, the budget
	 */
	public int getBudget() {return budget;}
	/**
	 * this is the getter for the length of the garden
	 * @return int, the length of the garden
	 */
	public int getLength() {return length;}
	/**
	 * this is the getter for the width of the garden
	 * @return int, this is the width of the garden
	 */
	public int getWidth() {return width;}
	/**
	 * this is the getter for the name of the garden 
	 * @return String, the name of the garden
	 */
	public String getName() {return name;}
	/**
	 * this is the getter for the sunlevel
	 * @return String, the SunLevel
	 */
	public String getSunLevel() {return sunLevel;}
	/**
	 * this is the getter for the moisture level
	 * @return String, the moisture
	 */
	public String getMoistureLevel() {return moistureLevel;}
	/**
	 *  this is the getter for the soil type
	 * @return String, the soil type
	 */
	public String getSoilType() {return soilType;}
	
	public ArrayList<Plant> getGardenPlants(){ return plants;}

}
