package com.example.admin.movies;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by admin on 23-12-2016.
 */

public class SpaceDecorator extends RecyclerView.ItemDecoration {

    private int spaceInPixels;
    private int gridSize;



    public SpaceDecorator(int spaceInPixels,int gridSize) {
        this.spaceInPixels = spaceInPixels;
        this.gridSize = gridSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
              int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();

        if( itemPosition == gridSize - 1 || itemPosition == gridSize - 2 ){
            outRect.top = spaceInPixels;
            outRect.bottom = spaceInPixels;
        }
        else {
            outRect.top = spaceInPixels;
        }

        outRect.left = spaceInPixels/2;
        outRect.right = spaceInPixels/2;

    }
}


