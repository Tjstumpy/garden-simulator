package com.collective.app;

import java.util.ArrayList;

import com.collective.app.utils.PositionUtils;
import com.collective.app.utils.SaveFile;
/**
 * Model for the current Garden
 *
 */
public class Garden implements java.io.Serializable{
	/**
	 *  this make it easier for the computer to know what it is serializing
	 */
	private static final long serialVersionUID = -4297119510973921265L;
	/**
	 * this is the name of the user want the garden to be saved as
	 */
	public String name;
	/**
	 *  this is the budget of the garden
	 */
	public int budget;
	/**
	 * this is the length of the garden
	 */
	public int length;
	/**
	 *  this is the width of the garden
	 */
	public int width;
	/**
	 *  this is the sunLevel of the garden
	 */
	public String sunLevel;
	/**
	 *  this is the soilTyle of the garden
	 */
	public String soilType;
	/**
	 * this is the moistureLevel of the garden
	 */
	public String moistureLevel;
	/**
	 * this is the total leps that all of the plants in the garden supports
	 */
	public int totalSupportedLeps;
	/**
	 * this is the volume of the music for the garden 
	 */
	public double volume;
	/**
	 * this is the boolean value of weather or not the music was muted
	 */
	public boolean isMuted;
	/**
	 * this is the boolean value of if the screen is fullscreen or not
	 */
	public boolean isFullscreen;
	public static final String debugging = "valgrind";
	public boolean isDarkMode;
	/**
	 * this is the arraylist that contains all of the plants for collision detection
	 */
	public ArrayList<Plant> plants = new ArrayList<>();
	
	/**
	 * This is the constructor for the new model class
	 * @param name, the name of the garden
	 * @param budget, the budget of the garden
	 * @param length, the length of the garden
	 * @param width, the width of the garden
	 * @param sunLevel, the sunlevel of the garden
	 * @param soilType, the soiltype of the garden
	 * @param moistureLevel, the moisturelevel of the garden
	 * @param totalSupportedLeps, the total leps that are supported in the garden
	 * @param volume, the is the volume of the music
	 * @param isMuted, this is whether or not the music is muted or not
	 * @param isFullscreen, this is whether or not it is fullscreen or nots
	 * @author The Team
	 */
	public Garden(String name, int budget, int length, int width, String sunLevel, String soilType, 
			String moistureLevel, int totalSupportedLeps, double volume, boolean isMuted, boolean isFullscreen,
			boolean isDarkMode) {
		this.name= name;
		this.budget = budget;
		this.length = length;
		this.width = width;
		this.sunLevel = sunLevel;
		this.soilType = soilType;
		this.moistureLevel = moistureLevel;
		this.totalSupportedLeps = totalSupportedLeps;
		this.volume = volume;
		this.isMuted = isMuted;
		this.isFullscreen = isFullscreen;
		this.isDarkMode = isDarkMode;
	}
	/**
	 * this is the getter for the totalSupportedLeps
	 * @return totalSpportedleps
	 * @author Aaron
	 */
	public int getTotalSupportedleps() {return totalSupportedLeps;}
	/**
	 * this is the setter for the totalSupportedLeps
	 * @param totalLeps this is the new totalSupportedLeps
	 * @author Aaron
	 */
	public void setTotalSupportedleps(int totalLeps) {this.totalSupportedLeps = totalLeps;}
	/**
	 * this is the combination of the getter and setter for the totalSupportedLeps
	 * @param addition this is the value that is added to the TotalSupportedLeps, could be negative or positive
	 * @author Aaron
	 */
	public void changeTotalSupportedLeps(int addition) {setTotalSupportedleps(getTotalSupportedleps() + addition);}
	/**
	 * this is the setter for the width
	 * @param width, this is the new width for the garden
	 * @author Aaron
	 */
	public void setWidth(int width) { this.width = width; }
	/**
	 * this is the getter for the width
	 * @return int, the width of the garden
	 * @author Aaron
	 */
	public int getWidth() { return this.width; }
	/**
	 * This is the setter for the length
	 * @param length, this is the current length of the garden
	 * @author Aaron
	 */
	public void setLength(int length) { this.length = length; }
	/**
	 * this is the getter for the length
	 * @return length, this is the current length of the garden
	 */
	public int getLength() { return this.length; }
	/**
	 * this sets the new soil type for the garden
	 * @param soil, this is the new soil type for graden
	 * @author Aaron
	 */
	public void setSoilType(String soil) {this.soilType= soil;}
	/**
	 * this is the getter for the soiltype
	 * @return soiltype, String value of the soil type of the garden
	 * @author Aaron
	 */
	public String getSoilType() {return this.soilType;}
	/**
	 * thi is the setter for the sunLevel
	 * @param sunlevel, this is the new sunLevel
	 * @author Aaron
	 */
	public void setSunLevel(String sunlevel) {this.sunLevel= sunlevel;}
	/**
	 * This is the getter for the sunLevel
	 * @return sunLevel, String value that of the sunLevel of the current garden
	 * @author Aaron
	 */
	public String getSunLevel() {return this.sunLevel;}
	/**
	 * this is hte setter for the moisture
	 * @param moisture, String value of the new moisture level of the garden
	 * @author Aaron
	 */
	public void setMoistureLevel(String moisture) {this.moistureLevel= moisture;}
	/**
	 * This is the getter for the moistureLevel
	 * @return mositureLevel, String value that shows the moistureLevel of the currentgarden
	 * @author Aaron
	 */
	public String getMoistureLevel() {return this.moistureLevel;}
	/**
	 * This is the setter for the Muted
	 * @param isMuted, this is the new value of Muted
	 * @author Aaron
	 */
	public void setMuted(boolean isMuted) {this.isMuted = isMuted;}
	/**
	 * this is the getter for the Muted
	 * @return Muted, boolean value to show if the music is muted or not
	 * @author AaronS
	 */
	public boolean getMuted() {return isMuted;}
	/**
	 * This is the setter for the fullscreen
	 * @param isFullscreen, boolean value to show if it is fullscreen or not
	 * @author Aaron
	 */
	public String getDebugging() {return debugging;}
	public void setFullscreen(boolean isFullscreen) {this.isFullscreen = isFullscreen;}
	/**
	 * this is the getter if it is fullScreen or not
	 * @return fullScreen, this is if it is fullScreen or not
	 * @author Aaron
	 */
	public boolean getFullscreen() {return isFullscreen;}
	/**
	 * this is the getter for the budget
	 * @return Budget
	 * @author Aaron
	 */
	
	public void setDarkMode(boolean isDarkMode) {this.isDarkMode = isDarkMode;}
	
	public boolean getDarkMode() {
		return isDarkMode;
	}
	
	public int getBudget() {return this.budget;}
	/**
	 * this is the setter for the budget
	 * @param budget this is the new budget
	 * @author Aaron
	 */
	public void setBudget(int budget) {this.budget = budget;}
	/**
	 * this is the combination of the getter and setter for the budget
	 * @param Addition this is the value that will be addded to the budget, could be both negative or positive
	 * @author Aaron
	 */
	public void changeBudget(int Addition){setBudget(getBudget() + Addition);}
	
	/**
	 * this is the getter for the volume
	 * @return volume
	 * @author Aaron
	 */
	public double getVolume() {return volume;}
	/**
	 * this is the setter for the volume
	 * @param volume this is the new volume
	 * @author Aaron
	 */
	public void setVolume(int volume) {this.volume = volume;}
	/**
	 * This is the setter for the name
	 * @param name, this is new name for the garden
	 * @author Aaron
	 */
	public void setName(String name) {this.name = name;}
	/**
	 * This is the getter for the name of the garden
	 * @return name, String value of the name for the garden
	 * @author Aaron
	 */
	public String getName() {return this.name;}
	/**
	 * Adds the specified plant to the garden
	 * @param plant the plant that will be added to the garden
	 * @author Jaydon
	 */
	
	public ArrayList<Plant> getPlants() {return this.plants;}
	
	public void addPlant (Plant plant) {
		plants.add(plant);
		for(Plant p : plants) {
			System.out.println(p.getSciName());
		}
	}
	
	public void removePlant(int x, int y) {
		int i = 0;
		boolean found = false;
		
		for(; i < plants.size(); i++) {
			Plant p = plants.get(i);
			if (p.getOffsetXPos() == x && p.getOffsetYPos() == y) {
				found = true;
				break;
			}	
		}
		
		if (found) {
			plants.remove(i);
		}
	}
	
	/**
	 * Checks to see if a plant collides with a proposed Plant placed at position (x, y) and size
	 * @param x proposed x value
	 * @param y proposed y value
	 * @param size proposed plant's size
	 * @return true if there is a plant, false if no collision
	 * @author Jaydon
	 */
	public boolean checkForPlant(int x, int y, int size) {
		
		int[][] targetCorners = PositionUtils.calculateBoundaries(x, y, size, length * 3, width * 3);
		
		if (targetCorners == null) {
			// Out of bounds
			return true;
		}
		
		int i = 0;
		for (Plant p : plants) {
			int[][] pCorners = PositionUtils.calculateCorners(p.getX(), p.getY(), p.getSize());
			i++;
			if (PositionUtils.checkCollisions(pCorners, targetCorners)) {
				if(getName().toLowerCase().equals(debugging)) {System.out.println("Collision Status: true");}
				return true;
			}
		}
		
		if(getName().toLowerCase().equals(debugging)) {System.out.println("Collision Status: false");}
		return false;
	}
	/**
	 * This returns the plant at a specified position
	 * @param x the x to search at
	 * @param y the y to search at
	 * @return Plant the plant at those coordinates, null if not found
	 * @author Jaydon
	 */
	public Plant getPlantByPos(int x, int y) {
		for(Plant p : plants) {
			if (p.getX() == x && p.getY() == y) {
				return p;
			}
		}
		
		return null;
	}
	/**
	 * This function saves the garden into a arraylist that is then written into a file
	 * @author Aaron
	 */
	public void save() {
		SaveFile.saveGarden(this);	
		if(getName().toLowerCase().equals(debugging)) {System.out.println("Reached save handler");}
	}
	
	/**
	 * This loads a garden from the save file and changes the values of the current garden to the one in the memory
	 * @param name, This is the name of the garden that is form memory
	 * @author Aaron
	 */
	public void load(String name) {
		Garden temp = SaveFile.loadGarden(name);
		this.name= name;
		this.budget = temp.budget;
		this.length = temp.length;
		this.width = temp.width;
		this.sunLevel = temp.sunLevel;
		this.soilType = temp.soilType;
		this.moistureLevel = temp.moistureLevel;
		this.totalSupportedLeps = temp.totalSupportedLeps;
		this.plants = temp.plants;
	}
	
}

