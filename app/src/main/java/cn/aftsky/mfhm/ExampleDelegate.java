package cn.aftsky.mfhm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.aftsky.mfhm.core.R2;
import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.core.net.RestClient;
import cn.aftsky.mfhm.core.net.callback.IResponseListener;
import cn.aftsky.mfhm.core.net.callback.ResponseListenerAdapter;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class ExampleDelegate extends MFHMDelegate {

    Button btnReg;
    Button btnLogIn;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        btnReg=(Button) rootView.findViewById(R.id.btn_reg);
        btnLogIn=(Button)rootView.findViewById(R.id.btn_login);

        System.out.println("视图绑定完毕");
        testRestClientBuilder();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReg.setText("被点击了...");
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogIn.setText("被点击了...");
            }
        });
    }

    /**
     * 测试建造者模式建造RestClient
     */
    private void testRestClientBuilder() {
        RestClient restClient = RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .responseListener(new ResponseListenerAdapter(){
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        Toast.makeText(getContext(),response, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure() {
                        super.onFailure();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        super.onError(code, msg);
                    }
                }).build();
        restClient.get(); //调用get方法执行
    }

    private void testRestClientBuilder2() {
        RestClient restClient = RestClient.builder()
                .url("http://www.baidu.com/")
                .params("username","kk")
                .params("password","00")
                .loader(getContext())
                .responseListener(new ResponseListenerAdapter(){
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        Toast.makeText(getContext(),response, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure() {
                        super.onFailure();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        super.onError(code, msg);
                    }
                }).build();
        restClient.get(); //调用get方法执行
    }
}
