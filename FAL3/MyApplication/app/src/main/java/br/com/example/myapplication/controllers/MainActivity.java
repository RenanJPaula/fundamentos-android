package br.com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextUser = (EditText) findViewById(R.id.editTextUser);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonSignin = (Button) findViewById(R.id.buttonSignIn);

        mButtonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(MainActivity.this, ServiceOrderListActivity.class);
                startActivity(goToNextActivity);
            }
        });
    }

}
