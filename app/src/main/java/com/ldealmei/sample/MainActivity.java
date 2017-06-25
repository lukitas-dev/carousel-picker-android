package com.ldealmei.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ldealmei.sample.activities.PickerImageActivity;
import com.ldealmei.sample.activities.PickerImageRestActivity;
import com.ldealmei.sample.activities.PickerImageWithDescriptionActivity;
import com.ldealmei.sample.activities.PickerImageWithDescriptionAndIndicatorActivity;
import com.ldealmei.sample.activities.PickerImageWithIndicatorActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void open(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.sample_image_picker:
                intent = new Intent(MainActivity.this,PickerImageActivity.class);
                break;
            case R.id.sample_image_description_picker:
                intent = new Intent(MainActivity.this,PickerImageWithDescriptionActivity.class);
                break;
            case R.id.sample_image_indicator_picker:
                intent = new Intent(MainActivity.this,PickerImageWithIndicatorActivity.class);
                break;
            case R.id.sample_image_description_indicator_picker:
                intent = new Intent(MainActivity.this,PickerImageWithDescriptionAndIndicatorActivity.class);
                break;
            case R.id.sample_image_rest_picker:
                intent = new Intent(MainActivity.this,PickerImageRestActivity.class);
                break;
        }
        startActivity(intent);
    }

}
