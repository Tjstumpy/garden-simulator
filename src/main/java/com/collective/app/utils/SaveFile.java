package com.collective.app.utils;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.collective.app.Garden;
/**
 * This is the utiltize function for saving and loading a garden inside of a ArrayList
 * @author Aaron
 *
 */
public class SaveFile {
	/**
	 * this is the constant Arraylist that is used to pass the arraylist from the loadGardenFile to the other functions
	 */
	private static ArrayList<Garden> Gardens =  new ArrayList<Garden>();
	
	/**
	 * This is the function that takes from a file and loads in the ArrayList of gardens that was saved
	 * @author Aaron
	 */
	public static void loadGardenFile() {
		try {
			FileInputStream fis = new FileInputStream("AllGardens.gardens");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Gardens = (ArrayList<Garden>) ois.readObject();
      
            ois.close();
            fis.close();
            
		}
		catch(Exception ex) {System.out.println("Loading file error with: " + ex.toString());}

	}
	
	/**
	 * This function takes the loaded ArrayList of gardens and finds a specific one identified by name
	 * @param name, this is the identification we will be using to find the garden
	 * @return Garden, this is the garden with the name given in the parameters
	 * @author Aaron
	 */
	public static Garden loadGarden(String name) {
		loadGardenFile();
		for(int index = 0; index < Gardens.size(); index++) {
			if(Gardens.get(index).getName().equals(name)) {
				return Gardens.get(index);
			}
		}
		return null;
	}
	
	/**
	 * This function determines if a name is in the list of saved gardens
	 * @param Boolean
	 * @author Jason
	 */
	
	public static Boolean checkForName(String name) {
		loadGardenFile();
		for(int index = 0; index < Gardens.size(); index++) {
			if(Gardens.get(index).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
//	public static void deleteSavedGarden(String name) {
//		loadGardenFile();
//		
//		for(int index = 0; index < Gardens.size(); index++) {
//			if(Gardens.get(index).getName().equals(name)) {
//				Gardens.remove(index);
//			}
//		}
//	}
//	
	/**
	 * This function determines if a Garden is in the list of saved gardens
	 * @param Boolean
	 * @author Jason
	 */
	public static Boolean checkForGarden(Garden garden) {
		loadGardenFile();
		for(int index = 0; index < Gardens.size(); index++) {
			if(Gardens.get(index).equals(garden)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This function saves the current garden inside of the arraylist of gardens
	 * @param garden, this is the current garden that the user is currently editing
	 * @author Aaron
	 */
	public static void saveGarden(Garden garden) {
		loadGardenFile();
		
		//deletes previous save if re-saving garden
		for(int index = 0; index < Gardens.size(); index++) {
			if(Gardens.get(index).getName().equals(garden.getName())) {
				Gardens.remove(index);
			}
		}
			
		Gardens.add(garden);
		try {
			FileOutputStream fos = new FileOutputStream("AllGardens.gardens");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Gardens);
			oos.close();
			fos.close();
			
		}
		catch(Exception ex) {System.out.println("Saving failed with: " + ex.toString());}
	}
	
	public static ArrayList<Garden> getGardens(){
		loadGardenFile();
		return Gardens;
	}
}

