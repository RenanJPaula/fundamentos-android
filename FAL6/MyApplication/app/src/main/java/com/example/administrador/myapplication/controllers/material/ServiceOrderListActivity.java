package com.example.administrador.myapplication.controllers.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

public class ServiceOrderListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ServiceOrderListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order_list_material);

        this.bindElements();
    }

    private void bindElements() {
        mRecyclerView = AppUtil.get(findViewById(R.id.recycleView));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FloatingActionButton floatingActionButton = AppUtil.get(findViewById(R.id.fabAdd));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent goToAddActivity = new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class);
                startActivity(goToAddActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<ServiceOrder> serviceOrders = ServiceOrder.getAll();
        if (mRecyclerView.getAdapter() == null) {
            mAdapter = new ServiceOrderListAdapter(serviceOrders);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDataset(serviceOrders);
            mAdapter.notifyDataSetChanged();
        }
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
                //final ServiceOrder seletedServiceOrder = mServiceOrdersAdapter.getItem(mServiceOrderIndex);
                //goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_SERVICE_ORDER, seletedServiceOrder);
                //goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_START_BENCHMARK, SystemClock.elapsedRealtime());
                super.startActivity(goToEditActivity);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
