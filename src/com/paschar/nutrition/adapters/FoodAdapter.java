package com.paschar.nutrition.adapters;

import java.util.ArrayList;

import android.R.color;
import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.paschar.nutrition.FoodObject;
import com.paschar.nutrition.FoodView;


//Adapter for the left bucket
public class FoodAdapter extends BaseAdapter{
	protected ArrayList<FoodObject> _arrayFood;
	public int category;

	public FoodAdapter(Context c) {
        mContext = c;
		SetFilter(FoodObject.FOODTYPE_GRAINS);  // default value
    }
	
	public FoodAdapter(Context c, int foodCategory) {
		mContext = c;
		SetFilter(foodCategory);
	}
	

	public void SetFilter(int foodcategory)
	{
		category = foodcategory;
		switch(foodcategory)
		{
			case FoodObject.FOODTYPE_GRAINS:
				_arrayFood = FoodObject.getGrains();
				break;
			case FoodObject.FOODTYPE_FRUITS:
				_arrayFood = FoodObject.getFruits();
				break;
			case FoodObject.FOODTYPE_VEGETABLES:
				_arrayFood = FoodObject.getVegetables();
				break;
			case FoodObject.FOODTYPE_DIARY:
				_arrayFood = FoodObject.getDairy();
				break;
			case FoodObject.FOODTYPE_MEAT:
				_arrayFood = FoodObject.getMeat();
				break;
			case FoodObject.FOODTYPE_EXTRAS:
				_arrayFood = FoodObject.getExtras();
				break;
		}
	}

    public int getCount() {
        return _arrayFood.size();
    }

    public Object getItem(int position) {
        return _arrayFood.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	ImageView iconView;
    	if (convertView == null) {
    		iconView = new FoodView(mContext);
        	iconView.setBackgroundColor(color.transparent);
        	iconView.setLayoutParams(new GridView.LayoutParams(100, 100));
           	//iconView.setAdjustViewBounds(false);
    	}
    	else {
    		iconView = (FoodView)convertView;
    	}
    	iconView.setImageResource(_arrayFood.get(position).GetDrawableId());

    	final int clickPosition = position;
    	iconView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("food_id", String.valueOf(clickPosition));
                DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }
        });
        return iconView;
    }

    private Context mContext;
}



