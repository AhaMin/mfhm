package cn.aftsky.mfhm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import cn.aftsky.mfhm.core.delegates.MFHMDelegate;
import cn.aftsky.mfhm.core.net.RestClient;
import cn.aftsky.mfhm.core.net.RestClientBuilder;
import cn.aftsky.mfhm.core.net.callback.IError;
import cn.aftsky.mfhm.core.net.callback.IFailure;
import cn.aftsky.mfhm.core.net.callback.ISuccess;

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
        System.out.println("视图绑定完毕");
        testRestClientBuilder();
    }

    /**
     * 测试建造者模式建造RestClient
     */
    private void testRestClientBuilder() {
        RestClient restClient = RestClient.builder()
                .url("http://www.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

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
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build();
        restClient.get(); //调用get方法执行
    }
}
