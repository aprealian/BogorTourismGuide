package com.teknokrait.bogortourismguide.view.dev;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sirius on 4/29/2017.
 */

public class DeactivatedViewPager extends ViewPager {

    private boolean enabled;

    public DeactivatedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*@Override
    public void scrollTo(int x, int y) {
        if(enabled) {
            super.scrollTo(x, y);
        }
    }*/

}