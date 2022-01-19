package at.rene.myvaccreg.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VaccinationUser {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "vaccination")
    public String vaccination;

    @ColumnInfo(name = "date")
    public String date;


    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
