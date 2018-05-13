package cn.aftsky.mfhm.core.net.callback;

/**
 * Created by MaoHonglu on 2018/5/13.
 *  定义请求回调接口
 */

public interface IRequest {
    public void onRequestStart();
    public void onRequestEnd();
}
