package com.paschar.nutrition;

import java.util.ArrayList;

import com.paschar.nutrition.R;
import com.paschar.nutrition.adapters.FoodAdapter;
import com.paschar.nutrition.logics.Membership;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class FoodIntake extends Activity {
    static final String TAG = "FoodIntake";

    private ViewFlipper flipper;
    private GridView gridFood;
	private GridView gridIntake;
	
	private TextView txtFoodCategory;
	
	private ImageButton btnRew;
	private ImageButton btnForward;
	private int currentCategory;
	
	protected ArrayList<FoodObject> _arrayIntake;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		gridIntake = (GridView)findViewById(R.id.gridIntake);
		gridFood = (GridView)findViewById(R.id.gridFood);
		txtFoodCategory = (TextView)findViewById(R.id.txtFoodCategory);
		
		btnRew = (ImageButton)findViewById(R.id.btnRew);
		btnForward = (ImageButton)findViewById(R.id.btnForward);
		//Setup Food
		SetFoodFilter(FoodObject.FOODTYPE_GRAINS);

		//Setup Intake
		_arrayIntake = new ArrayList<FoodObject>();
		gridIntake.setOnDragListener(new BoxDragListener());

		disableEvents(flipper);
	}

	public void disableEvents(ViewGroup viewGroup) {
		Log.i("Events", "===============disabling start");
    	//ArrayList<View> views = view.getTouchables();
    	int viewCount = viewGroup.getChildCount();
    	Log.i("Events", "View Count = " + String.valueOf(viewCount));
    	for (int i=0; i < viewCount; i++){
    		Log.i("Events", "----------------disable onTouch");
    		View v = viewGroup.getChildAt(i);
        	v.setOnTouchListener(new View.OnTouchListener() {
    			
    			@Override
    			public boolean onTouch(View v, MotionEvent event) {
    				// TODO Auto-generated method stub
    				return false;
    			}
    		});
        	if (v instanceof ViewGroup) {
            	disableEvents((ViewGroup)v);
        	}
    	}
	}
	
	
	public void SetFoodFilter(int foodcategory)
	{
		currentCategory = foodcategory;
		if(foodcategory == FoodObject.FOODTYPE_GRAINS)
		{
			btnRew.setEnabled(false);
		}
		else if(foodcategory == FoodObject.FOODTYPE_EXTRAS)
		{
			btnForward.setEnabled(false);
		}
		else
		{
			btnRew.setEnabled(true);
			btnForward.setEnabled(true);
		}
		switch(foodcategory)
		{
			case FoodObject.FOODTYPE_GRAINS:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_grain));
				break;
			case FoodObject.FOODTYPE_FRUITS:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_fruits));
				break;
			case FoodObject.FOODTYPE_VEGETABLES:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_vegetables));
				break;
			case FoodObject.FOODTYPE_DIARY:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_dairy));
				break;
			case FoodObject.FOODTYPE_MEAT:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_meats));
				break;
			case FoodObject.FOODTYPE_EXTRAS:
				txtFoodCategory.setText(getResources().getString(R.string.food_filter_title) + getResources().getString(R.string.category_extras));
				break;
		}
		gridFood.setAdapter(new FoodAdapter(this, foodcategory));
	}
	
	public void btnForward_Clicked(View target)
	{
		SetFoodFilter(currentCategory + 1);
	}
	
	public void btnRew_Clicked(View target)
	{
		SetFoodFilter(currentCategory - 1);
	}

	/**
	 * Remove food from food intake
	 * @param position
	 */
	public void RemoveFood(int position)
	{
		_arrayIntake.remove(position);
		gridIntake.setAdapter(new IntakeAdapter(this));  
	}
	
	/**
	 * Add food to the bucket where food was taken
	 * @param position: position at _arrayFood to indicate which object it is
	 */
	public void AddFood(int position)
	{
		Log.i("Drag", "Add Food: " + String.valueOf(position));
		boolean neverTaken = true;
		GridView grid = (GridView)flipper.getCurrentView();
		FoodAdapter adapter = (FoodAdapter)grid.getAdapter();
		FoodObject foodtaken =  (FoodObject)adapter.getItem(position);
		for(int i = 0; i < _arrayIntake.size(); i++) {
			if(_arrayIntake.get(i).GetFoodId() == foodtaken.GetFoodId())
			{
				_arrayIntake.get(i).AddServing();
				neverTaken = false;
				break;
			}
		}
		
		if(neverTaken)
		{
			_arrayIntake.add(foodtaken);

		}
		
		gridIntake.setAdapter(new IntakeAdapter(FoodIntake.this));  
	}
	
	public void btnCalculate_Clicked(View target)
	{
		
		String url = "http://www.ipickupsports.com/mobile/foodsummary/Default.aspx?FoodIdArray=";
		for(int i = 0; i < _arrayIntake.size(); i++)
		{
			for(int j = 0; j < _arrayIntake.get(i).GetServings(); j++)
			{
				url += String.valueOf(_arrayIntake.get(i).GetFoodId());
				url += ",";
			}
		}

		url = url.substring(0, url.length() - 1);
		
		Intent summary = new Intent(this, CalculationResult.class);
		summary.putExtra("url", url);
		startActivity(summary);
	}
	
	/**
	 * Launch Filter Types
	 */
	public void btnFilter_Click(View target)
	{
		AlertDialog alert = new AlertDialog.Builder(FoodIntake.this)
        .setTitle(getString(R.string.food_filter_title))
        .setItems(R.array.food_category, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	FoodIntake.this.SetFoodFilter(which + 1);
            }
        })
        .create();
		
		alert.show();
	}
	
	//Adapter for the right bucket
    public class IntakeAdapter extends BaseAdapter {
        public IntakeAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return _arrayIntake.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	
        	FrameLayout returnView = new FrameLayout(mContext);
            if (convertView == null) {
            	TextView servingText = new TextView(mContext);
            	servingText.setText(String.valueOf(_arrayIntake.get(position).GetServings()));
            	servingText.setLayoutParams(new GridView.LayoutParams(100, 100));
            	servingText.setBackgroundResource(_arrayIntake.get(position).GetDrawableId());
            	servingText.setTag(position);
            	servingText.setTextColor(Color.BLACK);
            	servingText.setOnClickListener(new View.OnClickListener() {
                 	public void onClick(View view) {
                       	TextView textView = (TextView)view;
                      	FoodIntake.this.RemoveFood(Integer.valueOf(textView.getTag().toString()));
            		}
            	});            		
            	
            	returnView.addView(servingText);
            } else {
            	returnView = (FrameLayout) convertView;
            }
            
            return returnView;
        }

        private Context mContext;
    }

    class ANRShadowBuilder extends DragShadowBuilder {
        boolean mDoAnr;

        public ANRShadowBuilder(View view, boolean doAnr) {
            super(view);
            mDoAnr = doAnr;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }
    }
    
    class BoxDragListener implements OnDragListener{
        boolean insideOfMe = false;
        Drawable border = null;
        //Drawable redBorder = getResources().getDrawable(R.drawable.border3);
        @Override
        public boolean onDrag(View self, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED){
                        border = self.getBackground();
                        gridIntake.setBackgroundColor(Color.CYAN);
                        gridIntake.setBackgroundColor(Color.argb(85, 58, 80, 80));
                } else if (event.getAction() == DragEvent.ACTION_DRAG_ENTERED){ 
                        insideOfMe = true;
                        gridIntake.setBackgroundColor(Color.argb(85, 89, 72, 52));
                } else if (event.getAction() == DragEvent.ACTION_DRAG_EXITED){
                        insideOfMe = false;
                        gridIntake.setBackgroundColor(Color.TRANSPARENT);
                } else if (event.getAction() == DragEvent.ACTION_DROP){
                        if (insideOfMe){
                    		ClipData clipData = event.getClipData();
                        	int position = Integer.parseInt(String.valueOf(clipData.getItemAt(0).getText()));
                    		FoodIntake.this.AddFood(Integer.valueOf(position));
                        }
                    	Log.i(TAG, "Dropped the icon");
                } else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED){
                        self.setBackgroundDrawable(border);        
                    	Log.i(TAG, "Drag Ended");
                }
                return true;
        }
    }


    public void btnPayment_Clicked(View view)
	{
        Intent myIntent = new Intent(this, Payment.class);
        startActivityForResult(myIntent, 0);
    }
    
    public void btnGraph_Clicked(View view)
	{
        Intent myIntent = new Intent(this, NutritionGraphActivity.class);
        startActivityForResult(myIntent, 0);
    }
    
	//Menu Options
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {			
			case R.id.menu_monthly:
				boolean isActivated = Membership.CheckIsMemberActive(this);
				if(isActivated)
				{
					btnGraph_Clicked(null);
				}
				else
				{
					btnPayment_Clicked(null);	
				}
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}