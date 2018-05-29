package cn.aftsky.mfhm.core.delegates.bottom;

import android.widget.Toast;

import cn.aftsky.mfhm.core.R;
import cn.aftsky.mfhm.core.app.MFHM;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;

/**
 * 底部导航栏图标统一功能封装
 * Created by MaoHonglu on 2018/5/29.
 */

public abstract class BottomItemDelegate extends MFHMDelegate{

    // 再点一次退出程序时间设置  2s
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + MFHM.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
