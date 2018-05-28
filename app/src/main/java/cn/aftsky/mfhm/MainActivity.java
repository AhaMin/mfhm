package cn.aftsky.mfhm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import cn.aftsky.mfhm.core.activities.ProxyActivity;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.main.launcher.LauncherDelegate;
import cn.aftsky.mfhm.main.sign.SignupDelegate;

public class MainActivity extends ProxyActivity {
    //测试delegate
//    @Override
//    public MFHMDelegate setRootDelegate() {
//        return new ExampleDelegate();
//    }

    //  启动页delegate
//    @Override
//    public MFHMDelegate setRootDelegate() {
//        return new LauncherDelegate();
//    }

    //注册页delegate
    @Override
    public MFHMDelegate setRootDelegate() {
        return new SignupDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MFHM.init(this)
//                .withIcon(new FontAwesomeModule())
//                .withIcon(new FontMFHMModule())
//                .withApiHost("http://127.0.0.1")
//                .configure();
//        System.out.println("初始化完毕！");
//        setContentView(R.layout.activity_main);
//
//        Toast.makeText(MFHM.getApplicationContext(),"启动Context",Toast.LENGTH_LONG).show();
//    }

}
