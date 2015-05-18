package com.example.administrador.myapplication.models.entities;

import com.example.administrador.myapplication.models.persistence.ServiceOrdersRepository;

import java.util.Date;
import java.util.List;

public class ServiceOrder {

    private Integer id;
    private String client;
    private String address;
    private Date date;
    private double value;
    private boolean paid;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrder that = (ServiceOrder) o;

        if (Double.compare(that.value, value) != 0) return false;
        if (paid != that.paid) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (paid ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceOrder{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", paid=" + paid +
                ", description='" + description + '\'' +
                '}';
    }

    public static List<ServiceOrder> getAll() {
        return ServiceOrdersRepository.getInstance().getAll();
    }

    public void save() {
        ServiceOrdersRepository.getInstance().save(this);
    }

}
