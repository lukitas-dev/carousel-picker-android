package com.ldealmei.sample.provider;

import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.infrastructure.RetrofitProvider;
import com.ldealmei.sample.interfaces.CallbackResponse;
import com.ldealmei.sample.interfaces.RestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public class CarouselRestProvider extends RetrofitProvider {

    private RestService service;

    public CarouselRestProvider() {
        super("http://192.168.25.103:9090/");
        service = retrofit.create(RestService.class);
    }

    public void getItens(final CallbackResponse<List<ItemPicker>> callback){
        service.getItens().enqueue(new Callback<List<ItemPicker>>() {
            @Override
            public void onResponse(Call<List<ItemPicker>> call, Response<List<ItemPicker>> response) {
                if(response.isSuccessful()) {
                    callback.onRestResponse(response.body());
                } else {
                    callback.onFailure(response.message(),null);
                }
            }

            @Override
            public void onFailure(Call<List<ItemPicker>> call, Throwable t) {
                callback.onFailure(t.getMessage(),t);
                callback.onRestFailure(new CarouselLocalProvider().createItens(),t);
            }
        });
    }


}
