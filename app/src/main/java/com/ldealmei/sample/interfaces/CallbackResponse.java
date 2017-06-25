package com.ldealmei.sample.interfaces;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public interface CallbackResponse<T> {

    void onRestResponse(T response);

    void onLocalResponse(T response);

    void onRestFailure(T response, Throwable t);

    void onFailure(String msg, Throwable t);

}
