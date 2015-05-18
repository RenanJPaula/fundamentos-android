package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.ServiceOrdersRepository;

import java.util.Date;
import java.util.List;

public class ServiceOrder implements Parcelable {

    private Integer mId;
    private String mClient;
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
        if (mAddress != null ? !mAddress.equals(that.mAddress) : that.mAddress != null) return false;
        if (mDate != null ? !mDate.equals(that.mDate) : that.mDate != null) return false;
        return !(mDescription != null ? !mDescription.equals(that.mDescription) : that.mDescription != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mClient != null ? mClient.hashCode() : 0);
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
        return "ServiceOrder{" +
                "mId=" + mId +
                ", mClient='" + mClient + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mDate=" + mDate +
                ", mValue=" + mValue +
                ", mPaid=" + mPaid +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }

    public static List<ServiceOrder> getAll() {
        return ServiceOrdersRepository.getInstance().getAll();
    }

    public void save() {
        ServiceOrdersRepository.getInstance().save(this);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mId);
        out.writeString(mClient == null ? "" : mClient);
        out.writeString(mAddress == null ? "" : mAddress);
        out.writeLong(mDate == null ? 0 : mDate.getTime());
        out.writeDouble(mValue);
        out.writeByte((byte) (mPaid ? 1 : 0));
        out.writeString(mDescription == null ? "" : mDescription);
    }

    public static final Parcelable.Creator<ServiceOrder> CREATOR = new Parcelable.Creator<ServiceOrder>() {
        public ServiceOrder createFromParcel(Parcel in) {
            return new ServiceOrder(in);
        }

        public ServiceOrder[] newArray(int size) {
            return new ServiceOrder[size];
        }
    };

    private ServiceOrder(Parcel in) {
        mId = in.readInt();
        mClient = in.readString();
        mAddress = in.readString();
        final long dateTime = in.readLong();
        mDate = dateTime == 0 ? null : new Date(dateTime);
        mValue = in.readDouble();
        mPaid = in.readByte() == 1;
        mDescription = in.readString();
    }
}
