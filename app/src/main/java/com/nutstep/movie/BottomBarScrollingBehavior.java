package com.nutstep.movie;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class BottomBarScrollingBehavior extends CoordinatorLayout.Behavior<AppBarLayout> {

    public BottomBarScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        android.support.design.widget.CoordinatorLayout.Behavior behavior = ((android.support.design.widget.CoordinatorLayout.LayoutParams)dependency.getLayoutParams()).getBehavior();
        if(behavior instanceof AppBarLayout.Behavior) {
            // do stuff here
            final AppBarLayout.Behavior ablBehavior = (AppBarLayout.Behavior) behavior;
            child.offsetTopAndBottom(555555);
        }
        return true;
    }

}
