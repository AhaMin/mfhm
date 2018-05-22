package cn.aftsky.mfhm.core.net.download;

import android.os.AsyncTask;

import java.util.Map;

import cn.aftsky.mfhm.core.net.RestCreater;
import cn.aftsky.mfhm.core.net.callback.IError;
import cn.aftsky.mfhm.core.net.callback.IFailure;
import cn.aftsky.mfhm.core.net.callback.IRequest;
import cn.aftsky.mfhm.core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaoHonglu on 2018/5/21.
 */

public class DownloadHandler {
    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreater.getParams();
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHandler(String url, String download_dir, String extension, String name, IRequest request, ISuccess success, IError error, IFailure failure) {
        this.URL = url;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    public final void handlerDownload(){
        if(REQUEST != null){
            REQUEST.onRequestStart();
        }
        RestCreater
                .getRestServer()
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                        RestCreater.getParams().clear();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                            RestCreater.getParams().clear();
                        }
                    }
                });
    }
}
