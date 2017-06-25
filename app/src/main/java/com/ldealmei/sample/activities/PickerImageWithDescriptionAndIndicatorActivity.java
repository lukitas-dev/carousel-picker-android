package com.ldealmei.sample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.interfaces.PickerListerner;
import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.R;

import java.util.ArrayList;
import java.util.List;

public class PickerImageWithDescriptionAndIndicatorActivity extends AppCompatActivity {

    private ImageView imgSelected;
    private TextView txtSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcker_image_with_description_and_indicator);

        getSupportActionBar().setTitle("Image Description Indicator Sample");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        carouselPicker.init(this)
                .addList(itens)
                .addCallback(new PickerListerner(){
                    @Override
                    public void onClickItem(ItemPicker item) {
                        imgSelected.setImageResource(item.imgResID);
                        if(item.txtResID != 0) {
                            txtSelected.setText(item.txtResID);
                        } else {
                            txtSelected.setText(item.txt);
                        }
                    }
                })
                .build();


        imgSelected.setImageResource(itens.get(0).imgResID);
        txtSelected.setText(itens.get(0).txt);

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
