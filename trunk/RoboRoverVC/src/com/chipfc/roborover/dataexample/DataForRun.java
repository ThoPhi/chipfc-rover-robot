package com.chipfc.roborover.dataexample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.chipfc.roborover.R;
import com.chipfc.roborover.model.Anwser;
import com.chipfc.roborover.model.Knowledge;
import com.chipfc.roborover.model.Level;
import com.chipfc.roborover.model.QuizInfo;
import com.chipfc.roborover.util.Utils;

public class DataForRun {
	
	public static List<Level> getAllLevel(Context mContext){
		List<Level> allLevel = new ArrayList<Level>();
		Level earth = new Level();
		earth.setImageID(R.drawable.planet_earth);
		earth.setTypeLevel(Utils.EARTH_LEVEL);
		earth.setLevelName("EARTH");
		earth.setLocked(false);
		allLevel.add(earth);
		
		Level sun = new Level();
		sun.setImageID(R.drawable.planet_sun);
		sun.setTypeLevel(Utils.SUN_LEVEL);
		sun.setLevelName("SUN");
		sun.setLocked(false);
		allLevel.add(sun);
		
		return allLevel;
	}
	
	public static List<Level> getAllUnlockLevel(Context mContext){
		List<Level> allUnlockLevel = new ArrayList<Level>();
		Level earth = new Level();
		earth.setImageID(R.drawable.planet_earth);
		earth.setTypeLevel(Utils.EARTH_LEVEL);
		earth.setLevelName("EARTH");
		earth.setLocked(false);
		return allUnlockLevel;
	}
	
	public static List<Knowledge> getDataForRoverRun(Context mContext, int typeLevel){
		List<Knowledge> results = new ArrayList<Knowledge>();
		switch (typeLevel) {
		case Utils.EARTH_LEVEL:
			Knowledge earth = new Knowledge();
			earth.setImageID(R.drawable.planet_earth);
			earth.setTypeLevel(typeLevel);
			earth.setInfoContent(mContext.getString(R.string.knowledge1_earth));
			earth.setInfoId(R.array.quizsofkl1_earth);
			earth.setQuizList(getQuizList(mContext, earth));
			results.add(earth);
			
			Knowledge earth1 = new Knowledge();
			earth1.setImageID(R.drawable.planet_earth);
			earth1.setTypeLevel(typeLevel);
			earth1.setInfoContent(mContext.getString(R.string.knowledge2_earth));
			earth1.setInfoId(R.array.quizsofkl2_earth);
			earth1.setQuizList(getQuizList(mContext, earth1));
			results.add(earth1);
			
			break;
		case Utils.SUN_LEVEL:
			Knowledge sun = new Knowledge();
			sun.setImageID(R.drawable.planet_sun);
			sun.setTypeLevel(typeLevel);
			sun.setInfoContent(mContext.getString(R.string.knowledge1_sun));
			sun.setInfoId(R.array.quizsofkl1_sun);
			sun.setQuizList(getQuizList(mContext, sun));
			results.add(sun);
			
			Knowledge sun1 = new Knowledge();
			sun1.setImageID(R.drawable.planet_sun);
			sun1.setTypeLevel(typeLevel);
			sun1.setInfoContent(mContext.getString(R.string.knowledge2_sun));
			sun1.setInfoId(R.array.quizsofkl2_sun);
			sun1.setQuizList(getQuizList(mContext, sun1));
			results.add(sun1);
			
			break;

		}
		return results;
	}
	
	public static List<QuizInfo> getQuizList(Context mContext, Knowledge knowledge){
		
		List<QuizInfo> quizList = new ArrayList<QuizInfo>();
		String[] quizs = mContext.getResources().getStringArray(knowledge.getInfoId());
		for(int i=0; i< quizs.length - 1; i+=2){
			QuizInfo quiz = new QuizInfo();
			quiz.setQuizImage(knowledge.getImageID());
			quiz.setQuizId(i);
			quiz.setCorrectAnwserId(Integer.valueOf(quizs[i+1]));
			quiz.setQuizContent(quizs[i]);
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
