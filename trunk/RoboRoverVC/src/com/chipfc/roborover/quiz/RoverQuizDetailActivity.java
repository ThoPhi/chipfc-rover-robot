package com.chipfc.roborover.quiz;

import java.util.ArrayList;
import java.util.List;

import com.chipfc.roborover.R;
import com.chipfc.roborover.dataexample.DataForRun;
import com.chipfc.roborover.model.Knowledge;
import com.chipfc.roborover.model.QuizInfo;
import com.chipfc.roborover.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import at.abraxas.amarino.Amarino;

public class RoverQuizDetailActivity extends Activity {

	public static final String SELECTED_LEVEL = "selected level";
	
	private Activity mActivity;
	private Context mContext;
	private Toast inform;
	
	private ViewPager view_quiz;
	private List<Knowledge> listKnowledges;
	private List<QuizInfo> listQuiz;
	private QuizAdapter quizAdapter;
	
	private Button btn_yes;
	private Button btn_no;
	
	private RelativeLayout view_control;
	private Button btn_RoverForward;
	private Button btn_RoverBack;
	private Button btn_RoverTurnLeft;
	private Button btn_RoverTurnRight;
	
	private String default_device_address = Utils.DEFAULT_DEVICE_ADDRESS;
	
	private int leveltype;
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Amarino.connect(this, default_device_address);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Amarino.disconnect(this, default_device_address);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rover_quiz_detail_layout);

		mActivity = this;
		mContext = this;
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			leveltype = extras.getInt(SELECTED_LEVEL, Utils.EARTH_LEVEL);
		}
		
		loadData();
	}
	
	private void loadData(){
		init();
		initGUI();
		initEvent();
	}
	
	private void init(){
		
		view_quiz = (ViewPager)findViewById(R.id.view_pager);
		
		btn_yes = (Button)findViewById(R.id.btn_yes);
		btn_no = (Button)findViewById(R.id.btn_no);
		
		view_control = (RelativeLayout)findViewById(R.id.view_control);
		btn_RoverForward = (Button)findViewById(R.id.btn_roverforward);
		btn_RoverBack = (Button)findViewById(R.id.btn_roverback);
		btn_RoverTurnLeft = (Button)findViewById(R.id.btn_roverleft);
		btn_RoverTurnRight = (Button)findViewById(R.id.btn_roverright);
		inform = new Toast(mContext);
		
		listKnowledges = DataForRun.getDataForRoverRun(mContext, leveltype);
		listQuiz = new ArrayList<QuizInfo>();
		for(Knowledge kl: listKnowledges){
			listQuiz.addAll(kl.getQuizList());
		}
		quizAdapter = new QuizAdapter();
	}
	
	private void initGUI(){
		
		view_quiz.setPageMargin(15);
		view_quiz.setOffscreenPageLimit(1);
		view_quiz.setClipChildren(false);
		view_quiz.setAdapter(quizAdapter);
		quizAdapter.notifyDataSetChanged();
		
		btn_RoverForward.setBackgroundResource(R.drawable.arrow_icon_315);
		btn_RoverBack.setBackgroundResource(R.drawable.arrow_icon_90);
		btn_RoverTurnLeft.setBackgroundResource(R.drawable.arrow_icon_180);
		btn_RoverTurnRight.setBackgroundResource(R.drawable.arrow_icon);
		
		enableControlArea(false);
	}
	
	private void initEvent(){
		
		View.OnClickListener anwserAction = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int viewId = v.getId();
				int quizIndex = view_quiz.getCurrentItem();
				QuizInfo curQuiz = listQuiz.get(quizIndex);
				
				if(viewId == R.id.btn_yes){
					enableControlArea(curQuiz.getCorrectAnwserId() == 1);
				}else if(viewId == R.id.btn_no){
					enableControlArea(curQuiz.getCorrectAnwserId() == 0);
				}
			}
		};
		
		btn_yes.setOnClickListener(anwserAction);
		btn_no.setOnClickListener(anwserAction);
		
		view_quiz.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				enableControlArea(false);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		View.OnClickListener onclickLtn = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int viewId = v.getId();
				if(viewId == R.id.btn_roverforward){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover forward", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					setNextKnowledge();
					sentAction(Utils.FORWARD, Utils.DEFAULT_DURATION);
					sentAction(Utils.BEGIN, Utils.DEFAULT_DURATION);
				}else if(viewId == R.id.btn_roverback){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover back", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					setNextKnowledge();
					sentAction(Utils.BACKWARD, Utils.DEFAULT_DURATION);
					sentAction(Utils.BEGIN, Utils.DEFAULT_DURATION);
				}else if(viewId == R.id.btn_roverleft){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover left", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					setNextKnowledge();
					sentAction(Utils.LEFT, Utils.DEFAULT_DURATION);
					sentAction(Utils.BEGIN, Utils.DEFAULT_DURATION);
				}else if(viewId == R.id.btn_roverright){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover right", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					setNextKnowledge();
					sentAction(Utils.RIGHT, Utils.DEFAULT_DURATION);
					sentAction(Utils.BEGIN, Utils.DEFAULT_DURATION);
				}
			}
		};
		
		btn_RoverForward.setOnClickListener(onclickLtn);
		btn_RoverBack.setOnClickListener(onclickLtn);
		btn_RoverTurnLeft.setOnClickListener(onclickLtn);
		btn_RoverTurnRight.setOnClickListener(onclickLtn);
	}
	
	private void enableControlArea(boolean isEnable){
		btn_RoverForward.setEnabled(isEnable);
		btn_RoverBack.setEnabled(isEnable);
		btn_RoverTurnLeft.setEnabled(isEnable);
		btn_RoverTurnRight.setEnabled(isEnable);
		view_control.setEnabled(isEnable);
		if(isEnable){
			
		}else{
			
		}
	}
	
	private void sentAction(char action, int duration){
		Amarino.sendDataToArduino(mContext, default_device_address, action, duration);
	}
	
	private void setNextKnowledge(){
		int curIndex = view_quiz.getCurrentItem();
		if(curIndex == listKnowledges.size()-1){
			view_quiz.setCurrentItem(0, true);
		}else{
			view_quiz.setCurrentItem(curIndex+1, true);
		}
	}
	
	private class QuizAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listQuiz.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == (View)arg1;
		}
		
		@Override
	    public int getItemPosition(Object object) {
	        return POSITION_NONE;
	    }
	    
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			View view = LayoutInflater.from(mContext).inflate(R.layout.knowledge_layout, null);
			ImageView img_KnowledgeImage = (ImageView)view.findViewById(R.id.knowledge_image);
			EditText txt_KnowledgeContent = (EditText)view.findViewById(R.id.txt_knowledge);
			
			final QuizInfo data = listQuiz.get(position);
			

			img_KnowledgeImage.setImageResource(data.getQuizImage());
//			img_KnowledgeImage.setImageResource(R.drawable.ic_launcher);
			txt_KnowledgeContent.setText(data.getQuizContent());
			
			container.addView(view);
			
			return view;
		}
	}
	
}
