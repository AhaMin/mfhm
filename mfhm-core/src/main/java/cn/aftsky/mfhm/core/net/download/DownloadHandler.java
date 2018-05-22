package cn.aftsky.mfhm.core.net.download;

import android.os.AsyncTask;

import java.util.Map;
import java.util.WeakHashMap;

import cn.aftsky.mfhm.core.net.RestCreater;
import cn.aftsky.mfhm.core.net.callback.IResponseListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaoHonglu on 2018/5/21.
 */

public class DownloadHandler {
    private final String URL;
    private static final Map<String, Object> PARAMS = new WeakHashMap<>();
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IResponseListener RESPONSELISTENER;

    public DownloadHandler(String url, String download_dir, String extension, String name, IResponseListener responseListener) {
        this.URL = url;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.RESPONSELISTENER=responseListener;
    }

    public final void handlerDownload(){
        RESPONSELISTENER.onRequestStart();
        RestCreater
                .getRestServer()
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(RESPONSELISTENER);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                    RESPONSELISTENER.onRequestEnd();
                            }
                        } else {
                                RESPONSELISTENER.onError(response.code(), response.message());
                        }
                        PARAMS.clear();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            RESPONSELISTENER.onFailure();
                            PARAMS.clear();
                    }
                });
    }
}
