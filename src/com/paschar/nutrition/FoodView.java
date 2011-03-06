package com.paschar.nutrition;

import android.content.ClipData;
import android.content.Context;
import android.widget.ImageView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;


public class FoodView extends ImageView {
    static final String TAG = "FoodView";
    private boolean mDragInProgress;
    private boolean mHovering;
    private boolean mAcceptsDrag;

	public FoodView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

        setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("food_id", "Food : " + v.toString());
                DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }
        });
	
	}

    /**
     * Drag and drop
     */
    @Override
    public boolean onDragEvent(DragEvent event) {
        boolean result = false;
        switch (event.getAction()) {
        case DragEvent.ACTION_DRAG_STARTED: {
            // claim to accept any dragged content
            Log.i(TAG, "Drag started, event=" + event);
            // cache whether we accept the drag to return for LOCATION events
            mDragInProgress = true;
            mAcceptsDrag = result = true;
            // Redraw in the new visual state if we are a potential drop target
            if (mAcceptsDrag) {
                invalidate();
            }
        } break;

        case DragEvent.ACTION_DRAG_ENDED: {
            Log.i(TAG, "Drag ended.");
            if (mAcceptsDrag) {
                invalidate();
            }
            mDragInProgress = false;
            mHovering = false;
        } break;

        case DragEvent.ACTION_DRAG_LOCATION: {
            // we returned true to DRAG_STARTED, so return true here
            Log.i(TAG, "... seeing drag locations ...");
            result = mAcceptsDrag;
        } break;

        case DragEvent.ACTION_DROP: {
            Log.i(TAG, "Got a drop! dot=" + this + " event=" + event);
//            if (mAnrType == ANR_DROP) {
//                sleepSixSeconds();
//            }
//            processDrop(event);
            result = true;
        } break;

        case DragEvent.ACTION_DRAG_ENTERED: {
            Log.i(TAG, "Entered dot @ " + this);
            mHovering = true;
            invalidate();
        } break;

        case DragEvent.ACTION_DRAG_EXITED: {
            Log.i(TAG, "Exited dot @ " + this);
            mHovering = false;
            invalidate();
        } break;

        default:
            Log.i(TAG, "other drag event: " + event);
            result = mAcceptsDrag;
            break;
        }

        return result;
    }
	
}
