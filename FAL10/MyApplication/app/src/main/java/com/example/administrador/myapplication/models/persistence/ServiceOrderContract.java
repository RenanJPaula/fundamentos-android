package com.example.administrador.myapplication.models.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceOrderContract {

    public static final String TABLE = "service_order";
    public static final String ID = "id";
    public static final String CLIENT = "client";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String DATE = "date";
    public static final String VALUE = "value";
    public static final String PAID = "paid";
    public static final String DESCRIPTION = "description";

    public static final String[] COLUNS = {ID, CLIENT, PHONE, ADDRESS, DATE, VALUE, PAID, DESCRIPTION};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(CLIENT + " TEXT, ");
        sql.append(PHONE + " TEXT, ");
        sql.append(ADDRESS + " TEXT, ");
        sql.append(DATE + " INTEGER, ");
        sql.append(VALUE + " REAL, ");
        sql.append(PAID + " INTEGER, ");
        sql.append(DESCRIPTION + " TEXT ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(ServiceOrder serviceOrder) {
        ContentValues content = new ContentValues();
        content.put(ID, serviceOrder.getId());
        content.put(CLIENT, serviceOrder.getClient());
        content.put(PHONE, serviceOrder.getPhone());
        content.put(ADDRESS, serviceOrder.getAddress());
        content.put(DATE, serviceOrder.getDate().getTime());
        content.put(VALUE, serviceOrder.getValue());
        content.put(PAID, serviceOrder.isPaid() ? 1 : 0);
        content.put(DESCRIPTION, serviceOrder.getDescription());
        return content;
    }

    public static ServiceOrder bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setId((cursor.getInt(cursor.getColumnIndex(ID))));
            serviceOrder.setClient(cursor.getString(cursor.getColumnIndex(CLIENT)));
            serviceOrder.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            serviceOrder.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            serviceOrder.setDate(new Date(cursor.getInt(cursor.getColumnIndex(DATE))));
            serviceOrder.setValue(cursor.getLong(cursor.getColumnIndex(VALUE)));
            serviceOrder.setPaid(cursor.getInt(cursor.getColumnIndex(PAID)) == 1);
            serviceOrder.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            return serviceOrder;
        }
        return null;
    }

    public static List<ServiceOrder> bindList(Cursor cursor) {
        final List<ServiceOrder> serviceOrders = new ArrayList<ServiceOrder>();
        while (cursor.moveToNext()) {
            serviceOrders.add(bind(cursor));
        }
        return serviceOrders;
    }

}
