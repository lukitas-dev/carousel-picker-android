package com.ldealmei.sample.provider;

import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.R;
import com.ldealmei.sample.interfaces.CallbackResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas Cruz on 25/06/2017.
 */

public class CarouselLocalProvider {


    public void getItens(final CallbackResponse<List<ItemPicker>> callback){
        List<ItemPicker> itens = createItens();
        callback.onLocalResponse(itens);
    }

    public  List<ItemPicker> createItens(){
        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.ic_banana,"Banana"));
        itens.add(new ItemPicker(R.drawable.ic_grapes,"Grapes"));
        itens.add(new ItemPicker(R.drawable.ic_orange,"Orange"));
        itens.add(new ItemPicker(R.drawable.ic_pineapple,"Pineapple"));
        itens.add(new ItemPicker(R.drawable.ic_raspberry,"Raspberry"));
        itens.add(new ItemPicker(R.drawable.ic_strawberry,"Strawberry"));
        itens.add(new ItemPicker(R.drawable.ic_watermelon,"Watermelon"));
        return itens;
    }
}
