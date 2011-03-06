package com.paschar.nutrition;

import java.util.ArrayList;

import com.paschar.nutrition.adapters.FoodAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.view.ViewGroup;


public class ViewScroller extends ViewFlipper {
	float oldTouchValue = 0;
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
	public boolean onTouchEvent(MotionEvent touchEvent) {
	  super.onTouchEvent(touchEvent);
	  Log.i("Scroller", "Touch Event" + String.valueOf(touchEvent.getAction()));
	  switch (touchEvent.getAction()) {
	    case MotionEvent.ACTION_DOWN: {
	    	Log.i("Scroller", "ACTION_DOWN");
	    	oldTouchValue = touchEvent.getX();
	    	break;
	    }
	    case MotionEvent.ACTION_UP: {
	    	Log.i("Scroller", "ACTION_UP");
        	if (nextCategory < 1 || nextCategory > 6) {
        		return false;
        	}
        	
			float currentX = touchEvent.getX();
			if (oldTouchValue < currentX) {
				this.setInAnimation(AnimationHelper.inFromLeftAnimation());
				this.setOutAnimation(AnimationHelper.outToRightAnimation());
				this.showNext();
			}
			if (oldTouchValue > currentX) {
				this.setInAnimation(AnimationHelper.inFromRightAnimation());
				this.setOutAnimation(AnimationHelper.outToLeftAnimation());
				this.showPrevious();
			}
			break;
	    }
	    case MotionEvent.ACTION_MOVE: {
        	//Log.i("Scroller", "ACTION_MOVE");
        	int distance = (int)(touchEvent.getX() - oldTouchValue);
        	final GridView currentView = (GridView)this.getCurrentView();
        	final GridView otherView;

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
        	
    	    currentView.layout(distance, currentView.getTop(), currentView.getRight(), 
    	            currentView.getBottom());
    	    //otherView.layout(currentView.getRight(), otherView.getTop(), 
            //        currentView.getRight() + otherView.getWidth(), 
            //        otherView.getBottom());
            //otherView.setVisibility(View.VISIBLE);
	        break;
	    }
	  }
	  return true;
	}	
}

