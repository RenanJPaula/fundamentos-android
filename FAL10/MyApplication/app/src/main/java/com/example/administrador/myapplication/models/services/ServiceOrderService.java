package com.example.administrador.myapplication.models.services;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.OutputStream;
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

    public static void save(ServiceOrder serviceOrder) {
        try {
            URL url = new URL("http://10.11.21.89:3000/api/serviceorders/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(serviceOrder));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
