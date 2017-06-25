package com.ldealmei.libs.carousel.builder;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldealmei.libs.carousel.R;
import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.libs.carousel.view.CarouselView;

/**
 * Created by Lucas Cruz on 24/06/2017.
 */

public class CarouselBuilder extends CarouselView {


    public CarouselBuilder(Context context) {
        super(context);
    }

    public CarouselBuilder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected void build(){
        int x = 0;
        for (final ItemPicker it : itens) {
            final int index = x;

            LayoutInflater inflater = LayoutInflater.from(context);
            final LinearLayout mainLayout = new LinearLayout(getContext());
            mainLayout.setLayoutParams(calculateLayoutSize());
            inflater.inflate(R.layout.item, mainLayout);

            LinearLayout itemLayout = (LinearLayout) mainLayout.findViewById(R.id.item_layout);
            ImageView itemImage = (ImageView) mainLayout.findViewById(R.id.img_item);
            TextView itemText = (TextView) mainLayout.findViewById(R.id.txt_item);
            ImageView indicatorImage = (ImageView) mainLayout.findViewById(R.id.img_indicator);

            if(it.imgUrl != null) {
                Glide.with(context)
                     .load(Uri.parse(it.imgUrl))
                     .into(itemImage);
            } else {
                itemImage.setImageResource(it.imgResID);
            }

            if(it.hasDescription) {
                if (customDescriptionColor != 0) {
                    itemText.setTextColor(ContextCompat.getColor(context, customDescriptionColor));
                }
                if (it.txt == null) {
                    itemText.setText(it.txtResID);
                } else {
                    itemText.setText(it.txt);
                }
            } else {
                itemText.setVisibility(GONE);
            }

            if(displayIndicator) {
                if(customIndicatorSize != 0){
                    indicatorImage.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,(int)context.getResources().getDimension(customIndicatorSize), 0.5f));
                }
                if(customIndicator != 0) {
                    indicatorImage.setImageResource(customIndicator);
                } else {
                    indicatorImage.setImageResource(R.drawable.ic_indicator);
                }
                if(customIndicatorColor != 0) {
                    indicatorImage.setColorFilter(ContextCompat.getColor(context, customIndicatorColor));
                }
                if(x == 0){
                    indicatorSelected = indicatorImage;
                } else {
                    indicatorImage.setAlpha(0f);
                }
                indicatorsMap.put(x, indicatorImage);
            } else {
                indicatorImage.setVisibility(GONE);
            }
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(displayIndicator) {
                        if (indicatorSelected != null) {
                            indicatorSelected.setAlpha(0f);
                        }
                        indicatorSelected = indicatorsMap.get(index);
                        indicatorSelected.setAlpha(1f);
                    }

                    int scroll = (mainLayout.getLeft() - (getWidth()/ 2)) + (mainLayout.getWidth() / 2);
                    pickerScroll.smoothScrollTo(scroll,0);

                    selectedItem = it;

                    if(callback != null) {
                        callback.onClickItem(it);
                    }
                }
            });

            pickerLayout.addView(mainLayout);
            x++;
        }
    }
}
