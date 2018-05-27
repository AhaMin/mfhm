package cn.aftsky.mfhm.core.util;

import java.util.TimerTask;

/**
 * Created by MaoHonglu on 2018/5/27.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener =null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
