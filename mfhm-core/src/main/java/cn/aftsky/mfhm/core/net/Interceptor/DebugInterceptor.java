package cn.aftsky.mfhm.core.net.Interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import java.io.IOException;

import cn.aftsky.mfhm.core.util.FileUtil;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by MaoHonglu on 2018/5/24.
 */

/**
 * 仅供测试使用!拦截Http请求，直接返回JSON数据供测试
 */
public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;//模拟发送请求的URL
    private final int DEBUG_RAW_ID;//RESPONSE返回JSON文件的ID

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")//SON格式
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    /**
     * 生成Json格式的Response响应
     * @param chain 请求链
     * @param rawId 对应的Json文件格式ID
     * @return  Response响应
     */
    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    /**
     * 如果请求链里面的url包含DEBUG_URL，则绑定一个JSON数据，否则原样返回
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
