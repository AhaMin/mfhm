package cn.aftsky.mfhm.main.sign;

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
import cn.aftsky.mfhm.main.R;
import cn.aftsky.mfhm.main.R2;

/**
 * Created by MaoHonglu on 2018/5/28.
 */

public class SigninDelegate extends MFHMDelegate {

    //绑定视图
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignupDelegate());//startWitjPop()
    }

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

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
//            RestClient.builder()
//                    .url("")
//                    .params("email", mEmail.getText().toString())
//                    .params("password", mPassword.getText().toString())
//                    .responseListener(new ResponseListenerAdapter(){
//                        @Override
//                        public void onSuccess(String response) {
//                            super.onSuccess(response);
//                            //具体的处理逻辑
//                        }
//                    })
//                    .build()
//                    .post();
            Toast.makeText(getContext(),"登录表单通过",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
