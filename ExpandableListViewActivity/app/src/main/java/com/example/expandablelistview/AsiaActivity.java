package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AsiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asia);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString(Constants.ASIA_ACTIVITY_DATA_KEY);
        TextView text1 = findViewById(R.id.text1);
        text1.setText(name);
    }
}
