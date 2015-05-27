package com.example.administrador.myapplication.controllers;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class ServiceOrderListAdapter extends RecyclerView.Adapter<ServiceOrderListAdapter.ViewHolder> {

    private List<ServiceOrder> mItens;

    private int mPosition;

    public ServiceOrderListAdapter(List<ServiceOrder> itens) {
        mItens = itens;
    }

    public void setItens(List<ServiceOrder> itens) {
        this.mItens = itens;
    }

    public ServiceOrder getSelectedItem() {
        return mItens.get(mPosition);
    }

    @Override
    public ServiceOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.service_order_list_item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();

        final ServiceOrder serviceOrder = mItens.get(position);
        holder.mTxtValue.setText(AppUtil.formatDecimal(serviceOrder.getValue()));
        if (serviceOrder.isPaid()) {
            holder.mTxtValue.setTextColor(holder.mTxtClient.getTextColors());
        } else {
            holder.mTxtValue.setTextColor(context.getResources().getColor(R.color.material_red_600));
        }
        holder.mTxtClient.setText(serviceOrder.getClient());
        holder.mTxtClient.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_text_client, 0, 0, 0);
        holder.mTxtDate.setText(AppUtil.formatDate(serviceOrder.getDate()));
        holder.mTxtDate.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_text_date, 0, 0, 0);

        /** Popup Menu */
        holder.mImageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = holder.getLayoutPosition();
                final PopupMenu popup = new PopupMenu(context, v);
                // This context must implements OnMenuItemClickListener
                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) context);
                popup.inflate(R.menu.menu_service_order_list_popup);
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.mImageViewMenu.setOnClickListener(null);
        super.onViewRecycled(holder);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtValue;
        private final TextView mTxtClient;
        private final TextView mTxtDate;
        private final ImageView mImageViewMenu;

        public ViewHolder(View view) {
            super(view);
            mTxtValue = AppUtil.get(view.findViewById(R.id.textViewValue));
            mTxtClient = AppUtil.get(view.findViewById(R.id.textViewClient));
            mTxtDate = AppUtil.get(view.findViewById(R.id.textViewDate));
            mImageViewMenu = AppUtil.get(view.findViewById(R.id.imageViewMenu));
        }
    }
}
