package com.example.projectappkelasbesar;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDoClass implements Parcelable {

    private Integer idToDo;
    private String namaToDo, tanggalToDoStart, jamToDoStart, tanggalToDoFinal, jamToDoFinal, status;

    public ToDoClass(Integer idToDo, String namaToDo, String tanggalToDoStart, String jamToDoStart, String tanggalToDoFinal, String jamToDoFinal, String status) {
        this.idToDo = idToDo;
        this.namaToDo = namaToDo;
        this.tanggalToDoStart = tanggalToDoStart;
        this.jamToDoStart = jamToDoStart;
        this.tanggalToDoFinal = tanggalToDoFinal;
        this.jamToDoFinal = jamToDoFinal;
        this.status = status;
    }

    protected ToDoClass(Parcel in) {
        if (in.readByte() == 0) {
            idToDo = null;
        } else {
            idToDo = in.readInt();
        }
        namaToDo = in.readString();
        tanggalToDoStart = in.readString();
        jamToDoStart = in.readString();
        tanggalToDoFinal = in.readString();
        jamToDoFinal = in.readString();
        status = in.readString();
    }

    public static final Creator<ToDoClass> CREATOR = new Creator<ToDoClass>() {
        @Override
        public ToDoClass createFromParcel(Parcel in) {
            return new ToDoClass(in);
        }

        @Override
        public ToDoClass[] newArray(int size) {
            return new ToDoClass[size];
        }
    };

    public Integer getIdToDo() {
        return idToDo;
    }

    public void setIdToDo(Integer idToDo) {
        this.idToDo = idToDo;
    }

    public String getNamaToDo() {
        return namaToDo;
    }

    public void setNamaToDo(String namaToDo) {
        this.namaToDo = namaToDo;
    }

    public String getTanggalToDoStart() {
        return tanggalToDoStart;
    }

    public void setTanggalToDoStart(String tanggalToDoStart) {
        this.tanggalToDoStart = tanggalToDoStart;
    }

    public String getJamToDoStart() {
        return jamToDoStart;
    }

    public void setJamToDoStart(String jamToDoStart) {
        this.jamToDoStart = jamToDoStart;
    }

    public String getTanggalToDoFinal() {
        return tanggalToDoFinal;
    }

    public void setTanggalToDoFinal(String tanggalToDoFinal) {
        this.tanggalToDoFinal = tanggalToDoFinal;
    }

    public String getJamToDoFinal() {
        return jamToDoFinal;
    }

    public void setJamToDoFinal(String jamToDoFinal) {
        this.jamToDoFinal = jamToDoFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idToDo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idToDo);
        }
        dest.writeString(namaToDo);
        dest.writeString(tanggalToDoStart);
        dest.writeString(jamToDoStart);
        dest.writeString(tanggalToDoFinal);
        dest.writeString(jamToDoFinal);
        dest.writeString(status);
    }
}
