package com.ldealmei.sample.business;

import android.content.Context;

import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.infrastructure.PickersUtil;
import com.ldealmei.sample.interfaces.CallbackResponse;
import com.ldealmei.sample.provider.CarouselLocalProvider;
import com.ldealmei.sample.provider.CarouselRestProvider;

import java.util.List;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public class CarouselBusiness {

    private CarouselRestProvider restProvider;
    private CarouselLocalProvider localProvider;
    private Context context;

    public CarouselBusiness() {
        localProvider = new CarouselLocalProvider();
    }

    public CarouselBusiness(Context context) {
        restProvider = new CarouselRestProvider();
        localProvider = new CarouselLocalProvider();
        this.context = context;
    }

    public void getItens(final CallbackResponse<List<ItemPicker>> callback){
        if(PickersUtil.isNetworkAvailable(context)){
            restProvider.getItens(callback);
        } else {
            localProvider.getItens(callback);
        }
    }
}
