package cn.aftsky.mfhm.main.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.core.util.PreferenceUtil;
import cn.aftsky.mfhm.main.R;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * 首次使用APP，轮播图
 * Created by MaoHonglu on 2018/5/27.
 */

public class LauncherScrollDelegate extends MFHMDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;//页面翻转控件
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();//存放图片的数组
    private ILauncherListener mILauncherListener = null;

    /*初始化轮播图控件*/
    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS) //传入creator和datas
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        INTEGERS.clear();//当界面不可见时把下面的小圆点清空，防止被多次创建出来
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            System.out.println("执行了onItemClick这个方法！");
//            System.out.println("PreferenceUtil.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name());"+PreferenceUtil.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()));默认为false
            PreferenceUtil.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
//            //检查用户是否已经登录
//            AccountManager.checkAccount(new IUserChecker() {
//                @Override
//                public void onSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
//                    }
//                }
//
//                @Override
//                public void onNotSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
//                    }
//                }
//            });
        }
    }
}
