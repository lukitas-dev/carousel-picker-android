 package com.ldealmei.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.ItemPicker;

import java.util.ArrayList;
import java.util.List;

 public class PickerImageWithIndicatorActivity extends AppCompatActivity {

     private ImageView imgSelected;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_item_with_indicator);
        imgSelected = (ImageView) findViewById(R.id.img_selected);

         getSupportActionBar().setTitle("Image Indicator Picker");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.ic_banana));
        itens.add(new ItemPicker(R.drawable.ic_grapes));
        itens.add(new ItemPicker(R.drawable.ic_orange));
        itens.add(new ItemPicker(R.drawable.ic_pineapple));
        itens.add(new ItemPicker(R.drawable.ic_raspberry));
        itens.add(new ItemPicker(R.drawable.ic_strawberry));
        itens.add(new ItemPicker(R.drawable.ic_watermelon));

        final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

        carouselPicker
                .displayIndicator(true)
                .addList(itens)
                .build(this);


        imgSelected.setImageResource(itens.get(0).imgResID);


        carouselPicker.addListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPicker item = carouselPicker.getSelectedItem();
                if(item != null){
                    imgSelected.setImageResource(item.imgResID);
                }
            }
        });

    }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
             // Respond to the action bar's Up/Home button
             case android.R.id.home:
                 onBackPressed();
                 return true;
         }
         return super.onOptionsItemSelected(item);
     }
 }
