package at.rene.myvaccreg.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VaccinationUserDao {

    @Query("select * from vaccinationuser")
    List<VaccinationUser> getAllVaccinations();

    @Query("delete from vaccinationuser where vaccine=:userInput")
    void deleteVacc(String userInput);

    @Insert
    void addVaccination(VaccinationUser vaccine);

    @Delete
    void delete(VaccinationUser vaccine);
}
