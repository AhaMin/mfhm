package cn.aftsky.mfhm;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.aftsky.mfhm.core.MFHM;
import cn.aftsky.mfhm.main.icon.FontMFHMModule;

/**
 * Created by MaoHonglu on 2018/5/12.
 */

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MFHM.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontMFHMModule())
                .withApiHost("http://127.0.0.1")
                .configure();
        System.out.println("初始化完毕！");
    }
}
