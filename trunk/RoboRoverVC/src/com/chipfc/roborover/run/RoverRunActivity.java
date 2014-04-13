package com.chipfc.roborover.run;

import java.util.ArrayList;
import java.util.List;

import com.chipfc.roborover.R;
import com.chipfc.roborover.dataexample.DataForRun;
import com.chipfc.roborover.model.Knowledge;
import com.chipfc.roborover.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import at.abraxas.amarino.Amarino;

public class RoverRunActivity extends Activity {

	private Activity mActivity;
	private Context mContext;
	private Toast inform;
	
	private ViewPager view_Knowledge;
	private List<Knowledge> listKnowledges;
	private KnowledgeAdapter knowledgeAdapter;
	
	private Button btn_RoverForward;
	private Button btn_RoverBack;
	private Button btn_RoverTurnLeft;
	private Button btn_RoverTurnRight;
	
	private String default_device_address = Utils.DEFAULT_DEVICE_ADDRESS;
	
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
		setContentView(R.layout.rover_run_layout);

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
		
		view_Knowledge = (ViewPager)findViewById(R.id.view_pager);
		btn_RoverForward = (Button)findViewById(R.id.btn_roverforward);
		btn_RoverBack = (Button)findViewById(R.id.btn_roverback);
		btn_RoverTurnLeft = (Button)findViewById(R.id.btn_roverleft);
		btn_RoverTurnRight = (Button)findViewById(R.id.btn_roverright);
		inform = new Toast(mContext);
		
		listKnowledges = new ArrayList<Knowledge>();
		listKnowledges.addAll(DataForRun.getDataForRoverRun(mContext, Utils.EARTH_LEVEL));
		listKnowledges.addAll(DataForRun.getDataForRoverRun(mContext, Utils.SUN_LEVEL));
		knowledgeAdapter = new KnowledgeAdapter();
	}
	
	private void initGUI(){
		
		view_Knowledge.setPageMargin(15);
		view_Knowledge.setOffscreenPageLimit(1);
		view_Knowledge.setClipChildren(false);
		view_Knowledge.setAdapter(knowledgeAdapter);
		knowledgeAdapter.notifyDataSetChanged();
		
		btn_RoverForward.setBackgroundResource(R.drawable.arrow_icon_315);
		btn_RoverBack.setBackgroundResource(R.drawable.arrow_icon_90);
		btn_RoverTurnLeft.setBackgroundResource(R.drawable.arrow_icon_180);
		btn_RoverTurnRight.setBackgroundResource(R.drawable.arrow_icon);
	}
	
	private void initEvent(){
		
		view_Knowledge.setOnTouchListener(null);
		
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
	
	private void sentAction(char action, int duration){
		Amarino.sendDataToArduino(mContext, default_device_address, action, duration);
	}
	
	private void setNextKnowledge(){
		int curIndex = view_Knowledge.getCurrentItem();
		if(curIndex == listKnowledges.size()-1){
			view_Knowledge.setCurrentItem(0, true);
		}else{
			view_Knowledge.setCurrentItem(curIndex+1, true);
		}
	}
	
	private class KnowledgeAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listKnowledges.size();
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
			
			final Knowledge data = listKnowledges.get(position);
			

			img_KnowledgeImage.setImageResource(data.getImageID());
//			img_KnowledgeImage.setImageResource(R.drawable.ic_launcher);
			txt_KnowledgeContent.setText(data.getInfoContent());
			
			container.addView(view);
			
			return view;
		}
	}
	
}
