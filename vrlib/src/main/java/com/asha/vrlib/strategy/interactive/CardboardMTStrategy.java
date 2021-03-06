package com.asha.vrlib.strategy.interactive;

import com.asha.vrlib.MD360Director;

import android.content.res.Resources;

/**
 * Created by hzqiujiadi on 16/10/13.
 * hzqiujiadi ashqalcn@gmail.com
 */
public class CardboardMTStrategy extends CardboardMotionStrategy {

    private static final float sDensity =  Resources.getSystem().getDisplayMetrics().density;

    private static final float sDamping = 0.2f;

    public CardboardMTStrategy(InteractiveModeManager.Params params) {
        super(params);
    }

    @Override
    public boolean handleDrag(int distanceX, int distanceY) {
        for (MD360Director director : getDirectorList()){
            director.setDeltaX(director.getDeltaX() - distanceX / sDensity * sDamping);
            director.setDeltaY(director.getDeltaY() - distanceY / sDensity * sDamping);
        }
        return false;
    }

}
