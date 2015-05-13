package br.com.example.myapplication.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.example.myapplication.R;

public class ServiceOrderListActivity extends AppCompatActivity {

    private ListView mListViewServiceOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewServiceOrders = (ListView) findViewById(R.id.listViewServiceOrders);
        String[] values = {"obj1", "obj2", "obj3", "obj4"};

        BaseAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        mListViewServiceOrders.setAdapter(adapter);
    }

}
