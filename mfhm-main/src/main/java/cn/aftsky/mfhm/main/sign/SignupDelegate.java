package cn.aftsky.mfhm.main.sign;

import android.app.Activity;
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
 * 注册页面
 * Created by MaoHonglu on 2018/5/26.
 */

public class SignupDelegate extends MFHMDelegate {

    //实例化表单控件
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    //用户登录注册回调监听
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    //注册表单内容验证
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://www.baidu.com")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .responseListener(new ResponseListenerAdapter(){
                        @Override
                        public void onSuccess(String response) {
                            super.onSuccess(response);
                            //具体的处理逻辑
                            System.out.println("用户注册按钮Http响应成功！");
                            MyLogger.json("用户注册", response);
                            SignHandler.onSignUp(response, mISignListener);
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
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(),"注册表单通过",Toast.LENGTH_LONG).show();
        }
    }

    //跳转到登录页面
    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SigninDelegate());
    }

    //表单验证
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;


        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
