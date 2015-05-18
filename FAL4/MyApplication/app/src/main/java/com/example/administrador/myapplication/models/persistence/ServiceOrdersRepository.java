package com.example.administrador.myapplication.models.persistence;

import com.example.administrador.myapplication.models.entities.ServiceOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceOrdersRepository {

    private static Integer sSequence = 0;
    private static Map<Integer, ServiceOrder> sRepository = new HashMap<>();

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

    public List<ServiceOrder> getAll() {
        return new ArrayList<ServiceOrder>(sRepository.values());
    }

}
