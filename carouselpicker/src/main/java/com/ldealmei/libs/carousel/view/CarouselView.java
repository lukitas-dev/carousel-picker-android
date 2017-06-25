package com.ldealmei.libs.carousel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldealmei.libs.carousel.R;
import com.ldealmei.libs.carousel.interfaces.PickerListerner;
import com.ldealmei.libs.carousel.model.ItemPicker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucas Cruz on 24/06/2017.
 */

public abstract class CarouselView extends ViewGroup {

    protected Map<Integer,ImageView> indicatorsMap;
    protected ItemPicker selectedItem;
    protected ImageView indicatorSelected;
    protected HorizontalScrollView pickerScroll;
    protected LinearLayout pickerLayout;
    protected boolean displayIndicator;
    protected int customIndicator;
    protected int customIndicatorColor;
    protected int customIndicatorSize;
    protected int customDescriptionColor;
    protected List<ItemPicker> itens;
    protected int numberOfItensPerPage = 3;
    protected PickerListerner callback;
    protected Context context;

    public CarouselView(Context context) {
        super(context);
    }

    public CarouselView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(getContext(), R.layout.main, this);

        indicatorsMap = new HashMap<>();

        pickerScroll = (HorizontalScrollView) findViewById(R.id.picker_horizontal_scroll);
        pickerLayout = (LinearLayout) findViewById(R.id.picker_linear_layout);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CarouselPicker, 0, 0);
        customIndicator = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicator,0);
        displayIndicator = typedArray.getBoolean(R.styleable.CarouselPicker_displayIndicator,false);
        customIndicatorColor = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicatorColor,0);
        customIndicatorSize = typedArray.getResourceId(R.styleable.CarouselPicker_customIndicatorSize,0);
        customDescriptionColor = typedArray.getResourceId(R.styleable.CarouselPicker_customDescriptionColor,0);
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
                child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
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

    protected LinearLayout.LayoutParams calculateLayoutSize(){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new LinearLayout.LayoutParams(size.x/numberOfItensPerPage, getLayoutParams().height);
    }


}
