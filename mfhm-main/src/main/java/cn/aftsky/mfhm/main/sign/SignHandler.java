package cn.aftsky.mfhm.main.sign;

import org.json.JSONObject;

import cn.aftsky.mfhm.core.app.AccountManager;

/**
 * 登录注册处理类
 * Created by MaoHonglu on 2018/5/28.
 */

public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {

//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        System.out.println("成功设置登录成功标志");
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        System.out.println("成功设置注册并登录成功标志");
        signListener.onSignUpSuccess();
    }
}
