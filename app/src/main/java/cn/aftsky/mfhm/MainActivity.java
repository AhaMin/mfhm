package cn.aftsky.mfhm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.aftsky.mfhm.core.Configurator;
import cn.aftsky.mfhm.core.MFHM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       MFHM.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
