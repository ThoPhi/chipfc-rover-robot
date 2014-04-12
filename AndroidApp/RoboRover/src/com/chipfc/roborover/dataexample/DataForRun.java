package com.chipfc.roborover.dataexample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.chipfc.roborover.model.Anwser;
import com.chipfc.roborover.model.Knowledge;
import com.chipfc.roborover.model.QuizInfo;
import com.chipfc.roborover.util.Utils;

public class DataForRun {
	public static List<Knowledge> getDataForRoverRun(Context mContext){
		List<Knowledge> results = new ArrayList<Knowledge>();
		for(int i=0;i < 4; i++){
			Knowledge earth = new Knowledge();
			earth.setImageID(i);
			earth.setTypeLevel(Utils.EARTH_LEVEL);
			earth.setInfoContent("trai dat la trai dat knowledge "+ i);
			earth.setInfoId(i);
			earth.setQuizList(getQuizList(earth.getInfoId()));
			results.add(earth);
		}
		
		return results;
	}
	
	public static List<QuizInfo> getQuizList(int knowledgeId){
		List<QuizInfo> quizList = new ArrayList<QuizInfo>();
		for(int i=0; i < 3; i++){
			QuizInfo quiz = new QuizInfo();
			quiz.setQuizId(i);
			quiz.setCorrectAnwserId(0);
			quiz.setQuizContent("Quiz content " + i + " of knowledge "+ knowledgeId);
			quiz.setAnswerList(getAnswer(knowledgeId, i));
			quizList.add(quiz);
		}
		return quizList;
	}
	
	public static List<Anwser> getAnswer(int knowledgeId, int quizId){
		List<Anwser> anwserList = new ArrayList<Anwser>();
		
		for(int i= 0; i<3 ; i++){
			Anwser anwser = new Anwser();
			anwser.setAnwserContent("example anwser "+ i + " of quiz " + quizId + " in knowledge " + knowledgeId);
			anwser.setAnwserId(i);
			anwserList.add(anwser);
		}
		
		return anwserList;
	}
}
