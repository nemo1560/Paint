package com.example.nemo1.paint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomPaint customPaint;
        //Create instance CustomPaint class
        // No XML file; just one custom view created programmatically.
        // Request the full available screen for layout.
        customPaint = new CustomPaint(this);
        customPaint.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(customPaint);
    }
}
