package com.chipfc.roborover.model;

public class Level {

	private int imageID;
	private int typeLevel;
	private String levelName;
	private boolean isLocked = true;
	/**
	 * @return the imageID
	 */
	public int getImageID() {
		return imageID;
	}
	/**
	 * @param imageID the imageID to set
	 */
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	/**
	 * @return the typeLevel
	 */
	public int getTypeLevel() {
		return typeLevel;
	}
	/**
	 * @param typeLevel the typeLevel to set
	 */
	public void setTypeLevel(int typeLevel) {
		this.typeLevel = typeLevel;
	}
	
	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}
	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
}
