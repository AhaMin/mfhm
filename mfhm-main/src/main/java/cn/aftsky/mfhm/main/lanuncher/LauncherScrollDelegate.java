package cn.aftsky.mfhm.main.lanuncher;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aftsky.mfhm.core.app.MFHM;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.core.util.PreferenceUtil;
import cn.aftsky.mfhm.main.R;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by MaoHonglu on 2018/5/27.
 */

public class LauncherScrollDelegate extends MFHMDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.lannuncher_01);
        INTEGERS.add(R.mipmap.lannuncher_02);
        INTEGERS.add(R.mipmap.lannuncher_03);
        INTEGERS.add(R.mipmap.lannuncher_04);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
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
//        if (position == INTEGERS.size() - 1) {
//            PreferenceUtil.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
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
//        }
    }
}
