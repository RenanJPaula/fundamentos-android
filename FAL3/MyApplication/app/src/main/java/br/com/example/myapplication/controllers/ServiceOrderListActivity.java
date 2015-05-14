package br.com.example.myapplication.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.example.myapplication.R;
import br.com.example.myapplication.models.entities.ServiceOrder;

public class ServiceOrderListActivity extends AppCompatActivity {

    private ListView mListViewServiceOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_order_list);

        mListViewServiceOrders = (ListView) findViewById(R.id.listViewServiceOrders);

        BaseAdapter adapter = new ServiceOrderAdapter(ServiceOrder.getAll(), this);

        mListViewServiceOrders.setAdapter(adapter);
    }

}
