package cn.aftsky.mfhm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import cn.aftsky.mfhm.core.activities.ProxyActivity;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.main.launcher.ILauncherListener;
import cn.aftsky.mfhm.main.launcher.LauncherDelegate;
import cn.aftsky.mfhm.main.launcher.OnLauncherFinishTag;
import cn.aftsky.mfhm.main.sign.ISignListener;
import cn.aftsky.mfhm.main.sign.SigninDelegate;
import cn.aftsky.mfhm.main.sign.SignupDelegate;

public class MainActivity extends ProxyActivity implements ISignListener ,ILauncherListener{

    //测试delegate
//    @Override
//    public MFHMDelegate setRootDelegate() {
//        return new ExampleDelegate();
//    }

    //  启动页delegate
    @Override
    public MFHMDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    //注册页delegate
//    @Override
//    public MFHMDelegate setRootDelegate() {
//        return new SignupDelegate();
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new ExampleDelegate());//用户登录了的话跳转到主页面
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SigninDelegate());//用户没登录的话跳转到登录页面
                break;
            default:
                break;
        }
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
