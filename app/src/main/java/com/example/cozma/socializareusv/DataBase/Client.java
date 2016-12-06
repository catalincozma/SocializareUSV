package com.example.cozma.socializareusv.DataBase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cozma on 22.11.2016.
 */

public class Client implements Parcelable, Comparable<Client> {
    private int _id;
    private String _firstname;
    private String _lastName;
    private String _university;
    private String _phone_number;
    private String _imagePath;

    public static int options;


    public String get_imagePath() {
        return _imagePath;
    }

    public void set_imagePath(String _imagePath) {
        this._imagePath = _imagePath;
    }

    public Client(int _id, String _firstname, String _lastName, String _university, String _phone, String _imagePath) {
        this._id = _id;
        this._firstname = _firstname;
        this._lastName = _lastName;
        this._university = _university;
        this._phone_number = _phone;
        this._imagePath = _imagePath;
    }

    public Client( String _firstname, String _lastName, String _university, String _phone) {
        this._imagePath = _imagePath;
        this._firstname = _firstname;
        this._lastName = _lastName;
        this._university = _university;
        this._phone_number = _phone;
    }


    private Client(Parcel in) {
        _firstname = in.readString();
        _lastName = in.readString();
        _university = in.readString();
        _phone_number = in.readString();
        _imagePath = in.readString();

    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_university() {
        return _university;
    }

    public void set_university(String _university) {
        this._university = _university;
    }

    public String get_phone() {
        return _phone_number;
    }

    public void set_phone(String _phone) {
        this._phone_number = _phone;
    }

    public Client() {

    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this._firstname);
        out.writeString(this._lastName);
        out.writeString(this._university);
        out.writeString(this._phone_number);
        out.writeString(this._imagePath);
    }

    @Override
    public int compareTo(Client another) {
        if (options == 0) {

            return get_phone().compareTo(another.get_phone());
        } else {
            return get_firstname().compareTo(another.get_firstname());
        }
    }
}
