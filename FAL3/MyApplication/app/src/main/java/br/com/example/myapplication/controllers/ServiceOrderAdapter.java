package br.com.example.myapplication.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.example.myapplication.R;
import br.com.example.myapplication.models.entities.ServiceOrder;

public class ServiceOrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<ServiceOrder> mValues;

    public ServiceOrderAdapter(List<ServiceOrder> values, Context context) {
        mContext = context;
        mValues = values;
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
        ServiceOrder serviceOrder = getItem(position);

        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View serviceOrderView = inflator.inflate(R.layout.service_order_list_item, parent, false);

        TextView textViewValue = (TextView) serviceOrderView.findViewById(R.id.textViewValue);
        textViewValue.setText(String.valueOf(serviceOrder.getValue()));

        TextView textViewDate = (TextView) serviceOrderView.findViewById(R.id.textViewDate);
        textViewDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(serviceOrder.getDate()));

        TextView textViewPaid = (TextView) serviceOrderView.findViewById(R.id.textViewPaid);
        textViewPaid.setText(serviceOrder.isPaid() ? "Sim" : "Não");

        return serviceOrderView;
    }
}
