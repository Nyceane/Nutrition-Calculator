package com.paschar.nutrition;

import android.content.ClipData;
import android.content.Context;
import android.widget.ImageView;
import android.view.View;


public class FoodView extends ImageView {
    static final String TAG = "FoodView";
    public int mFoodId;

	public FoodView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

        setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
            	FoodView fv = (FoodView)v;
                ClipData data = ClipData.newPlainText("food_id", String.valueOf(fv.mFoodId));
                DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }
        });
	
	}

	
}
