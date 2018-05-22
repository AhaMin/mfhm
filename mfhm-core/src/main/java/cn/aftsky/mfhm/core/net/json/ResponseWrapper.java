package cn.aftsky.mfhm.core.net.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON实体类
 * Created by MaoHonglu on 2018/5/22.
 */

public class ResponseWrapper {

    private final boolean success;

    private final String errMessage;

    private final Map<String, Object> data;

    @JsonCreator
    public ResponseWrapper(@JsonProperty("success") boolean success,
                           @JsonProperty("errMessage") String errMessage,
                           @JsonProperty("data") Map<String, Object> data) {
        this.success = success;
        this.errMessage = errMessage;
        this.data = data;
    }

    public ResponseWrapper(String errMessage) {
        this.success = false;
        this.errMessage = errMessage;
        this.data = new HashMap<>();
    }

    public <T> ResponseWrapper addObject(T value, String key) {
        if (key != null) {
            data.put(key, value);
        }
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public Map<String, Object> getData() {
        return data;
    }
}

