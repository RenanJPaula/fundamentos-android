package com.example.administrador.myapplication.controllers.material;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.protocol.HTTP;

import java.util.List;

public class ServiceOrderListActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_EDIT = 2;
    private RecyclerView mServiceOrders;
    private ServiceOrderListAdapter mServiceOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order_list_material);

        this.bindElements();
    }

    private void bindElements() {
        mServiceOrders = AppUtil.get(findViewById(R.id.recyclerViewServiceOrders));
        mServiceOrders.setHasFixedSize(true);
        mServiceOrders.setLayoutManager(new LinearLayoutManager(this));

        final FloatingActionButton fabAdd = AppUtil.get(findViewById(R.id.fabAdd));
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent goToAddActivity = new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class);
                startActivityForResult(goToAddActivity, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateAdapterItens();
    }

    private void updateAdapterItens() {
        final List<ServiceOrder> serviceOrders = ServiceOrder.getAll();
        if (mServiceOrdersAdapter == null) {
            mServiceOrdersAdapter = new ServiceOrderListAdapter(serviceOrders);
            mServiceOrders.setAdapter(mServiceOrdersAdapter);
        } else {
            mServiceOrdersAdapter.setItens(serviceOrders);
            mServiceOrdersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD) {
                Toast.makeText(this, R.string.msg_add_success, Toast.LENGTH_LONG).show();
            } else if (requestCode == REQUEST_CODE_EDIT) {
                Toast.makeText(this, R.string.msg_edit_success, Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final ServiceOrder serviceOrder = mServiceOrdersAdapter.getSelectedItem();
        switch (item.getItemId()) {
            case R.id.actionEdit:
                final Intent goToEditActivity = new Intent(ServiceOrderListActivity.this, ServiceOrderActivity.class);
                goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_SERVICE_ORDER, serviceOrder);
                goToEditActivity.putExtra(ServiceOrderActivity.EXTRA_START_BENCHMARK, SystemClock.elapsedRealtime());
                super.startActivityForResult(goToEditActivity, REQUEST_CODE_EDIT);
                return true;
            case R.id.actionDelete:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.lbl_confirm)
                        .setMessage(R.string.msg_delete)
                        .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                serviceOrder.delete();
                                Toast.makeText(ServiceOrderListActivity.this, R.string.msg_delete_success, Toast.LENGTH_LONG).show();
                                updateAdapterItens();
                            }
                        })
                        .setNeutralButton(R.string.lbl_no, null)
                        .create().show();
                return true;
            case R.id.actionCall:
                // Best Practices: http://stackoverflow.com/questions/4275678/how-to-make-phone-call-using-intent-in-android
                final Intent goToSOPhoneCall = new Intent(Intent.ACTION_DIAL);
                goToSOPhoneCall.setData(Uri.parse("tel:" + serviceOrder.getPhone()));
                startActivity(goToSOPhoneCall);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_service_order_list_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * @see <a href="http://developer.android.com/guide/components/intents-filters.html">Forcing an app chooser</a>
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionShare:
                // Create the text message with a string
                final Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, ServiceOrder.getAll().toString());
                sendIntent.setType(HTTP.PLAIN_TEXT_TYPE);

                // Create intent to show the chooser dialog
                final Intent chooser = Intent.createChooser(sendIntent, getString(R.string.lbl_share_option));

                // Verify the original intent will resolve to at least one activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
