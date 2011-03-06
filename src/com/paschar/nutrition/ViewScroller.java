package com.paschar.nutrition;

import com.paschar.nutrition.adapters.FoodAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

public class ViewScroller extends ViewFlipper {
	static float touchDownX = 0;
	int category;
	int nextCategory;
	private Context mContext;
	
	public ViewScroller(Context context) {
		super(context);
		mContext = context;
		category = 1;
		nextCategory = 2;
	}

	public ViewScroller(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		category = 1;
		nextCategory = 2;
	}

	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("Intercept", "Intercept Event" + String.valueOf(ev.getAction()));
		//this.onTouchEvent(ev);
		//super.onInterceptTouchEvent(ev);

		// Capture it if it's on this view.
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				Log.i("Scroller", "Intercept: ACTION_DOWN");
				touchDownX = ev.getX();
				return false;
			}
		    case MotionEvent.ACTION_MOVE: {
	        	Log.i("Scroller", "Intercept: ACTION_MOVE");
	        	int distance = (int)(ev.getX() - touchDownX);

	        	// Skip tiny moves
	        	if (Math.abs(distance) >= 5){
	        		return true;
	        	}
	        	else {
	        		return false;
	        	}
		    }
		}
		return false;
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent touchEvent) {
	  super.onTouchEvent(touchEvent);
	  switch (touchEvent.getAction()) {
	    case MotionEvent.ACTION_UP: {
	    	Log.i("Scroller", "Scroller: ACTION_UP");
        	if (nextCategory < 1 || nextCategory > 6) {
        		return false;
        	}
        	
			float currentX = touchEvent.getX();
			if (touchDownX < currentX) {
				this.setInAnimation(AnimationHelper.inFromLeftAnimation());
				this.setOutAnimation(AnimationHelper.outToRightAnimation());
				this.showNext();
			}
			if (touchDownX > currentX) {
				this.setInAnimation(AnimationHelper.inFromRightAnimation());
				this.setOutAnimation(AnimationHelper.outToLeftAnimation());
				this.showPrevious();
			}
			break;
	    }
	    case MotionEvent.ACTION_MOVE: {
        	Log.i("Scroller", "Scroller: ACTION_MOVE");
        	int distance = (int)(touchEvent.getX() - touchDownX);
        	final GridView currentView = (GridView)this.getCurrentView();
        	final GridView otherView;

        	// Skip tiny moves
        	if (Math.abs(distance) < 5){
        		return false;
        	}
        	// Create next view
        	//int childCount = this.getChildCount();
        	GridView view1 = (GridView)this.getChildAt(0);
        	GridView view2 = (GridView)this.getChildAt(1);
        	if (currentView == view1){
        		otherView = view2;
        	}
        	else {
        		otherView = view1;
        	}
        	
        	// Right or left?
        	if (distance > 0) {
        		// Right
            	nextCategory = ((FoodAdapter)currentView.getAdapter()).category - 1;
        	}
        	else {
        		// Left
            	nextCategory = ((FoodAdapter)currentView.getAdapter()).category + 1;
        	}
        	
        	if (nextCategory < 1 || nextCategory > 6) {
        		return false;
        	}
        	
        	//Log.i("Scroller", "Other Category = " + String.valueOf(otherCategory));
        	FoodAdapter otherAdapter = (FoodAdapter)otherView.getAdapter();
        	if (otherAdapter == null || otherAdapter.category != nextCategory) {
        		Log.i("Scroller", "set adapter");
        		otherView.setAdapter(new FoodAdapter(this.mContext, nextCategory));
        	}
        	
        	Log.i("Moving", "distance: " + String.valueOf(distance));
        	Log.i("Moving", "width: " + String.valueOf(currentView.getWidth()));
    	    currentView.layout(distance, 
			    	    	   currentView.getTop(), 
			    	    	   currentView.getWidth() + distance, 
			    	           currentView.getBottom());
//    	    otherView.layout(currentView.getRight() + 50, 
//    	    				 currentView.getTop(), 
//    	    				 currentView.getRight() + 50 + currentView.getWidth(), 
//    	    				 currentView.getBottom());
//            otherView.setVisibility(View.VISIBLE);
	        break;
	    }
	  }
	  return true;
	}	
}

