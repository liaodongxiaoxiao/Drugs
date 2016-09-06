package com.ldxx.drug.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ldxx.drug.app.DrugApplication;
import com.ldxx.drug.component.AppComponent;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(DrugApplication.get(this).component());
    }


    protected abstract void setupComponent(AppComponent appComponent);
}
