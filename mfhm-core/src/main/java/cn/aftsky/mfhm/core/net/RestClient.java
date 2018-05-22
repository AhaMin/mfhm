package cn.aftsky.mfhm.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.aftsky.mfhm.core.constants.HttpMethod;
import cn.aftsky.mfhm.core.net.callback.IResponseListener;
import cn.aftsky.mfhm.core.net.callback.RequestCallBack;
import cn.aftsky.mfhm.core.net.download.DownloadHandler;
import cn.aftsky.mfhm.core.constants.LoaderStyle;
import cn.aftsky.mfhm.core.ui.MFHMLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RestClient {
    private final String URL;
    private final Map<String, Object> PARAMS =new WeakHashMap<>();
    private final IResponseListener RESPONSELISTENER;
    private final RequestBody MBODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final Context CONTEXT;

    public RestClient(String url, Map<String, Object> params,IResponseListener iResponseListener, RequestBody mbody, LoaderStyle loaderStyle, File file, String downloader_dir, String extension, String name, Context context) {
        this.URL = url;
        PARAMS.putAll(PARAMS);
        this.RESPONSELISTENER=iResponseListener;
        this.MBODY = mbody;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloader_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(RESPONSELISTENER, LOADER_STYLE);
    }

    /**
     * 具体的使用方法
     */

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (MBODY == null)
            request(HttpMethod.POST);
        else if (!PARAMS.isEmpty()) {
            throw new RuntimeException("params must be null");
        } else
            request(HttpMethod.POST_RAW);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void put() {
        if (MBODY == null)
            request(HttpMethod.PUT);
        else if (!PARAMS.isEmpty()) {
            throw new RuntimeException("params must be null");
        } else
            request(HttpMethod.PUT_RAW);
    }

    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    public final void download(){
        new DownloadHandler(URL,DOWNLOAD_DIR,EXTENSION,NAME,RESPONSELISTENER).handlerDownload();
    }
    //RestClient发送HTTP请求
    private void request(HttpMethod method) {
        final RestServer server = RestCreater.getRestServer();

        //返回的call对象
        Call<String> call = null;

        //在RequestStart之前做一些初始化工作
        RESPONSELISTENER.onRequestStart();

        if (LOADER_STYLE != null) {
            MFHMLoader.showLoading(CONTEXT, LOADER_STYLE.name());
        }

        //开始执行请求
        switch (method) {
            case GET:
                call = server.get(URL, PARAMS);
                break;
            case POST:
                call = server.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = server.postRaw(URL, MBODY);
                break;
            case PUT:
                call = server.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = server.putRaw(URL, MBODY);
                break;
            case DELETE:
                call = server.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreater.getRestServer().upload(URL, body);
                break;
            default:
                break;
        }

        //确认返回call对象
        if (call != null) {
//            call.execute();这个call会在主线程当中执行，也就是Ui线程执行
            call.enqueue(getRequestCallBack());//这个会在新启的一个线程当中执行，推荐使用
        }
    }
}
