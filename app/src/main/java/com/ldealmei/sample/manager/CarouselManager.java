package com.ldealmei.sample.manager;

import android.content.Context;

import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.business.CarouselBusiness;
import com.ldealmei.sample.interfaces.CallbackResponse;

import java.util.List;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public class CarouselManager {

    private CarouselBusiness business;

    public CarouselManager(Context context){
        business = new CarouselBusiness(context);
    }

    public void getItens(final CallbackResponse<List<ItemPicker>> callback){
        business.getItens(callback);
    }


}
