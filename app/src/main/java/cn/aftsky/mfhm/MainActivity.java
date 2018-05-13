package cn.aftsky.mfhm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.aftsky.mfhm.core.Configurator;
import cn.aftsky.mfhm.core.MFHM;
import cn.aftsky.mfhm.main.activities.ProxyActivity;
import cn.aftsky.mfhm.main.delegates.MFHMDelegate;
import cn.aftsky.mfhm.main.icon.FontMFHMModule;

public class MainActivity extends ProxyActivity {

    @Override
    public MFHMDelegate setRootDelegate() {
        return new ExampleDelegate();
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
