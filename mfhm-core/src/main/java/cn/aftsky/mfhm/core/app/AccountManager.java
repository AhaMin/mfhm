package cn.aftsky.mfhm.core.app;

/**
 * Created by MaoHonglu on 2018/5/28.
 */

import cn.aftsky.mfhm.core.util.PreferenceUtil;

/**
 * 用户账户管理工具类
 * 主要判断用户的登录状态
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        PreferenceUtil.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return PreferenceUtil.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) { //如果用户登录过了
            checker.onSignIn();
        } else {    //如果用户没有登录过
            checker.onNotSignIn();
        }
    }
}
