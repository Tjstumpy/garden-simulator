package com.collective.app.dtos;

/**
 * this is the data object for the functionality of the object page
 * @author Aaron
 *
 */
public class OptionsPageModel {
	/**
	 * this is the current volume of the music within the program
	 */
	private double volume;
	/**
	 * this to see if the music is muted or not
	 */
	private boolean mute;
	/**
	 * this is to see if the application is fullscreen or not
	 */
	private boolean fullscreen;
	
	private boolean darkMode;
	/**
	 * this is the contructor for the object page and it's values that it needs from the controller
	 * @param volume, this is the current volume of the music
	 * @param mute, this is if the music is muted or not
	 * @param fullscreen, this is if it is full screen or not
	 * @author Aaron
	 */
	public OptionsPageModel(double d, boolean mute, boolean fullscreen, boolean darkMode) {
		this.volume = d;
		this.mute = mute;
		this.fullscreen = fullscreen;
		this.darkMode = darkMode;
	}
	/**
	 * this is the getter for the current volume
	 * @return int, volume of the current program
	 * @author Aaron
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 *  this is the getter for the boolean isMuted 
	 * @return boolean, is it muted or not
	 * @author Aaron
	 */
	public boolean isMute() {
		return mute;
	}
	/**
	 * this is the getter for the boolean fullscreen
	 * @return boolean, is it fullscreen or not
	 * @author Aaron
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	public boolean isDarkMode() {
		return darkMode;
	}

}
