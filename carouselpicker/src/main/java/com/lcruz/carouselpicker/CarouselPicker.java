package com.lcruz.carouselpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucas Cruz on 27/05/2017.
 */

public class CarouselPicker extends ViewGroup {

    private Map<Integer,ImageView> indicatorsMap;
    private List<CompositeOnClickListener> listeners;
    private ItemPicker selectedItem;
    private ImageView indicatorSelected;
    private HorizontalScrollView pickerScroll;
    private LinearLayout pickerLayout;
    private LinearLayout myView;
    private boolean displayIndicator;
    private int customIndicator;
    private int customIndicatorColor;
    private int customIndicatorSize;
    private List<ItemPicker> itens;


    public CarouselPicker(Context context) {
        super(context);
    }

    public CarouselPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(getContext(), R.layout.main, this);

        indicatorsMap = new HashMap<>();
        listeners = new ArrayList<>();

        myView = (LinearLayout) findViewById(R.id.my_view);
        pickerScroll = (HorizontalScrollView) findViewById(R.id.picker_horizontal_scroll);
        pickerLayout = (LinearLayout) findViewById(R.id.picker_linear_layout);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CarouselPicker, 0, 0);
        customIndicator = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicator,0);
        displayIndicator = typedArray.getBoolean(R.styleable.CarouselPicker_displayIndicator,false);
        customIndicatorColor = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicatorColor,0);
        customIndicatorSize = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicatorSize,0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;
        int childLeft = this.getPaddingLeft();
        int childTop = this.getPaddingTop();
        int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        int childWidth = childRight - childLeft;
        int childHeight = childBottom - childTop;

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                //Get the maximum size of the child
                child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                        MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
                curWidth = child.getMeasuredWidth();
                curHeight = child.getMeasuredHeight();
                //wrap is reach to the end
                if (curLeft + curWidth >= childRight) {
                    curLeft = childLeft;
                    curTop += maxHeight;
                    maxHeight = 0;
                }
                child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
                if (maxHeight < curHeight)
                    maxHeight = curHeight;
                curLeft += curWidth;
            }
        }
    }


    public CarouselPicker displayIndicator(boolean display){
        this.displayIndicator = display;
        return this;
    }

    public CarouselPicker customIndicator(int indicatorRes){
        this.customIndicator = indicatorRes;
        return this;
    }
    public CarouselPicker customIndicatorColor(int colorRes){
        this.customIndicatorColor = colorRes;
        return this;
    }

    public CarouselPicker customIndicatorSize(int sizeRes){
        this.customIndicatorSize = sizeRes;
        return this;
    }

    public CarouselPicker addList(List<ItemPicker> itens){
       this.itens = itens;
        return this;
    }

    public void addListener(View.OnClickListener listener){
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).addOnClickListener(listener);
        }
    }

    public ItemPicker getSelectedItem() {
        return selectedItem;
    }

    public void build(Context context){
        int x = 0;
        for (final ItemPicker it : itens) {
            final int index = x;

            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            final LinearLayout sll = new LinearLayout(context);
            LinearLayout.LayoutParams sllParams = new LinearLayout.LayoutParams(size.x/3, getLayoutParams().height);
            sll.setLayoutParams(sllParams);
            sll.setPadding(50,50,50,0);
            sll.setOrientation(LinearLayout.VERTICAL);

            LinearLayout ll = new LinearLayout(context);
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
            ll.setLayoutParams(llParams);
            ll.setOrientation(LinearLayout.VERTICAL);

            ImageView img = new ImageView(context);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getLayoutParams().height/2);
            imgParams.gravity = Gravity.TOP;
            img.setLayoutParams(imgParams);
            img.setAdjustViewBounds(true);
            img.setImageResource(it.imgResID);
            ll.addView(img);

            TextView txt = new TextView(context);
            LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            txtParams.gravity = Gravity.TOP;
            txt.setLayoutParams(txtParams);
            txt.setTextColor(Color.WHITE);
            txt.setTypeface(Typeface.DEFAULT_BOLD);
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            if(it.txt == null) {
                txt.setText(it.txtResID);
            } else {
                txt.setText(it.txt);
            }
            ll.addView(txt);

            sll.addView(ll);

            if(displayIndicator) {
                ImageView sel = new ImageView(context);
                LinearLayout.LayoutParams selParams;
                if(customIndicatorSize != 0){
                    selParams = new LinearLayout.LayoutParams((int)context.getResources().getDimension(customIndicatorSize), LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                } else {
                    selParams = new LinearLayout.LayoutParams((int)context.getResources().getDimension(R.dimen.indicator_default_size),(int)context.getResources().getDimension(R.dimen.indicator_default_size),1f);
                }
                selParams.gravity = (Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                sel.setLayoutParams(selParams);
                sel.setScaleType(ImageView.ScaleType.FIT_XY);
                sel.setAdjustViewBounds(true);
                if(customIndicator != 0) {
                    sel.setImageResource(customIndicator);
                } else {
                    sel.setImageResource(R.drawable.ic_indicator);
                }
                if(customIndicatorColor != 0) {
                    sel.setColorFilter(ContextCompat.getColor(context, customIndicatorColor));
                } else {
                    sel.setColorFilter(ContextCompat.getColor(context, R.color.indicator_color_default));
                }
                if(x == 0){
                    indicatorSelected = sel;
                } else {
                    sel.setVisibility(View.GONE);
                }
                indicatorsMap.put(x, sel);

                sll.addView(sel);
            }
            CompositeOnClickListener groupListener = new CompositeOnClickListener();
            ll.setOnClickListener(groupListener);
            groupListener.addOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(displayIndicator) {
                        if (indicatorSelected != null) {
                            indicatorSelected.setVisibility(View.GONE);
                        }
                        indicatorSelected = indicatorsMap.get(index);
                        indicatorSelected.setVisibility(View.VISIBLE);
                    }

                    int scroll = (sll.getLeft() - (myView.getWidth()/ 2)) + (sll.getWidth() / 2);
                    pickerScroll.smoothScrollTo(scroll,0);

                    selectedItem = it;
                }
            });

           listeners.add(groupListener);

            pickerLayout.addView(sll);
            x++;
        }
    }

    private class CompositeOnClickListener implements View.OnClickListener{
        List<View.OnClickListener> listeners;

        public CompositeOnClickListener(){
            listeners = new ArrayList<OnClickListener>();
        }

        public void addOnClickListener(View.OnClickListener listener){
            listeners.add(listener);
        }

        @Override
        public void onClick(View v){
            for(View.OnClickListener listener : listeners){
                listener.onClick(v);
            }
        }
    }

}
