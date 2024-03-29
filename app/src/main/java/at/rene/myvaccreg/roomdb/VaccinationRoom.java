package at.rene.myvaccreg.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VaccinationRoom {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "renew_date")
    public String renewDate;

    @ColumnInfo(name = "virus")
    public String virus;

    @ColumnInfo(name = "manufacturer")
    public String manufacturer;

    public VaccinationRoom () {
        this ("","","","","","");
    }

    public VaccinationRoom(String name, String desc, String date, String renewDate, String virus, String manufacturer) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.renewDate = renewDate;
        this.virus = virus;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRenewDate() {
        return renewDate;
    }

    public void setRenewDate(String renewDate) {
        this.renewDate = renewDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVirus() {
        return virus;
    }

    public void setVirus(String virus) {
        this.virus = virus;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
