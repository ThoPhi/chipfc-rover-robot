package com.chipfc.roborover.model;

import java.util.List;

public class QuizInfo {

	private String quizContent;
	private int quizId;
	private List<Anwser> answerList;
	private int correctAnwserId;
	/**
	 * @return the quizContent
	 */
	public String getQuizContent() {
		return quizContent;
	}
	/**
	 * @param quizContent the quizContent to set
	 */
	public void setQuizContent(String quizContent) {
		this.quizContent = quizContent;
	}
	/**
	 * @return the quizId
	 */
	public int getQuizId() {
		return quizId;
	}
	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	/**
	 * @return the answerList
	 */
	public List<Anwser> getAnswerList() {
		return answerList;
	}
	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(List<Anwser> answerList) {
		this.answerList = answerList;
	}
	/**
	 * @return the correctAnwserId
	 */
	public int getCorrectAnwserId() {
		return correctAnwserId;
	}
	/**
	 * @param correctAnwserId the correctAnwserId to set
	 */
	public void setCorrectAnwserId(int correctAnwserId) {
		this.correctAnwserId = correctAnwserId;
	}
	
	
}
