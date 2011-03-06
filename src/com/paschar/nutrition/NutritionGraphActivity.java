package com.paschar.nutrition;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NutritionGraphActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Hide the title bar        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.foodsummary);

    }
}
