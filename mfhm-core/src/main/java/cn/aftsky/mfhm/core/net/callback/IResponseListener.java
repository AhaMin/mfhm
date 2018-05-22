package cn.aftsky.mfhm.core.net.callback;

/**
 * Created by MaoHonglu on 2018/5/22.
 */

public interface IResponseListener {
    void onRequestStart();
    void onSuccess(String response);
    void onError(int code,String msg);
    void onFailure();
    void onRequestEnd();
}
