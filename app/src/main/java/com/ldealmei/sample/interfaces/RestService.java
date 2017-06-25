package com.ldealmei.sample.interfaces;

import com.ldealmei.libs.carousel.model.ItemPicker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public interface RestService {

    @GET("itens.json")
    Call<List<ItemPicker>> getItens();
}
