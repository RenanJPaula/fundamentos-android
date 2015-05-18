package com.example.administrador.myapplication.controllers;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class ServiceOrderListAdapter extends BaseAdapter {

    Activity mContext;
    List<ServiceOrder> mValues;

    public ServiceOrderListAdapter(Activity context) {
        mContext = context;
    }

    public ServiceOrderListAdapter(Activity context, List<ServiceOrder> values) {
        mValues = values;
        mContext = context;
    }

    public void setValues(List<ServiceOrder> values) {
        this.mValues = values;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public ServiceOrder getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View serviceOrderView = mContext.getLayoutInflater().inflate(R.layout.service_order_list_item, parent, false);

        final ServiceOrder serviceOrder = getItem(position);

        TextView textView = AppUtil.get(serviceOrderView.findViewById(R.id.textViewDate));
        textView.setText(new SimpleDateFormat("dd/MM/yyyy", AppUtil.LOCALE_PT_BR).format(serviceOrder.getDate()));

        textView = AppUtil.get(serviceOrderView.findViewById(R.id.textViewValue));
        textView.setText(String.valueOf(serviceOrder.getValue()));

        textView = AppUtil.get(serviceOrderView.findViewById(R.id.textViewPaid));
        textView.setText(serviceOrder.isPaid() ? mContext.getString(R.string.lbl_yes) : mContext.getString(R.string.lbl_no));

        return serviceOrderView;
    }
}
