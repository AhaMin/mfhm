package cn.aftsky.mfhm.core.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import cn.aftsky.mfhm.core.constants.ConfigType;
import cn.aftsky.mfhm.core.app.MFHM;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RestCreater {

    /**
     * 获取RestServer的代理对象
     */
    public static RestServer getRestServer(){
        return RestServerHolder.REST_SERVER;
    }

    /**
     * 单例模式创建Retrofit实例
     */
    private static final class RetrofitHolder{
        //获取API_HOST
        private static final String BASE_URL=(String) MFHM.getConfigurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT).addConverterFactory(ScalarsConverterFactory.create()).build();

    }

    /**
     * 单例模式创建OkHttp实例
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = MFHM.getConfiguration(ConfigType.INTERCEPTOR); //获取OkHttp拦截器
//        private static final ArrayList<Interceptor> INTERCEPTORS = null;


        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 用Retrofit创建一个RestServer的代理对象
     */
    private static final class RestServerHolder{
        private static final RestServer REST_SERVER=RetrofitHolder.RETROFIT_CLIENT.create(RestServer.class);
    }
}
