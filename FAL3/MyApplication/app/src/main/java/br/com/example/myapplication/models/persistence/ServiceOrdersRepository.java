package br.com.example.myapplication.models.persistence;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.example.myapplication.models.entities.ServiceOrder;

public class ServiceOrdersRepository {

    private static class Singleton {
        public static final ServiceOrdersRepository INSTANCE = new ServiceOrdersRepository();
    }

    private ServiceOrdersRepository() {
        super();
    }

    public static ServiceOrdersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public List<ServiceOrder> getAll() {
        List<ServiceOrder> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ServiceOrder so = new ServiceOrder();
            so.setClient("Client " + i);
            so.setAddress("Address " + i);
            so.setDate(new Date());
            so.setValue(10 * i);
            so.setPaid(i % 2 == 0);
            so.setDescription("Description " + i);
            list.add(so);
        }
        return list;
    }

}
