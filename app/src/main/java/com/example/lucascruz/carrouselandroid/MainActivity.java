package com.example.lucascruz.carrouselandroid;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcruz.carouselpicker.CarouselPicker;
import com.lcruz.carouselpicker.ItemPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer,ImageView> itensMap;
    private ImageView itemSelected;

    private ImageView imgSelected;
    private TextView txtSelected;
    private HorizontalScrollView pickerScroll;
    private LinearLayout picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSelected = (ImageView) findViewById(R.id.img_selected);
        txtSelected = (TextView) findViewById(R.id.txt_selected);

        List<ItemPicker> itens = new ArrayList<>();
        itens.add(new ItemPicker(R.drawable.ic_tea,"Green Tea"));
        itens.add(new ItemPicker(R.drawable.ic_sandwich,"Sandwich"));
        itens.add(new ItemPicker(R.drawable.ic_cheese,"Cheese"));
        itens.add(new ItemPicker(R.drawable.ic_tea,"Green Tea"));
        itens.add(new ItemPicker(R.drawable.ic_sandwich,"Sandwich"));
        itens.add(new ItemPicker(R.drawable.ic_cheese,"Cheese"));

        final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

        carouselPicker.addList(itens).build(this);

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

    private void buildPicker(){
        final List<Item> itens = createItemList();
        int x = 0;
        for (final Item it : itens) {
            final int index = x;
            final LinearLayout ll = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.setLayoutParams(llParams);
            ll.setPadding(50,50,50,50);
            ll.setOrientation(LinearLayout.VERTICAL);

            ImageView img = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            img.setLayoutParams(imgParams);
            img.setAdjustViewBounds(true);
            img.setImageResource(it.imgResourceID);
            ll.addView(img);

            TextView txt = new TextView(getApplicationContext());
            LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            txt.setLayoutParams(txtParams);
            txt.setPadding(30,30,30,30);
            txt.setTextColor(Color.BLUE);
            txt.setTypeface(Typeface.DEFAULT_BOLD);
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txt.setText(it.txt);
            ll.addView(txt);

            ImageView sel = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams selParams = new LinearLayout.LayoutParams(50,50);
            selParams.gravity = Gravity.CENTER_HORIZONTAL;
            sel.setLayoutParams(selParams);
            sel.setAdjustViewBounds(true);
            sel.setImageResource(R.drawable.ic_indicator);
            sel.setVisibility(View.GONE);
            itensMap.put(x,sel);

            ll.addView(sel);

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemSelected != null){
                        itemSelected.setVisibility(View.GONE);
                    }
                    itemSelected = itensMap.get(index);
                    itemSelected.setVisibility(View.VISIBLE);

                    imgSelected.setImageResource(it.imgResourceID);
                    txtSelected.setText(it.txt);

                    int scroll = (ll.getLeft() - (getWindowManager().getDefaultDisplay().getWidth()/ 2)) + (ll.getWidth() / 2);
                    pickerScroll.smoothScrollTo(scroll,0);
                }
            });
            picker.addView(ll);
            x++;
        }
    }

    private List<Item> createItemList(){
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(R.drawable.ic_tea,"Green Tea"));
        itens.add(new Item(R.drawable.ic_sandwich,"Sandwich"));
        itens.add(new Item(R.drawable.ic_cheese,"Cheese"));
        itens.add(new Item(R.drawable.ic_tea,"Green Tea"));
        itens.add(new Item(R.drawable.ic_sandwich,"Sandwich"));
        itens.add(new Item(R.drawable.ic_cheese,"Cheese"));
        itens.add(new Item(R.drawable.ic_tea,"Green Tea"));
        itens.add(new Item(R.drawable.ic_sandwich,"Sandwich"));
        itens.add(new Item(R.drawable.ic_cheese,"Cheese"));
        return itens;
    }
}
