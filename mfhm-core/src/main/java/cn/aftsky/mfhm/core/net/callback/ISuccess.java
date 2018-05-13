package cn.aftsky.mfhm.core.net.callback;

import okhttp3.ResponseBody;

/**
 * Created by MaoHonglu on 2018/5/13.
 * 定义请求成功回调接口
 */

public interface ISuccess {
    public void onSuccess(String response);
}
