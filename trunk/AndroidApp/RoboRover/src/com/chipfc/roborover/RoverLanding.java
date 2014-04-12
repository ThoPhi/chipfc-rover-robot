package com.chipfc.roborover;

import com.chipfc.roborover.run.RoverRunActivity;
import com.chipfc.roborover.util.Utils;
import com.chipfc.roborover.wiki.RoverWikiActivity;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import com.chipfc.roborover.R;;
/**
 * @author Dragonboy
 *
 */
public class RoverLanding extends Activity {

	private Activity mActivity;
	private Context mContext;
	private Toast inform;
	
	private Button btn_RoverRun;
	private Button btn_RoverQuiz;
	private Button btn_RoverWiki;
	private Button btn_RoverAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rover_landing_layout);

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
		btn_RoverRun = (Button)findViewById(R.id.btn_roverrun);
		btn_RoverQuiz = (Button)findViewById(R.id.btn_roverquiz);
		btn_RoverWiki = (Button)findViewById(R.id.btn_roverwiki);
		btn_RoverAbout = (Button)findViewById(R.id.btn_roverabout);
	}
	
	private void initGUI(){
		btn_RoverRun.setText("Rover Run");
		btn_RoverQuiz.setText("Rover Quiz");
		btn_RoverWiki.setText("Rover Wiki");
		btn_RoverAbout.setText("Rover About");
	}
	
	private void initEvent(){
		View.OnClickListener onclickLtn = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent delegateView = null;
				
				int viewId = v.getId();
				if(viewId == R.id.btn_roverrun){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover run", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					delegateView = new Intent(mContext, RoverRunActivity.class);
				}else if(viewId == R.id.btn_roverquiz){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover quiz", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					
				}else if(viewId == R.id.btn_roverwiki){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover wiki", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					delegateView = new Intent(mContext, RoverWikiActivity.class);
					delegateView.putExtra(RoverWikiActivity.URL_DATA, Utils.ROVER_WIKI_HOMEPAGE);
				}else if(viewId == R.id.btn_roverabout){
					if(inform!=null) inform.cancel();
					inform = Toast.makeText(mContext, "rover about", Toast.LENGTH_SHORT);
					inform.show();
					// TODO Auto-generated method stub
					delegateView = new Intent(mContext, RoverWikiActivity.class);
					delegateView.putExtra(RoverWikiActivity.URL_DATA, Utils.ROVER_ABOUT_CHIPFC);
					
				}
				
				if(delegateView!=null){
					mContext.startActivity(delegateView);
				}
				
			}
		};
		
		btn_RoverRun.setOnClickListener(onclickLtn);
		btn_RoverQuiz.setOnClickListener(onclickLtn);
		btn_RoverWiki.setOnClickListener(onclickLtn);
		btn_RoverAbout.setOnClickListener(onclickLtn);
	}

	
	
}
