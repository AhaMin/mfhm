package cn.aftsky.mfhm.main.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.core.net.RestClient;
import cn.aftsky.mfhm.core.net.callback.ResponseListenerAdapter;
import cn.aftsky.mfhm.core.util.MyLogger;
import cn.aftsky.mfhm.main.R;
import cn.aftsky.mfhm.main.R2;

/**
 * 登录页面
 * Created by MaoHonglu on 2018/5/28.
 */

public class SigninDelegate extends MFHMDelegate {

    //绑定视图
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    //用户登录注册回调监听
    private ISignListener mISignListener = null;

    //跳转到注册页面按钮
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignupDelegate());//startWithPop()
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    //表单填写内容验证
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    //登录按钮事件绑定
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {

         /*
          有302重定向错误，暂时
            RestClient restClient = RestClient.builder()
                    .url("http://www.baidu.com")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .loader(getContext())
                    .responseListener(new ResponseListenerAdapter(){
                        @Override
                        public void onSuccess(String response) {
                            super.onSuccess(response);
                            //具体的处理逻辑
                            System.out.println("用户登录按钮Http响应成功！");
                            MyLogger.json("用户登录", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }

                        @Override
                        public void onRequestStart() {
                            super.onRequestStart();
                            System.out.println("请求开始！");
                        }

                        @Override
                        public void onError(int code, String msg) {
                            super.onError(code, msg);
                            System.out.println("请求错误！"+code+":"+msg); //302重定向错误
                        }

                        @Override
                        public void onFailure() {
                            super.onFailure();
                            System.out.println("请求失败！");
                        }

                        @Override
                        public void onRequestEnd() {
                            super.onRequestEnd();
                            System.out.println("请求结束！");
                        }
                    })
                    .build();
                restClient.post();

                */

         RestClient restClient = RestClient.builder()
                    .url("http://127.0.0.1/index")
                    .loader(getContext())
                    .responseListener(new ResponseListenerAdapter(){
                        @Override
                        public void onSuccess(String response) {
                            super.onSuccess(response);
                            //具体的处理逻辑
                            System.out.println("用户登录按钮Http响应成功！");
                            MyLogger.json("用户登录", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }

                        @Override
                        public void onRequestStart() {
                            super.onRequestStart();
                            System.out.println("请求开始！");
                        }

                        @Override
                        public void onError(int code, String msg) {
                            super.onError(code, msg);
                            System.out.println("请求错误！"+code+":"+msg);
                        }

                        @Override
                        public void onFailure() {
                            super.onFailure();
                            System.out.println("请求失败！");
                        }

                        @Override
                        public void onRequestEnd() {
                            super.onRequestEnd();
                            System.out.println("请求结束！");
                        }
                    }).build();
            restClient.get(); //调用get方法执行

            Toast.makeText(getContext(),"登录表单通过",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}
