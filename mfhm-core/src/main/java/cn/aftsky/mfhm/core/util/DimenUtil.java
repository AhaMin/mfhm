package cn.aftsky.mfhm.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import cn.aftsky.mfhm.core.app.MFHM;

/**
 * Created by MaoHonglu on 2018/5/21.
 */

public class DimenUtil {


    public static int getScreenWidth(){
        final Resources resources = MFHM.getApplicationContext().getResources();
        final DisplayMetrics dm  = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = MFHM.getApplicationContext().getResources();
        final DisplayMetrics dm  = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
