package com.example.administrador.myapplication.controllers.material;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class ServiceOrderListAdapter extends RecyclerView.Adapter<ServiceOrderListAdapter.ViewHolder> {

    private List<ServiceOrder> mItens;

    public ServiceOrderListAdapter(List<ServiceOrder> itens) {
        mItens = itens;
    }

    public void setDataset(List<ServiceOrder> itens) {
        this.mItens = itens;
    }

    @Override
    public ServiceOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.service_order_list_item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.mTxtDate.getContext();
        final ServiceOrder serviceOrder = mItens.get(position);
        holder.mTxtDate.setText(AppUtil.format(serviceOrder.getDate(), AppUtil.PATTERN_DATETIME));
        holder.mTxtValue.setText(AppUtil.format(serviceOrder.getValue(), AppUtil.PATTERN_NUMBER));
        holder.mTxtPaid.setText(serviceOrder.isPaid() ? context.getString(R.string.lbl_yes) : context.getString(R.string.lbl_no));
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTxtValue;
        public TextView mTxtDate;
        public TextView mTxtPaid;

        public ViewHolder(View view) {
            super(view);
            mTxtValue = AppUtil.get(view.findViewById(R.id.textViewValue));
            mTxtDate = AppUtil.get(view.findViewById(R.id.textViewDate));
            mTxtPaid = AppUtil.get(view.findViewById(R.id.textViewPaid));
        }
    }
}
