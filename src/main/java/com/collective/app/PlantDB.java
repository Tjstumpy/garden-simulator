package com.collective.app;

import java.util.*;
import java.io.File;
import java.util.Scanner;

import com.collective.app.consts.PlantType;
/**
 * This is class where all of the plants are created and added a ArrayList
 * @author Jaydon
 *
 */
public class PlantDB {
	/**
	 * this is the arraylist that will contain all the plants for the garden
	 */
	private static ArrayList<Plant> db = new ArrayList<>();
	
	/**
	 * This is the contructor that calls the createALlPlants()
	 * @author Jaydon
	 */
	public PlantDB() {
		try {createAllPlants();}
		catch(Exception ex) {System.out.println("Loading file error with: " + ex.toString());}
	}
	/**
	 * This creates all of the plants and adds them to the ArrayList Database
	 * @throws Exception this throws the error message if there is one
	 * @author Aaron
	 */
	public void createAllPlants() throws Exception{
		File file = new File("Plants.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			String tempWholeString = scanner.nextLine();
			String[] elements = tempWholeString.split(",");
			PlantType type = PlantType.WOODY;
			int size = 5;
			if(elements[3].equals("h")) {
				type = PlantType.HERBACIOUS;
				size = 3;
			}
//			if(getClass().getResourceAsStream("/imgs/" + elements[0] + ".png") == null) {
//				System.out.println(elements[0]);
//			}
			db.add(new Plant(elements[0], elements[1], elements[2], Integer.parseInt(elements[4]), size, type, elements[5], elements[7], elements[6]));
		}
		scanner.close();
	}
	
	/**
	 * This is the function that will find the plant based on the their names
	 * @param name this is the name of the plant that want to be located
	 * @return this is the plant with the same common name
	 * @author Jaydon
	 */
	public Plant findPlantByName(String name) {
		for(Plant plant : db) {
			if (plant.getComName().equals(name)) {
				// Want to make sure we don't just have a single instance floating around, copy this!
				return new Plant(plant);
			}
		}
		
		return null;
	}
	
	/**
	 * This is the function that will find the plant based on their scientific names
	 * @param sciname, This string is the scientific name for the plants
	 * @return this is the plant with that scientific plant
	 * @author Jaydon
	 */
	public Plant findPlantByScentificName(String sciname) {
		for(Plant plant : db) {
			if (plant.getSciName().equals(sciname)) {
				// Want to make sure we don't just have a single instance floating around, copy this!
				return new Plant(plant);
			}
		}
		
		return null;
	}
	/**
	 * Filters plants in the DB based off the specified type
	 * @param type, this is the type that the user wants
	 * @return returns a array with all of the plants filtered by type
	 */	
	public ArrayList<Plant> getPlantsByType(PlantType type) {
		ArrayList<Plant> temp = new ArrayList<>();
		
		for(Plant plant : db) {
			if (plant.getPlantType().equals(type)) {
				temp.add(plant);
			}
		}
		return temp;
	}
	
	/**
	 * This function returns a new ArrayList of the PlantDB
	 * @return copy of the PlantDB types
	 */
	public ArrayList<Plant> getPlants() {
		ArrayList<Plant> temp = new ArrayList<>();
		for(Plant plant : db) {
				temp.add(plant);
		}
		return temp;
	}
}
