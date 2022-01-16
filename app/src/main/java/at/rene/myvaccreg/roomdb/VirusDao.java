package at.rene.myvaccreg.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VirusDao {

    @Query("select * from virus")
    List<Virus> getAllViruses();

    @Insert
    void insertVirus(Virus... virus);

    @Delete
    void delete(Virus virus);
}
