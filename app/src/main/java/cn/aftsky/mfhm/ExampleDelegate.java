package cn.aftsky.mfhm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aftsky.mfhm.main.delegates.MFHMDelegate;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class ExampleDelegate extends MFHMDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
