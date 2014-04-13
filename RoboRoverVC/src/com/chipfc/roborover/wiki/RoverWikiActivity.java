package com.chipfc.roborover.wiki;

import com.chipfc.roborover.R;
import com.chipfc.roborover.util.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RoverWikiActivity extends Activity {
	/** Called when the activity is first created. */
	public static String URL_DATA = "url data";
	
	private Context mContext;
	private Activity mActivity;
	private WebView browser;
	private ProgressBar proWebView;
	private String url;
	
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.roverwiki_layout);   
        
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
        	url = extras.getString(URL_DATA);
        }
        
        mContext = this;
        mActivity = this;
        
		init();
		setGUI();
		initEvents();
    }
    
    public void init(){
    	browser = (WebView)findViewById(R.id.webView);
    	proWebView = (ProgressBar)findViewById(R.id.proWebView);
   }
    
    public void setGUI(){
        browser.setWebViewClient(new WebViewOverrideUrl());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setPluginsEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setSupportZoom(true);  
        browser.getSettings().setBuiltInZoomControls(true);
        
        browser.getSettings().setDomStorageEnabled(true);
        
        browser.loadUrl(url);
    }
    
    public void initEvents(){
    	
    }
    
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(browser.canGoBack()){
			browser.goBack();
		}else{
			finish();
		}
	}
    
    @Override
	protected void onPause() {
		super.onPause();
	}
    
    protected void onResume() {
		super.onResume();
	}
    
    private class WebViewOverrideUrl extends WebViewClient {
    	
    	public WebViewOverrideUrl() {
        }
    	
    	@Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }
    	
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        
        @Override
        public void onPageStarted(final WebView view, final String url, Bitmap favicon ){
			proWebView.setVisibility(View.VISIBLE);
        }
        
        @Override  
        public void onPageFinished(WebView view, String url) {    
        	//stop rotating
            proWebView.setVisibility(View.GONE);
        } 
    }
	
	@Override
	public void onDestroy(){
		System.gc();
		super.onDestroy();
	}
}
