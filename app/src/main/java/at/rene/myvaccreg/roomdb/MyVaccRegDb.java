package at.rene.myvaccreg.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {VaccinationRoom.class, VaccinationUser.class, Virus.class}, version = 27)

public abstract class MyVaccRegDb extends RoomDatabase {

    public abstract VaccinationDao vaccinationDao();

    public abstract VaccinationUserDao vaccinationUserDao();

    public abstract VirusDao virusDao();

    private static MyVaccRegDb INSTANCE;

    public static MyVaccRegDb getDbInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyVaccRegDb.class, "MY_VACC_REG")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
