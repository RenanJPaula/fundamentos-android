package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

public class ServiceOrderListActivity extends AppCompatActivity {

    private ListView mOrders;
    private ServiceOrderListAdapter mOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order_list);

        mOrders = AppUtil.get(findViewById(R.id.listViewServiceOrders));

        mOrdersAdapter = new ServiceOrderListAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrdersAdapter.setValues(ServiceOrder.getAll());
        if (mOrders.getAdapter() == null) {
            mOrders.setAdapter(mOrdersAdapter);
        } else {
            mOrdersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_service_order_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
