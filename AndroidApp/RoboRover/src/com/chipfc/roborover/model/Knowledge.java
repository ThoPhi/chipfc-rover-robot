package com.chipfc.roborover.model;

import java.util.List;

public class Knowledge {

	private int imageID;
	private int typeLevel;
	
	private String infoContent;
	private int infoId;
	private List<QuizInfo> quizList;
	
	
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
	 * @return the infoContent
	 */
	public String getInfoContent() {
		return infoContent;
	}
	/**
	 * @param infoContent the infoContent to set
	 */
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	/**
	 * @return the infoId
	 */
	public int getInfoId() {
		return infoId;
	}
	/**
	 * @param infoId the infoId to set
	 */
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	/**
	 * @return the quizList
	 */
	public List<QuizInfo> getQuizList() {
		return quizList;
	}
	/**
	 * @param quizList the quizList to set
	 */
	public void setQuizList(List<QuizInfo> quizList) {
		this.quizList = quizList;
	}
	
	
}
