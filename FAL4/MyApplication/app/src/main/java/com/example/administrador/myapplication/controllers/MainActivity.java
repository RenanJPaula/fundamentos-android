package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.util.CastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText login = CastUtil.get(findViewById(R.id.editTextLogin));
        final EditText pass = CastUtil.get(findViewById(R.id.editTextPass));

        Button btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceOrderListActivity.class));
            }
        });
    }

}
