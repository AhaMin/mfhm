package cn.aftsky.mfhm.core.net.callback;

/**
 * Created by MaoHonglu on 2018/5/13.
 * 定义请求错误回调接口
 */

public interface IError {
    public void onError(int code,String msg);
}
