package com.ldealmei.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.interfaces.PickerListerner;
import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.R;

import java.util.ArrayList;
import java.util.List;

public class PickerImageActivity extends AppCompatActivity {

    private ImageView imgSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_item);

        getSupportActionBar().setTitle("Image Description Picker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgSelected = (ImageView) findViewById(R.id.img_selected);

        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.ic_banana));
        itens.add(new ItemPicker(R.drawable.ic_grapes));
        itens.add(new ItemPicker(R.drawable.ic_orange));
        itens.add(new ItemPicker(R.drawable.ic_pineapple));
        itens.add(new ItemPicker(R.drawable.ic_raspberry));
        itens.add(new ItemPicker(R.drawable.ic_strawberry));
        itens.add(new ItemPicker(R.drawable.ic_watermelon));

        final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

        carouselPicker.init(this)
                .addList(itens)
                .addCallback(new PickerListerner(){
                    @Override
                    public void onClickItem(ItemPicker item) {
                        imgSelected.setImageResource(item.imgResID);
                    }
                })
                .build();


        imgSelected.setImageResource(itens.get(0).imgResID);

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
