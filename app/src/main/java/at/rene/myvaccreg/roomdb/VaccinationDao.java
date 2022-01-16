package at.rene.myvaccreg.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VaccinationDao {

    @Query("select * from vaccinationroom")
    List<VaccinationRoom> getAllVaccines();

    @Insert
    void insertVaccine(VaccinationRoom... vaccine);

    @Delete
    void delete(VaccinationRoom vaccine);

}
