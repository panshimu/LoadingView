package com.miaozi.loadingview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingView = findViewById(R.id.loadingView);

    }

    public void open(View view) {
        mLoadingView.show();
    }

    public void hide(View view) {
        mLoadingView.hide();
    }
}
