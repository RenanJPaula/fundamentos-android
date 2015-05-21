package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.ServiceOrdersRepository;

import java.util.Date;
import java.util.List;

public class ServiceOrder implements Parcelable {

    private Integer mId;
    private String mClient;
    private String mPhone;
    private String mAddress;
    private Date mDate;
    private double mValue;
    private boolean mPaid;
    private String mDescription;

    public ServiceOrder() {
        super();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getClient() {
        return mClient;
    }

    public void setClient(String client) {
        this.mClient = client;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        this.mValue = value;
    }

    public boolean isPaid() {
        return mPaid;
    }

    public void setPaid(boolean paid) {
        this.mPaid = paid;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrder that = (ServiceOrder) o;

        if (Double.compare(that.mValue, mValue) != 0) return false;
        if (mPaid != that.mPaid) return false;
        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mClient != null ? !mClient.equals(that.mClient) : that.mClient != null) return false;
        if (mPhone != null ? !mPhone.equals(that.mPhone) : that.mPhone != null) return false;
        if (mAddress != null ? !mAddress.equals(that.mAddress) : that.mAddress != null)
            return false;
        if (mDate != null ? !mDate.equals(that.mDate) : that.mDate != null) return false;
        return !(mDescription != null ? !mDescription.equals(that.mDescription) : that.mDescription != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mClient != null ? mClient.hashCode() : 0);
        result = 31 * result + (mPhone != null ? mPhone.hashCode() : 0);
        result = 31 * result + (mAddress != null ? mAddress.hashCode() : 0);
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        temp = Double.doubleToLongBits(mValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mPaid ? 1 : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "serviceOrder: {" +
                "id:" + mId +
                ", client:'" + mClient + '\'' +
                ", phone:'" + mPhone + '\'' +
                ", address:'" + mAddress + '\'' +
                ", date:" + mDate +
                ", value:" + mValue +
                ", paid:" + mPaid +
                ", description:'" + mDescription + '\'' +
                '}';
    }

    public static List<ServiceOrder> getAll() {
        return ServiceOrdersRepository.getInstance().getAll();
    }

    public void save() {
        ServiceOrdersRepository.getInstance().save(this);
    }

    public void delete() {
        ServiceOrdersRepository.getInstance().delete(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mClient);
        dest.writeString(this.mPhone);
        dest.writeString(this.mAddress);
        dest.writeLong(mDate != null ? mDate.getTime() : -1);
        dest.writeDouble(this.mValue);
        dest.writeByte(mPaid ? (byte) 1 : (byte) 0);
        dest.writeString(this.mDescription);
    }

    private ServiceOrder(Parcel in) {
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mClient = in.readString();
        this.mPhone = in.readString();
        this.mAddress = in.readString();
        long tmpDate = in.readLong();
        this.mDate = tmpDate == -1 ? null : new Date(tmpDate);
        this.mValue = in.readDouble();
        this.mPaid = in.readByte() != 0;
        this.mDescription = in.readString();
    }

    public static final Parcelable.Creator<ServiceOrder> CREATOR = new Parcelable.Creator<ServiceOrder>() {
        public ServiceOrder createFromParcel(Parcel source) {
            return new ServiceOrder(source);
        }

        public ServiceOrder[] newArray(int size) {
            return new ServiceOrder[size];
        }
    };
}
