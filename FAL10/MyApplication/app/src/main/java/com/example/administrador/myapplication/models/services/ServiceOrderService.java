package com.example.administrador.myapplication.models.services;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class ServiceOrderService {

    private ServiceOrderService() {
        super();
    }

    public static List<ServiceOrder> getAll() {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        try {
            URL url = new URL("http://10.11.21.89:3000/api/serviceorders/");
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + conn.getResponseCode());
            }
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ServiceOrder.class);
            serviceOrders = objectMapper.readValue(conn.getInputStream(), collectionType);
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return serviceOrders;
    }

}
