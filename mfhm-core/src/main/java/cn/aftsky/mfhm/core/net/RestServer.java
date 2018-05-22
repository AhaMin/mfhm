package cn.aftsky.mfhm.core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

/**
 * 用Retrofit 写的Restful网络请求接口
 * 对于Android来说，Call回调方法会执行在主线程
 * 用法和OkHttp一样
 */
public interface RestServer {

    //QueryMap中的数据用于URL
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    //FormUrlEncoded表示请求体是一个Form表单
    //FieldMap中的数据用于表单字段，Map的key作为表单的键
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    //POST原始数据
    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody requestBody);


    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody requestBody);

    @DELETE
    Call<String> delete(@Url String url,@QueryMap Map<String, Object> params);

    //Streaming表示响应体的数据用流的形式返回
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url,@QueryMap Map<String,Object> params);

    //Multipart表示请求体是一个支持文件上传的Form表单
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
