package com.example.administrador.myapplication.controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.CastUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrador on 13/05/2015.
 */
public class ServiceOrderListAdapter extends BaseAdapter {

    List<ServiceOrder> mValues;
    Activity mContext;

    public ServiceOrderListAdapter(List<ServiceOrder> values, Activity context) {
        mValues = values;
        mContext = context;
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
        LayoutInflater inflater = mContext.getLayoutInflater();
        View serviceOrderView = inflater.inflate(R.layout.service_order_list_item, parent, false);

        ServiceOrder so = getItem(position);

        TextView tx = CastUtil.get(serviceOrderView.findViewById(R.id.textViewDate));
        tx.setText(new SimpleDateFormat("dd/MM/yyyy").format(so.getDate()));

        tx = CastUtil.get(serviceOrderView.findViewById(R.id.textViewValue));
        tx.setText(String.valueOf(so.getValue()));

        tx = CastUtil.get(serviceOrderView.findViewById(R.id.textViewPaid));
        tx.setText(so.isPaid() ? "Sim" : "Não");

        return serviceOrderView;
    }
}
