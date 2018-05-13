package cn.aftsky.mfhm.core.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import cn.aftsky.mfhm.core.R;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by MaoHonglu on 2018/5/12.
 */

public abstract class ProxyActivity extends SupportActivity{
    public abstract MFHMDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState ){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState ==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());//fragmentation提供的方法，加载根Fragment
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
