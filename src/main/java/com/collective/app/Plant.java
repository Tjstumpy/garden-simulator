package com.collective.app;

import javafx.scene.image.Image;
import com.collective.app.consts.PlantType;
/**
 * This is the plant object that will be placed on to the garden
 * @author Aaron
 *
 */
public class Plant implements java.io.Serializable {
	/**
	 * this helps the compiler know what class is being saved
	 */
	private static final long serialVersionUID = -2047754722165797222L;
	/**
	 * this is the Scientific name of the plant
	 */
	public String SciName;
	/**
	 * this is the common name of the of the plant
	 */
	public String ComName;
	/**
	 * this is the leps that the plant supports
	 */
	public String Family;
	public int LepSupported;
	/**
	 *  this is the plants type either being woody or herbavious
	 */
	public PlantType type;
	/** 
	 * this is the sunLevel the plant needs to grow
	 */
	public String sunLevel;
	/**
	 *  this is the moisture level that the plant needs to grow
	 */
	public String moistureLevel;
	/**
	 *  this is the soiltype that the plant needs to grow
	 */
	public String soilType;
	
	// placement info
	/**
	 *  this is the x location that the plant is placed at
	 */
	int x = 0;
	/** 
	 *  this is the y location that the plant is placed at
	 */
	int y = 0;
	/**
	 *  this is the size of the plant needs to grow
	 */
	int size = 3;
	
	/**
	 * this is the constructor for the plant
	 * @param SciName, this is the String for the scientific Name
	 * @param ComName, this is the String for the common name
	 * @param LepSupported, this is the int for all of the leps supported
	 * @param ReqSpace, this is the int for all of space required for the plant to grow
	 * @param type, this is PlantType that identifies if the plant is WODDY or HERBACIOUS
	 * @param sunLevel, this is the required sunLevel for the plant
	 * @param moistureLevel, this is the required moistureLevel for the plant
	 * @param soilType, this is the required Soiltype for the plant
	 * @author Aaron
	 */
	public Plant(String SciName, String ComName, String Family, int LepSupported, int size, PlantType type
			,String sunLevel, String moistureLevel, String soilType) {
		this.SciName = SciName;
		this.ComName = ComName;
		this.Family = Family;
		this.LepSupported = LepSupported;
		this.size = size;
		this.type = type;
		this.sunLevel = sunLevel;
		this.moistureLevel = moistureLevel;
		this.soilType = soilType;
	}
	
	public Plant(String SciName, String ComName, String Family, int LepSupported, int size, PlantType type,
			String sunLevel, String moistureLevel, String soilType, int x, int y, int inputtedSize) {
		this.SciName = SciName;
		this.ComName = ComName;
//		this.Family = Family;
		this.LepSupported = LepSupported;
		this.size = size;
		this.type = type;
		this.sunLevel = sunLevel;
		this.moistureLevel = moistureLevel;
		this.soilType = soilType;
		this.x = x;
		this.y = y;
		this.size = inputtedSize;
	}
	
	/**
	 * this clones the current plant
	 * @param p, this is the new plant object who's values will be reassigned
	 * @author Jaydon
	 */
	public Plant(Plant p) {
		this.SciName = p.getSciName();
		this.ComName = p.getComName();
//		this.Family = p.getFamily();
		this.LepSupported = p.getLepSupported();
		this.size = p.getSize();
		this.type = p.getPlantType();
		this.sunLevel = p.getSunLevel();
		this.moistureLevel = p.getMoistureLevel();
		this.soilType = p.getSoilType();
	}
	/**
	 * this is the getter for the Scientific Name
	 * @return Scientific Name
	 * @author Aaron
	 */
	public String getSciName() {
		return SciName;
	}
	/**
	 * this is the getter for the plantType
	 * @return Type
	 * @author Aaron
	 */
	public PlantType getPlantType() {
		return type;
	}
	/**
	 * This is the getter for the common name
	 * @return CommonName, String
	 */
	public String getComName() {
		return ComName;
	}
	/**
	 * this is the getter for the leps that the plants supports
	 * @return int, total leps that the plants supports
	 */
	public int getLepSupported() {
		return  LepSupported;
	}
	/**
	 * this is the getter for the sunLevel
	 * @return sunLevel
	 * @author Aaron
	 */
	public String getSunLevel() {return this.sunLevel;}
	/**
	 * This is the getter moistureLevel
	 * @return mositerlevel
	 * @author Aaron
	 */
	public String getMoistureLevel() {return this.moistureLevel;}
	public String getFamily() {return this.Family;}
	/**
	 * This is the getter for the soilType
	 * @return soilType
	 * @author Aaron
	 */
	public String getSoilType() {return this.soilType;}
	/**
	 * This is the setter for the sunLevel
	 * @param sunLevel, this is the new sunLevel
	 * @author Aaron
	 */
	public void setSunLevel(String sunLevel) {this.sunLevel = sunLevel;}
	/**
	 * this is the setter for the moistureLevel
	 * @param moistureLevel, this is the new moisture level
	 * @author Aaron
	 */
	public void setMoistureLevel(String moistureLevel) {this.moistureLevel = moistureLevel;}
	/**
	 * This is the setter for the soil type
	 * @param soilType, this is the new soil type
	 * @author Aaron
	 */
	public void setSoilType(String soilType) {this.soilType = soilType;}
	/**
	 * This is the getter for the x value
	 * @return x value
	 * @author Aaron
	 */
	public int getX() {
		return x;
	}
	/**
	 * this is the getter for the y value
	 * @return y value
	 * @author Aaron
	 */
	public int getY() {
		return y;
	}
	/**
	 * this is the getter for the size value
	 * @return size
	 * @author Aaron
	 */
	public int getSize() {
		return size;
	}
	/**
	 * this is the setter for the X value
	 * @param x, the new X value
	 * @author Aaron
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * this is the setter for the y value
	 * @param y, the new y value
	 * @author Aaron
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * this is the setter for the size value
	 * @param size, the new size value
	 * @author Aaron
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * This is the setter for the SciName
	 * @param SciName, this is the new Scientific name
	 * @author Aaron
	 */
	public void setSciName(String SciName) {
		this.SciName = SciName;
	}
	/**
	 * this is the setter for the common name
	 * @param ComName, this is the new common name
	 * @author Aaron
	 */
	public void setComName(String ComName) {
		this.ComName = ComName;
	}
	/**
	 * this is the setter for the leps supported
	 * @param LepSupported, this is the new value of leps Supported
	 * @author Aaron
	 */
	public void setLepSupported (int LepSupported) {
		this.LepSupported = LepSupported;
	}
	/**
	 * this is the setter for the plant type
	 * @param type, this is the new plant type
	 * @author Aaron
	 */
	public void setPlantType(PlantType type) {
		this.type = type;
	}

	/**
	 * Returns the cost associated with this plant based off it's type
	 * @return plant cost
	 */
	public int getCost() {
		if (type.equals(PlantType.WOODY)) {
			return 20;
		} else {
			return 6;
		}
	}
	
	public int getOffsetXPos() {
		return x - (size / 2);
	}
	
	public int getOffsetYPos() {
		return y - (size / 2);
	}
}
