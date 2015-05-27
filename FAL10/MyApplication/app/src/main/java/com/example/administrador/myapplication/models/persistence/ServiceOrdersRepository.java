package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ServiceOrdersRepository {

    private static class Singleton {
        public static final ServiceOrdersRepository INSTANCE = new ServiceOrdersRepository();
    }

    private ServiceOrdersRepository() {
        super();
    }

    public static ServiceOrdersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public void save(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (serviceOrder.getId() == null) {
            db.insert(ServiceOrderContract.TABLE, null, ServiceOrderContract.getContentValues(serviceOrder));
        } else {
            String where = ServiceOrderContract.ID + " = ?";
            String[] args = {serviceOrder.getId().toString()};
            db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        }
        db.close();
        helper.close();
    }

    public void delete(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = ServiceOrderContract.ID + " = ?";
        String[] args = {serviceOrder.getId().toString()};
        db.delete(ServiceOrderContract.TABLE, where, args);
        db.close();
        helper.close();
    }

    public List<ServiceOrder> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(ServiceOrderContract.TABLE, ServiceOrderContract.COLUNS, null, null, null, null, ServiceOrderContract.DATE);
        List<ServiceOrder> serviceOrders = ServiceOrderContract.bindList(cursor);
        db.close();
        helper.close();
        return serviceOrders;
    }

}