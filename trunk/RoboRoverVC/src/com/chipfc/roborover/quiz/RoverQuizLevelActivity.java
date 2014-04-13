package com.chipfc.roborover.quiz;

import java.util.List;

import com.chipfc.roborover.R;
import com.chipfc.roborover.dataexample.DataForRun;
import com.chipfc.roborover.model.Knowledge;
import com.chipfc.roborover.model.Level;
import com.chipfc.roborover.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import at.abraxas.amarino.Amarino;

public class RoverQuizLevelActivity extends Activity {

	private Activity mActivity;
	private Context mContext;
	private Toast inform;
	
	private ViewPager view_Level;
	private List<Level> listLevels;
	private LevelAdapter LevelAdapter;
	private String default_device_address = Utils.DEFAULT_DEVICE_ADDRESS;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rover_quiz_layout);

		mActivity = this;
		mContext = this;
		
		loadData();
	}
	
	private void loadData(){
		init();
		initGUI();
		initEvent();
	}
	
	private void init(){
		
		view_Level = (ViewPager)findViewById(R.id.view_pager);
		inform = new Toast(mContext);
		
		listLevels = DataForRun.getAllLevel(mContext);
		LevelAdapter = new LevelAdapter();
	}
	
	private void initGUI(){
		
		view_Level.setPageMargin(15);
		view_Level.setOffscreenPageLimit(1);
		view_Level.setClipChildren(false);
		view_Level.setAdapter(LevelAdapter);
		LevelAdapter.notifyDataSetChanged();
	}
	
	private void initEvent(){
	}
	
	private class LevelAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listLevels.size();
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
			View view = LayoutInflater.from(mContext).inflate(R.layout.level_layout, null);
			ImageView img_LevelImage = (ImageView)view.findViewById(R.id.img_levelimg);
			TextView txt_LevelContent = (TextView)view.findViewById(R.id.txt_levelname);
			TextView txt_locked = (TextView)view.findViewById(R.id.txt_locked);
			
			final Level data = listLevels.get(position);
			
			img_LevelImage.setImageResource(data.getImageID());
//			img_LevelImage.setImageResource(R.drawable.ic_launcher);
			txt_LevelContent.setText(data.getLevelName());
			
			
			if(data.isLocked()){
				txt_locked.setVisibility(View.VISIBLE);
				view.setOnClickListener(null);
				view.setEnabled(false);
			}else{
				txt_locked.setVisibility(View.GONE);
				view.setEnabled(true);
				view.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent quizDetail = new Intent(mContext, RoverQuizDetailActivity.class);
						quizDetail.putExtra(RoverQuizDetailActivity.SELECTED_LEVEL,	data.getTypeLevel());
						startActivity(quizDetail);
					}
				});
			}
			

			
			
			
			container.addView(view);
			
			return view;
		}
	}
	
}
