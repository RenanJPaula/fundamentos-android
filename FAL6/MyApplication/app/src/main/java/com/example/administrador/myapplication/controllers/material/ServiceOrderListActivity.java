package com.example.administrador.myapplication.controllers.material;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

public class ServiceOrderListActivity extends AppCompatActivity {

    private ListView mServiceOrders;
    private ServiceOrderListAdapter mServiceOrdersAdapter;
    private int mServiceOrderIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order_list);

        this.bindElements();

        super.registerForContextMenu(mServiceOrders);

        mServiceOrdersAdapter = new ServiceOrderListAdapter(this);
    }

    private void bindElements() {
        mServiceOrders = AppUtil.get(findViewById(R.id.listViewServiceOrders));
        mServiceOrders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mServiceOrderIndex = position;
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mServiceOrdersAdapter.setValues(ServiceOrder.getAll());
        if (mServiceOrders.getAdapter() == null) {
            mServiceOrders.setAdapter(mServiceOrdersAdapter);
        } else {
            mServiceOrdersAdapter.notifyDataSetChanged();
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
            case R.id.actionAdd:
                final Intent goToAddActivity = new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class);
                super.startActivity(goToAddActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_service_order_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionEdit:
                final Intent goToEditActivity = new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class);
                final ServiceOrder serviceOrder = mServiceOrdersAdapter.getItem(mServiceOrderIndex);
                goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_SERVICE_ORDER, serviceOrder);
                goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_START_BENCHMARK, SystemClock.elapsedRealtime());
                super.startActivity(goToEditActivity);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
