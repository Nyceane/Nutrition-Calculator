package com.paschar.nutrition;

import java.io.File;
import java.io.FileOutputStream;

import com.paschar.utilities.IntentIntegrator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NutritionGraphActivity extends Activity{
    private WebView _webview;
    private static final int MENU_NOTES = 1;
    private static final int MENU_FACEBOOK = 2;
	private static final int MENU_TWITTER = 3;
    /** Rapid prototype, Comments will be inserted later. Trackers will be dyanmic in the future */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.foodsummary);
		ProgressDialog dialog = ProgressDialog.show(this, "", 
                "Loading, please wait...", true);
		dialog.setCancelable(false);
        String url = "http://www.ipickupsports.com/mobile/foodsummary/weekly/";
                
		// Get a reference to the declared WebView holder
		_webview = (WebView) this.findViewById(R.id.webview);
		
		//Set the webview scale to 100%
		_webview.setInitialScale(95);
		
		// Get the settings
		WebSettings webSettings = _webview.getSettings();

		// Enable Javascript for interaction
		webSettings.setJavaScriptEnabled(true);

		// Allow for touching selecting/deselecting data series
		_webview.requestFocusFromTouch();
		// Set the client
		_webview.setWebViewClient(new WebViewClient());
		_webview.setWebChromeClient(new WebChromeClient());
		_webview.loadUrl(url);
		dialog.dismiss();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		// sets the search menu button key
		menu.add(0, MENU_NOTES, 0, "Take notes")
		.setIcon(R.drawable.note_menu)
		.setAlphabeticShortcut(SearchManager.MENU_KEY);
		
		menu.add(0, MENU_FACEBOOK, 0, "Facebook")
		.setIcon(R.drawable.facebook_menu)
		.setAlphabeticShortcut(SearchManager.MENU_KEY);	        
	        
		menu.add(0, MENU_TWITTER, 0, "Twitter")
		.setIcon(R.drawable.twitter_menu)
		.setAlphabeticShortcut(SearchManager.MENU_KEY);

		return super.onCreateOptionsMenu(menu);
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_NOTES:
				IntentIntegrator notes = new IntentIntegrator(this);
				try
				{
					//Set up the picture from _weviewalt, which is a fixed size webview
				    Picture picture = _webview.capturePicture();
				    Bitmap  b = Bitmap.createBitmap( picture.getWidth(),
				                picture.getHeight(), Bitmap.Config.ARGB_8888);
			
				    Canvas c = new Canvas( b );
				    picture.draw( c );
				    
				    //Storing location of the file
					File location = new File(Environment.getExternalStorageDirectory() + "/catch/", "healthgraphsnapshot.jpg");
					
					if (location.exists()) {
						location.delete();
					}
					else
					{
						new File(Environment.getExternalStorageDirectory() + "/catch").mkdirs();			
					}
					
				    try {
				    	//s
						FileOutputStream fos=new FileOutputStream(location.getPath());			
				        if ( fos != null )
				        {
				            b.compress(Bitmap.CompressFormat.JPEG,100, fos);  
				            fos.close();
				        }
				        
				        //Assuming everything is successful
				        File thefile = new File(location.getPath());
				        if(thefile != null)
				        {
				        	Uri imageUri = Uri.fromFile(thefile);
				        	
				        	notes.createNote("Nutrition Calculator Monthly Snapshot Graph taken\n\n" +"#nutrition", imageUri);
				   	    }
				        
				    } catch (Exception e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
			
				    }
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return true;
			case MENU_FACEBOOK:
				return true;
			case MENU_TWITTER:
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
