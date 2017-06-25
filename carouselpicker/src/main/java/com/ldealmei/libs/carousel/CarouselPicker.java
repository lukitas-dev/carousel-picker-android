package com.ldealmei.libs.carousel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ldealmei.libs.carousel.builder.CarouselBuilder;
import com.ldealmei.libs.carousel.interfaces.PickerListerner;
import com.ldealmei.libs.carousel.model.ItemPicker;

import java.util.List;

/**
 * Created by Lucas Cruz on 27/05/2017.
 */

public class CarouselPicker extends CarouselBuilder {

    public CarouselPicker(Context context) {
        super(context);
    }

    public CarouselPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public CarouselPicker init(@NonNull Context context){
        this.context = context;
        return this;
    }

    public CarouselPicker itensByPage(@NonNull int number){
        this.numberOfItensPerPage = number;
        return this;
    }

    public CarouselPicker displayIndicator(@NonNull boolean display){
        this.displayIndicator = display;
        return this;
    }

    public CarouselPicker customIndicator(@NonNull int indicatorRes){
        this.customIndicator = indicatorRes;
        return this;
    }
    public CarouselPicker customIndicatorColor(@NonNull int colorRes){
        this.customIndicatorColor = colorRes;
        return this;
    }

    public CarouselPicker customDescriptionColor(@NonNull int colorRes){
        this.customDescriptionColor = colorRes;
        return this;
    }

    public CarouselPicker customIndicatorSize(@NonNull int sizeRes){
        this.customIndicatorSize = sizeRes;
        return this;
    }

    public CarouselPicker addList(@NonNull List<ItemPicker> itens){
       this.itens = itens;
        return this;
    }

    public CarouselPicker addCallback(@NonNull PickerListerner callback){
        this.callback = callback;
        return this;
    }


    public void build(){
        super.build();
    }


}
