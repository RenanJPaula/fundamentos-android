package com.example.administrador.myapplication.models.persistence;

import com.example.administrador.myapplication.models.entities.ServiceOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ServiceOrdersRepository {

    private static Integer sSequence = 0;
    private static Map<Integer, ServiceOrder> sRepository = new LinkedHashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            final ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setClient("Person Name " + i);
            serviceOrder.setAddress("Address " + i);
            serviceOrder.setPhone("99999999 " + i);
            serviceOrder.setDate(new Date());
            serviceOrder.setValue(10.00D * (i + 1));
            serviceOrder.setPaid(i % 2 == 0);
            serviceOrder.setDescription("Description " + i);
            serviceOrder.save();
        }
    }

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
        if (serviceOrder.getId() == null) {
            serviceOrder.setId(sSequence++);
        }
        sRepository.put(serviceOrder.getId(), serviceOrder);
    }

    public void delete(ServiceOrder serviceOrder) {
        sRepository.remove(serviceOrder.getId());
    }

    public List<ServiceOrder> getAll() {
        return new ArrayList<ServiceOrder>(sRepository.values());
    }

}
