package cn.aftsky.mfhm.core.net.callback;


import android.os.Handler;

import cn.aftsky.mfhm.core.ui.LoaderStyle;
import cn.aftsky.mfhm.core.ui.MFHMLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RequestCallBack implements Callback<String> {

    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallBack(ISuccess success, IRequest request, IError error, IFailure failure, LoaderStyle style) {
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
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
