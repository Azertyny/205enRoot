package e.clement.a205enroot;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
        //setMyScroller();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}