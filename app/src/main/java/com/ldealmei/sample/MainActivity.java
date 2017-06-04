package com.ldealmei.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.ItemPicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imgSelected;
    private TextView txtSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSelected = (ImageView) findViewById(R.id.img_selected);
        txtSelected = (TextView) findViewById(R.id.txt_selected);

        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.ic_banana,"Banana"));
        itens.add(new ItemPicker(R.drawable.ic_grapes,"Grapes"));
        itens.add(new ItemPicker(R.drawable.ic_orange,"Orange"));
        itens.add(new ItemPicker(R.drawable.ic_pineapple,"Pineapple"));
        itens.add(new ItemPicker(R.drawable.ic_raspberry,"Raspberry"));
        itens.add(new ItemPicker(R.drawable.ic_strawberry,"Strawberry"));
        itens.add(new ItemPicker(R.drawable.ic_watermelon,"Watermelon"));

        final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

        carouselPicker.addList(itens).build(this);


        imgSelected.setImageResource(itens.get(0).imgResID);
        txtSelected.setText(itens.get(0).txt);


        carouselPicker.addListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPicker item = carouselPicker.getSelectedItem();
                if(item != null){
                    imgSelected.setImageResource(item.imgResID);
                    if(item.txtResID != 0) {
                        txtSelected.setText(item.txtResID);
                    } else {
                        txtSelected.setText(item.txt);
                    }
                }
            }
        });

    }

}
