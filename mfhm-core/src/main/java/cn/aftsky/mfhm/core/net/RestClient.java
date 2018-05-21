package cn.aftsky.mfhm.core.net;

import android.content.Context;

import java.util.Map;

import cn.aftsky.mfhm.core.net.callback.IError;
import cn.aftsky.mfhm.core.net.callback.IFailure;
import cn.aftsky.mfhm.core.net.callback.IRequest;
import cn.aftsky.mfhm.core.net.callback.ISuccess;
import cn.aftsky.mfhm.core.net.callback.RequestCallBack;
import cn.aftsky.mfhm.core.ui.LoaderStyle;
import cn.aftsky.mfhm.core.ui.MFHMLoader;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RestClient {
    private final String URL;
    private static final Map<String ,Object> PARAMS=RestCreater.getParams();
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody MBODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url, Map<String, Object> params, ISuccess success, IRequest request, IError error, IFailure failure,RequestBody mbody,Context context,LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(PARAMS);
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.MBODY=mbody;
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    //RestClient发送HTTP请求
    private void request(HttpMethod method){
        final RestServer server = RestCreater.getRestServer();
        Call<String> call = null;

        //确认请求不为空，并且在RequestStart之前做一些初始化工作
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            MFHMLoader.showLoading(CONTEXT,LOADER_STYLE.name());
        }

        //开始执行请求
        switch (method){
            case GET:
                call = server.get(URL,PARAMS);
                break;
            case POST:
                call=server.post(URL,PARAMS);
                break;
            case PUT:
                call=server.put(URL,PARAMS);
                break;
            case DELETE:
                call=server.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        //确认返回call对象
        if(call!=null){
//            call.execute();这个call会在主线程当中执行，也就是Ui线程执行
              call.enqueue(getRequestCallBack());//这个会在新启的一个线程当中执行，推荐使用
        }
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallBack(SUCCESS,REQUEST,ERROR,FAILURE,LOADER_STYLE);
    }

    /**
     *     具体的使用方法
     */

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.GET);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
}
