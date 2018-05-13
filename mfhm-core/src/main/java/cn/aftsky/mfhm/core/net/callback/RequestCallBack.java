package cn.aftsky.mfhm.core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaoHonglu on 2018/5/13.
 */

public class RequestCallBack implements Callback<String> {

    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RequestCallBack(ISuccess success, IRequest request, IError error, IFailure failure) {
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
