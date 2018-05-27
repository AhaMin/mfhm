package cn.aftsky.mfhm.core.net.Interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by MaoHonglu on 2018/5/24.
 */

/**
 * 仅供测试使用!
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取get请求当中Url当中的参数
     * @param chain 请求链
     * @return url参数的集合
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * 获取get请求当中指定key的value
     * @param chain 请求链
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 获取post请求当中Url当中的参数
     * @param chain 请求链
     * @return post请求体参数集合
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = 0;
        if (formBody != null) {
            size = formBody.size();
        }
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * 获取post请求当中指定key的value
     * @param chain 请求链
     * @param key
     * @return
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}