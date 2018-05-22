package cn.aftsky.mfhm.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.aftsky.mfhm.core.constants.LoaderStyle;
import cn.aftsky.mfhm.core.net.callback.IResponseListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by MaoHonglu on 2018/5/13.
 * 建造者模式构建RestClient
 */

public class RestClientBuilder {
    private String mUrl=null;
    private Map<String ,Object> PARAMS=new WeakHashMap<>();
    private IResponseListener mResponseListener;
    private RequestBody mBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;
    private File mFile=null;
    private String mDownloader = null;
    private String mExtension=null;
    private String mName=null;

    public RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RestClientBuilder responseListener(IResponseListener responseListener) {
        this.mResponseListener=responseListener;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }
    public final RestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }
    public final RestClientBuilder file(String file){
        this.mFile=new File(file);
        return this;
    }
    public final RestClientBuilder name(String name){
        this.mName = name;
        return this;
    }
    public final RestClientBuilder dir(String dir){
        this.mDownloader=dir;
        return this;
    }
    public final RestClientBuilder extension(String extension){
        this.mExtension = extension;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mResponseListener,mBody,mLoaderStyle,mFile,mDownloader,mExtension,mName,mContext);
    }
}
