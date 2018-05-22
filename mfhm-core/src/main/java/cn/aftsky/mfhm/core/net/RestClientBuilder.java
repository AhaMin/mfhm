package cn.aftsky.mfhm.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.aftsky.mfhm.core.net.callback.IError;
import cn.aftsky.mfhm.core.net.callback.IFailure;
import cn.aftsky.mfhm.core.net.callback.IRequest;
import cn.aftsky.mfhm.core.net.callback.ISuccess;
import cn.aftsky.mfhm.core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by MaoHonglu on 2018/5/13.
 * 建造者模式构建RestClient
 */

public class RestClientBuilder {
    private String mUrl=null;
    private static final Map<String ,Object> PARAMS=RestCreater.getParams();
    private ISuccess mISuccess=null;
    private IRequest mIRequest=null;
    private IError mIError=null;
    private IFailure mIFailure=null;
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

    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
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
        return new RestClient(mUrl,PARAMS,mISuccess,mIRequest,mIError,mIFailure,mBody,mLoaderStyle,mFile,mDownloader,mExtension,mName,mContext);
    }
}
