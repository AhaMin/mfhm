package cn.aftsky.mfhm.core.net.callback;


import android.os.Handler;

import cn.aftsky.mfhm.core.constants.LoaderStyle;
import cn.aftsky.mfhm.core.ui.MFHMLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RequestCallBack implements Callback<String> {

    private final IResponseListener RESPONSELISTENER;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallBack(IResponseListener iResponseListener, LoaderStyle style) {
        this.RESPONSELISTENER=iResponseListener;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            System.out.print("请求响应成功");
            if (call.isExecuted()) {
                System.out.print("开始回调onSuccess");
                    RESPONSELISTENER.onSuccess(response.body());
            }
        } else {
            RESPONSELISTENER.onError(response.code(), response.message());
        }

        if (LOADER_STYLE != null) {
            stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        RESPONSELISTENER.onFailure();
        RESPONSELISTENER.onRequestEnd();

        if (LOADER_STYLE != null) {
            stopLoading();
        }
    }

    //延迟3s钟Loader消失
    private static void stopLoading() {
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                MFHMLoader.stopLoading();
            }
        }, 3000);
    }
}
