package com.ldealmei.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ldealmei.libs.carousel.CarouselPicker;
import com.ldealmei.libs.carousel.interfaces.PickerListerner;
import com.ldealmei.libs.carousel.model.ItemPicker;
import com.ldealmei.sample.R;
import com.ldealmei.sample.interfaces.CallbackResponse;
import com.ldealmei.sample.manager.CarouselManager;

import java.util.List;

public class PickerImageRestActivity extends AppCompatActivity {

    private ImageView imgSelected;
    private TextView txtSelected;
    private CarouselPicker carouselPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_image_rest);

        getSupportActionBar().setTitle("Image Description Indicator Sample");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgSelected = (ImageView) findViewById(R.id.img_selected);
        txtSelected = (TextView) findViewById(R.id.txt_selected);
        carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

        new CarouselManager(this).getItens(new CallbackResponse<List<ItemPicker>>(){

            @Override
            public void onRestResponse(List<ItemPicker> response) {
                buildCarousel(response);
            }

            @Override
            public void onLocalResponse(List<ItemPicker> response) {
                buildCarousel(response);
            }

            @Override
            public void onRestFailure(List<ItemPicker> response, Throwable t) {
                buildCarousel(response);
            }

            @Override
            public void onFailure(String msg, Throwable t) {
                Toast.makeText(PickerImageRestActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void buildCarousel(List<ItemPicker> itens){
        carouselPicker.init(this)
                .addList(itens)
                .addCallback(new PickerListerner(){
                    @Override
                    public void onClickItem(ItemPicker item) {

                        if(item.imgUrl != null){
                            Glide.with(PickerImageRestActivity.this)
                                    .load(item.imgUrl)
                                    .into(imgSelected);
                        } else {
                            imgSelected.setImageResource(item.imgResID);
                        }
                        if(item.txtResID != 0) {
                            txtSelected.setText(item.txtResID);
                        } else {
                            txtSelected.setText(item.txt);
                        }
                    }
                })
                .build();

        if(itens.get(0).imgUrl != null){
            Glide.with(PickerImageRestActivity.this)
                    .load(itens.get(0).imgUrl)
                    .into(imgSelected);
        } else {
            imgSelected.setImageResource(itens.get(0).imgResID);
        }
        if(itens.get(0).txtResID != 0) {
            txtSelected.setText(itens.get(0).txtResID);
        } else {
            txtSelected.setText(itens.get(0).txt);
        }
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
