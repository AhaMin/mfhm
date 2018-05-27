package cn.aftsky.mfhm;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.aftsky.mfhm.core.app.MFHM;
import cn.aftsky.mfhm.core.net.Interceptor.DebugInterceptor;
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
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        System.out.println("初始化完毕！");
    }
}
