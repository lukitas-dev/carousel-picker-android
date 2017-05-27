package com.example.lucascruz.carrouselandroid;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer,ImageView> itensMap;
    private ImageView itemSelected;

    private ImageView imgSelected;
    private TextView txtSelected;
    private HorizontalScrollView pickerScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout picker = (LinearLayout) findViewById(R.id.pickerView);
        imgSelected = (ImageView) findViewById(R.id.img_selected);
        txtSelected = (TextView) findViewById(R.id.txt_selected);

        pickerScroll = (HorizontalScrollView) findViewById(R.id.pickerScroll);


        final List<Item> itens = createItemList();

        itensMap = new HashMap<>();
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
            sel.setImageResource(R.drawable.ic_selected);
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
