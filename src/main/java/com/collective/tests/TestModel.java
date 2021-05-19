package com.collective.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.collective.app.*;
import com.collective.app.consts.*;
import com.collective.app.utils.*;
/**
 * this is the test class for all of the seperate functions within the model
 * @author Aaron
 *
 */
public class TestModel {
	
	// Add all the tests here, and then right click on the project and select "Run As" and then "Junit Test" to run them
	/**
	 * this is a plant for testing
	 */
	Plant junit2 = new Plant("GenusSpecies", "Tulip", "family", 5, 2, PlantType.WOODY, "Full Sun", "Average", "Soil", 5, 5, 3);
	
	//second plant for testing getCost()
	Plant junit3 = new Plant("GenusSpecies", "Strawberry","family",5, 2, PlantType.HERBACIOUS, "Full Sun", "Average", "Soil", 5, 5, 3);

	/**
	 * this is a garden for testing
	 */
	Garden junit = new Garden("FirstGarden", 1000, 10, 20, "Shade", "Good", "Average", 30, 50, false, false, false);
	

	
	/**
	 * this tests the getter for the total supported leps
	 * @author Aaron
	 */
	@Test
	public void testGetTotalSupportedLeps() {
		int result = junit.getTotalSupportedleps();
		assertEquals(30, result);
	}
	
	/**
	 * this tests the set total leps
	 * @author Aaron
	 */
	@Test
	public void testSetTotalSupportedLeps() {
		junit.setTotalSupportedleps(100);
		int result = junit.getTotalSupportedleps();
		assertEquals(100, result);
	}

	/**
	 * this tests changing the total supported leps
	 * @author Aaron
	 */
	@Test
	public void testChangeTotalSupportedLeps() {
		junit.changeTotalSupportedLeps(20);
		int result = junit.getTotalSupportedleps();
		assertEquals(50, result);
	}
	
	/**
	 * this tests getter for the budget
	 * @author Aaron
	 */
	@Test
	public void testGetBudget() {
		int result = junit.getBudget();
		assertEquals(1000, result);
	}
	

	/**
	 * this tests setting the budget
	 * @author Aaron
	 */
	@Test
	public void testSetBudget() {
		junit.setBudget(2000);
		int result = junit.getBudget();
		assertEquals(2000, result);
	}

	/**
	 * this test changing the budget
	 * @author Aaron
	 */
	@Test
	public void testChangeBudget(){
		junit.changeBudget(500);
		int result = junit.getBudget();
		assertEquals(1500, result);
	}
	
	/**
	 * this tests setting the width of the garden
	 * @author Jason
	 */	
	@Test 
	public void testSetWidth(){
		junit.setWidth(10);
		int result = junit.getWidth();
		assertEquals(10, result);
	}
	
	/**
	 * this tests setting the length of the garden
	 * @author Jason
	 */	
	@Test 
	public void testSetLength(){
		junit.setLength(15);
		int result = junit.getLength();
		assertEquals(15, result);
	}
	
	/**
	 * this tests gets the getter for the volume
	 * @author Aaron
	 */
	@Test
	public void testGetVolume() {
		double result = junit.getVolume();
		assertEquals((int)50, (int)result);
	}
	
	/**
	 * Tests the getting for the debug garden name
	 * @author Jaydon
	 */
	@Test
	public void testGetDebugging() {
		String result = junit.getDebugging();
		assertEquals("valgrind", result);
	}
	
	@Test
	public void testGetDarkMode() {
		assertEquals(false, junit.getDarkMode());
	}
	
	@Test
	public void testSetDarkMode() {
		junit.setDarkMode(true);
		assertEquals(true, junit.getDarkMode());
	}
	
	/**
	 * this tests the setter for the volume
	 * @author Aaron
	 */
	@Test
	public void testSetVolume() {
		junit.setVolume(100);
		double result = junit.getVolume();
		assertEquals((int)100, (int)result);
	}
	
	/**
	 * this tests the setter for the common name
	 * @author Aaron
	 */
	@Test
	public void testSetName() {
		junit.setName("SecondGarden");
		String result = junit.getName();
		assertEquals("SecondGarden", result);
	}
	
	/**
	 * this tests the getter for the common name
	 * @author Aaron
	 */
	@Test
	public void testGetName() {
		String result = junit.getName();
		assertEquals("FirstGarden", result);
	}

	/**
	 * this tests setting and getting the soiltype of the garden
	 * @author Jason
	 */	
	@Test
	public void testSetAndGetGardenSoilType() {
		junit.setSoilType("Sand");
		String result = junit.getSoilType();
		assertEquals("Sand", result);
	}
	
	/**
	 * this tests setting and getting the moisture level of the garden
	 * @author Jason
	 */	
	@Test
	public void testSetAndGetGardenMoistureLevel() {
		junit.setMoistureLevel("Average");
		String result = junit.getMoistureLevel();
		assertEquals("Average", result);
	}
	
	/**
	 * this tests setting and getting the sun level  of the garden
	 * @author Jason
	 */	
	@Test
	public void testSetAndGetGardenSunLevel() {
		junit.setSunLevel("Half Sun");
		String result = junit.getSunLevel();
		assertEquals("Half Sun", result);
	}
	/**
	 * this tests the getter for the Scientific name
	 * @author Aaron
	 */
	@Test
	public void testGetSciName() { 
		String result = junit2.getSciName();
		assertEquals("GenusSpecies", result);
	}
	
	/**
	 * this tests the setter for the Scientific name
	 * @author Aaron
	 */
	@Test
	public void testGetComName() {
		String result = junit2.getComName();
		assertEquals("Tulip", result);
	}
	
	/**
	 * this test the getter for the total leps
	 * @author Aaron
	 */
	@Test
	public void testGetLepSupported() {
		Plant test = new Plant("GenusSpecies", "Tulip","family", 5, 2, PlantType.WOODY, "Full Sun", "Average", "Soil");
		int result = test.getLepSupported();
		assertEquals(5, result);
	}
	
	/**
	 * this tests the setter for the scientific name
	 * @author Aaron
	 */
	@Test
	public void testSetSciName() {
		junit2.setSciName("GenusSpeciesTwo");
		String result = junit2.getSciName();
		assertEquals("GenusSpeciesTwo", result);	
	}
	
	/**
	 * this tests the setter for the common name
	 * @author Aaron
	 */
	@Test
	public void testSetComName() {
		junit2.setComName("Rose");
		String result = junit2.getComName();
		assertEquals("Rose", result);	
	}
	
	/**
	 * this tests the setter for the leps supported
	 * @author Aaron
	 */
	@Test
	public void testSetLepSupported () {
		junit2.setLepSupported(10);
		int result = junit2.getLepSupported();
		assertEquals(10, result);		
	}
	
	/**
	 * this tests getting the sunlevel
	 * @author Daniel
	 */
	@Test
	public void testGetSunLevel() {
		Plant test = new Plant(junit2);
		String result = test.getSunLevel();
		assertEquals("Full Sun", result);
	}
	
	/**
	 * this tests getting the moisture level
	 * @author Daniel
	 */
	@Test
	public void testGetMoistureLevel() {
		String result = junit2.getMoistureLevel();
		assertEquals("Average", result);
	}
	
	/**
	 * this tests getting the soil type
	 * @author Daniel
	 */
	@Test
	public void testGetSoilType() {
		String result = junit2.getSoilType();
		assertEquals("Soil", result);
	}
	
	/**
	 * this tests the setter for the sun level
	 * @author Daniel
	 */
	@Test
	public void testSetSunLevel() {
		junit2.setSunLevel("Half Sun");
		String result = junit2.getSunLevel();
		assertEquals("Half Sun", result);
	}
	
	/**
	 * this tests the setter for the moisture level
	 * @author Daniel
	 */
	@Test
	public void testSetMoistureLevel() {
		junit2.setMoistureLevel("Low");
		String result = junit2.getMoistureLevel();
		assertEquals("Low", result);
	}
	
	/**
	 * this tests the setter for the soil type
	 * @author Daniel
	 */
	@Test
	public void testSetSoilType() {
		junit2.setSoilType("Sand");
		String result = junit2.getSoilType();
		assertEquals("Sand", result);
	}
	
	/**
	 * this tests getting the coordinate x of the plant
	 * @author Daniel
	 */
	@Test
	public void testGetX() {
		int result = junit2.getX();
		assertEquals(5, result);
	}
	
	/**
	 * this tests getting the coordinate y of the plant
	 * @author Daniel
	 */
	@Test
	public void testGetY() {
		int result = junit2.getY();
		assertEquals(5, result);
	}
	
	/**
	 * this tests getting the size of the plant
	 * @author Daniel
	 */
	@Test
	public void testGetSize() {
		int result = junit2.getSize();
		assertEquals(3, result);
	}
	
	/**
	 * this tests setting the coordinate x of the plant
	 * @author Daniel
	 */
	@Test
	public void testSetX() {
		junit2.setX(4);
		int result = junit2.getX();
		assertEquals(4, result);
	}
	
	/**
	 * this tests setting the coordinate y of the plant
	 * @author Daniel
	 */
	@Test
	public void testSetY() {
		junit2.setY(4);
		int result = junit2.getY();
		assertEquals(4, result);
	}
	
	/**
	 * this tests setting the size of the plant
	 * @author Daniel
	 */
	@Test
	public void testSetSize() {
		junit2.setSize(6);
		int result = junit2.getSize();
		assertEquals(6, result);
	}
	
	/**
	 * this tests getting the plant type
	 * @author Daniel
	 */
	@Test
	public void testGetPlantType() {
		PlantType result = junit2.getPlantType();
		assertEquals(PlantType.WOODY, result);
	}
	
	/**
	 * this tests setting the plant type
	 * @author Daniel
	 */
	@Test
	public void testSetPlantType() {
		junit2.setPlantType(PlantType.HERBACIOUS);
		PlantType result = junit2.getPlantType();
		assertEquals(PlantType.HERBACIOUS, result);
	}
	
	/**
	 * this tests getting the cost of the plant depending on its type
	 * @author Daniel
	 */
	@Test
	public void testGetCost() {
		int resultWood = junit2.getCost();
		int resultHerb = junit3.getCost();
		assertEquals(20, resultWood);
		assertEquals(6, resultHerb);
	}
	
	/**
	 * this tests the offset x position of the plant
	 * @author Daniel
	 */
	
	@Test
	public void testGetOffsetXPos() {
		int result = junit2.getOffsetXPos();
		assertEquals(4, result);
	}
	
	/**
	 * this tests the offset y position of the plant
	 * @author Daniel
	 */
	@Test
	public void testGetOffsetYPos() {
		int result = junit2.getOffsetYPos();
		assertEquals(4, result);
	}
	
	/**
	 *  this tests if all of the plants are created
	 *  @author Aaron
	 */
//	@Test
//	public void testDataBaseCreation() {
//		
//	}
//	
	/**
	 * this tests getting removing a plant from the arraylist
	 * @author Jason
	 */	
	@Test
	public void testRemovePlant() {
		junit.addPlant(junit2);
		junit.removePlant(4, 4);
		ArrayList<Plant> result = junit.getPlants();
		ArrayList<Plant> test = new ArrayList<Plant>();
		assertEquals(test, result);
	}
	
	/**
	 * this tests checking for a plant in the arraylist
	 * @author Jason
	 */	
	@Test
	public void testCheckForPlantSuccess1() {
		junit.addPlant(junit2);
		Boolean result = junit.checkForPlant(5, 5, 3);
		assertEquals(result, true);
	}
	
	/**
	 * Test for checkForPlant success case 2
	 * @author Jaydon
	 */
	@Test
	public void testCheckForPlantSuccess2() {
		junit.addPlant(junit2);
		Boolean result = junit.checkForPlant(-1, -1, 3);
		assertEquals(result, true);
	}
	
	/**
	 * Test for checkForPlant failure
	 * @author Jaydon
	 */
	@Test
	public void testCheckForPlantFail() {
		junit.addPlant(junit2);
		Boolean result = junit.checkForPlant(8, 8, 3);
		assertEquals(result, false);
	}
	
	/**
	 * this tests getting a plant by its x and y position
	 * @author Jason
	 */	
	
	@Test
	public void testGetPlantByPosSuccess() {
		junit.addPlant(junit2);
		Plant result = junit.getPlantByPos(5, 5);
		assertEquals(result, junit2);
	}
	
	@Test
	public void testGetPlantByPosFail() {
		junit.addPlant(junit2);
		Plant result = junit.getPlantByPos(4, 4);
		assertEquals(result, null);
	}
	
	/**
	 * this tests setting and getting the mute status of the garden
	 * @author Jason
	 */	
	@Test
	public void testSetAndGetMuted() {
		junit.setMuted(true);
		Boolean result = junit.getMuted();
		assertEquals(true, result);		
	}
	
	/**
	 * this tests setting and getting the fullscreen status of the garden
	 * @author Jason
	 */	
	@Test
	public void testSetandGetFullscreen() {
		junit.setFullscreen(true);
		Boolean result = junit.getFullscreen();
		assertEquals(true, result);	
	}
	
	/**
	 * this tests adding and getting plants from the arraylist
	 * @author Jason
	 */	
	@Test
	public void testAddAndGetPlants() {
		junit.addPlant(junit2);
		ArrayList<Plant> result = junit.getPlants();
		ArrayList<Plant> test = new ArrayList<Plant>();
		test.add(junit2);
		assertEquals(result, test);
	}
	
	
	/**
	 * this tests the save function
	 * @author Aaron
	 */
	@Test
	public void testSave() {
		Garden temp = new Garden("SecondGarden", 1000, 10, 20, "Shade", "Good", "Average", 30, 50, false, false, false);
		Garden overRidden = new Garden("TrashGarden", 100, 10, 20, "Shade", "Good", "Average", 30, 50, false, false, false);
		temp.save();
		overRidden.load(temp.getName());
		assertEquals(temp.getBudget(),overRidden.getBudget());
		assertEquals(temp.getLength(),overRidden.getLength());
		assertEquals(temp.getWidth(),overRidden.getWidth());
		assertEquals(temp.getSunLevel(),overRidden.getSunLevel());
		assertEquals(temp.getMoistureLevel(),overRidden.getMoistureLevel());
		assertEquals(temp.getTotalSupportedleps(),overRidden.getTotalSupportedleps());
		assertEquals(temp.getPlants(),overRidden.getPlants());
	}
}
