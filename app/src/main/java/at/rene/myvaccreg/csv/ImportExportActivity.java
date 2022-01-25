package at.rene.myvaccreg.csv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class ImportExportActivity extends AppCompatActivity {
    private MyVaccRegDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_export);
        db = MyVaccRegDb.getDbInstance(getApplicationContext());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    importCSV();
                }
                break;
            case 20:
                if (resultCode == RESULT_OK) {
                    try {
                        exportCSV();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    void exportCSV() throws FileNotFoundException {
        FileOutputStream fos = null;
        String csvHeader = "Impfstoff;Virus;Letzte Impfung;Auffrischungsimpfung\n";
        String entry;

        //Write Vaccination CSV File
        try {
            fos = this.openFileOutput("vaccines.csv", this.MODE_PRIVATE);


            // getVaccinations(mPreferences.getInt(SHOW_VACCINATION_TYPE, 4));

            fos.write(csvHeader.getBytes());

            List<VaccinationRoom> vaccinations = new ArrayList<>();

            vaccinations = db.vaccinationDao().getAllVaccines();

            for (VaccinationRoom vaccination : vaccinations) {
                entry = vaccination.getName() + ";" + vaccination.getVirus()
                        + ";" + vaccination.getDate() + "\n";
                fos.write(entry.getBytes());
                fos.flush();
            }

            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void importCSV() {
        ArrayList<VaccinationRoom> vaccinationList = new ArrayList<>();

        try {
            InputStream inputStream = this.openFileInput("vaccines.csv");

            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream)).withCSVParser(csvParser).build();

            String[] line;
            if (csvReader.readNext() != null) {
                while ((line = csvReader.readNext()) != null) {

                    VaccinationRoom vaccine = new VaccinationRoom();

                    vaccine.name = line[0];
                    vaccine.virus = line[1];
                    vaccine.date = line[2];
                    vaccine.renewDate = line[3];

                    db.vaccinationDao().insertVaccine(vaccine);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onExport(View view) {
        Intent exportIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        exportIntent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(exportIntent, 20);
    }

    public void onImport(View view) {
        Intent importintent = new Intent(Intent.ACTION_GET_CONTENT);
        importintent.setType("*/*");
        startActivityForResult(importintent, 10);
    }
}