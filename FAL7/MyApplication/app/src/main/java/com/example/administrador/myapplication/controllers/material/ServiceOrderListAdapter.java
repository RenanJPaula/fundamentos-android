package com.example.administrador.myapplication.controllers.material;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class ServiceOrderListAdapter extends RecyclerView.Adapter<ServiceOrderListAdapter.ViewHolder> {

    private List<ServiceOrder> mItens;

    /** Context Menu */
    private int mPosition;

    public ServiceOrderListAdapter(List<ServiceOrder> itens) {
        mItens = itens;
    }

    public void setItens(List<ServiceOrder> itens) {
        this.mItens = itens;
    }

    /** Context Menu */
    public ServiceOrder getLongClickItem() {
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
        final Context context = holder.mTxtDate.getContext();
        final ServiceOrder serviceOrder = mItens.get(position);
        holder.mTxtDate.setText(AppUtil.formatDate(serviceOrder.getDate()));
        holder.mTxtClient.setText(serviceOrder.getClient());
        holder.mTxtValue.setText(AppUtil.formatDecimal(serviceOrder.getValue()));
        /** Context Menu */
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPosition = holder.getLayoutPosition();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    /** Context Menu */
    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    /** Context Menu (View.OnCreateContextMenuListener) */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView mTxtValue;
        public TextView mTxtClient;
        public TextView mTxtDate;

        public ViewHolder(View view) {
            super(view);
            mTxtValue = AppUtil.get(view.findViewById(R.id.textViewValue));
            mTxtClient = AppUtil.get(view.findViewById(R.id.textViewClient));
            mTxtDate = AppUtil.get(view.findViewById(R.id.textViewDate));

            /** Context Menu */
            view.setOnCreateContextMenuListener(this);
        }

        /** Context Menu */
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.lbl_options);
            menu.add(Menu.NONE, R.id.actionEdit, Menu.NONE, R.string.lbl_edit);
        }
    }
}
