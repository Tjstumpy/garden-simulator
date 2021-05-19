package com.collective.app.utils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * A collection of utilities to help with positioning and collisions between Plants
 * @author Jaydon
 *
 */
public class PositionUtils {
	
	/**
	 * Calculates the topLeft and bottomRight corners for range detection
	 * @param centerx the center x value of the Plant
	 * @param centery the center y value of the Plant
	 * @param size the radius of the Plant
	 * @return the array of all the points (an array of size 2 arrays)
	 * @author Jaydon
	 */
	public static int[][] calculateCorners(int centerx, int centery, int size) {
		int[][] corners = new int[2][2];
		
		int offset = Math.floorDiv(size, 2);
		
		int topLeftX = centerx - offset;
		int topLeftY = centery - offset;

		int bottomRightX = centerx + offset;
		int bottomRightY = centery + offset;
		
		if (topLeftX < 0  || topLeftY < 0) {
			return null;
		}
		
		corners[0][0] = topLeftX;
		corners[0][1] = topLeftY;
		
		corners[1][0] = bottomRightX;
		corners[1][1] = bottomRightY;
		
		return corners;
	}
	
	/**
	 * Calculates the corners and midpoints to found a boundary of points around the specified center
	 * @param centerx the center x value of the Plant
	 * @param centery the center y value of the Plant
	 * @param size the radius of the Plant
	 * @return the array of all the points (an array of size 2 arrays)
	 * @author Jaydon
	 */
	public static int[][] calculateBoundaries(int centerx, int centery, int size, int maxX, int maxY) {
		int[][] bounds = new int[8][2];
		
		int offset = Math.floorDiv(size, 2);
		
		System.out.println("SIZE: " + size);
		System.out.println("OFFSET: " + offset);
		
		// Corners
		
		int topLeftX = centerx - offset;
		int topLeftY = centery - offset;
		
		int topRightX = centerx + offset;
		int topRightY = centery - offset;
		
		int bottomLeftX = centerx - offset;
		int bottomLeftY = centery + offset;
		
		int bottomRightX = centerx + offset;
		int bottomRightY = centery + offset;
		
		// Midpoints
		
		int leftX = centerx - offset;
		int leftY = centery;
		
		int rightX = centerx + offset;
		int rightY = centery;
		
		int topX = centerx;
		int topY = centery - offset;
		
		int bottomX = centerx;
		int bottomY = centery + offset;
		
		if (bottomRightX >= maxX || bottomRightY >= maxY) {
			return null;
		}
		
		if (topLeftX < 0 || topRightX < 0 || topRightY < 0 || topLeftY < 0) {
			return null;
		}
		
		bounds[0][0] = topLeftX;
		bounds[0][1] = topLeftY;
		
		bounds[1][0] = topRightX;
		bounds[1][1] = topRightY;
		
		bounds[2][0] = bottomLeftX;
		bounds[2][1] = bottomLeftY;
		
		bounds[3][0] = bottomRightX;
		bounds[3][1] = bottomRightY;
		
		bounds[4][0] = topX;
		bounds[4][1] = topY;
		
		bounds[5][0] = bottomX;
		bounds[5][1] = bottomY;
		
		bounds[6][0] = leftX;
		bounds[6][1] = leftY;
		
		bounds[7][0] = rightX;
		bounds[7][1] = rightY;
		
		return bounds;
	}
	
	/**
	 * Determines if a placed plant collides with an already existing plant in the garden
	 * @param existingCorners the array of corners for this existing plant
	 * @param placedCorners the array of corners for the proposed placed plant
	 * @return true if there is a collision, false if not
	 * @author Jaydon
	 */
	public static boolean checkCollisions(int[][] existingCorners, int[][] placedCorners) {
		// Determine if the placed plant's bound collides with the existing plant
		
		int topLeftX = existingCorners[0][0];
		int topLeftY = existingCorners[0][1];
		
		int bottomRightX = existingCorners[1][0];
		int bottomRightY = existingCorners[1][1];
		
		for(int i = 0; i < 8; i++) {
				int x = placedCorners[i][0];
				int y = placedCorners[i][1];		
				
				if(x >= topLeftX && x <= bottomRightX && y >= topLeftY && y <= bottomRightY) {
					return true;
				}
		}
		
		return false;
	}
	/**
	 * Calculates the x position of a Node in the GridPane
	 * @param target the node to get a X for
	 * @return the x position of the Node
	 * @author Jaydon
	 */
	public static int calculateXPos(Node target) {
        Integer tempx = GridPane.getColumnIndex(target);
        int x;
        
        if (tempx.equals(null)) {
        	x = 0;
        } else {
        	x = tempx;
        }
        
        return x;  
	}
	/**
	 * Calculates the y position of a Node in the GridPane
	 * @param target the node to get a Y for
	 * @return the y position of the Node
	 * @author Jaydon
	 */
	public static int calculateYPos(Node target) {
        Integer tempy = GridPane.getRowIndex(target);
		int y;
		
		if (tempy.equals(null)) {
        	y = 0;
        } else {
        	y = tempy;
        }
		
		return y;
	}
	
	/**
	 * Utility function that quickly offsets the passed val (either x or y) to the top left most position
	 * @param val Either the x or the y to offset
	 * @param size Size of the image across
	 * @return The offset position
	 * @author Jaydon
	 */
	public static int calculateOffsetPos(int val, int size) {
		return val - (size / 2);
	}
	
	/**
	 * Uses the midpoint formula to calculate the distance between two points
	 * @param x1 x position of the first point
	 * @param y1 y position of the first point
	 * @param x2 x position of the second point
	 * @param y2 y position of the second point
	 * @return The distance between the two points
	 * @author Jaydon
	 */
	public static int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.floor(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
	}
	
}
