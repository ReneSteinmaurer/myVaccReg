package at.rene.myvaccreg.myVacc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.addVirus.DisplayVirusesFragment;
import at.rene.myvaccreg.data.Vaccination;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class MainActivity extends AppCompatActivity {
    private MainWindowFragment mainWindowFragment;
    private AddNewVaccination addNewVaccination;
    private MyVaccsFragment myVaccsFragment;
    private DisplayVirusesFragment displayVirusesFragment;
    private LinearLayout vaccines;
    private Intent mainIntent;
    private Intent intent;
    private Vaccination vaccClass;
    private ArrayList<Vaccination> vaccinations;
    private AppCompatButton displayFood;
    private MyVaccRegDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mainIntent = new Intent(this, MainActivity.class);
        //vaccClass = new Vaccination();
        //vaccinations = vaccClass.initVaccines();

        mainWindowFragment = new MainWindowFragment();
        myVaccsFragment = new MyVaccsFragment();
        addNewVaccination = new AddNewVaccination();
        displayVirusesFragment = new DisplayVirusesFragment();

        vaccines = findViewById(R.id.myVaccsRecyclerView);

        // Log.d("MainActivity: ","Array index(6): "+vaccinations.get(6));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, mainWindowFragment)
                .commit();
    }

    /**
     * Übergibt die Daten an die MyVacc Klasse
     *
     * @param view
     */
    public void onMyVacc(View view) {
        /*intent = new Intent(this, MyVacc.class);
        intent.putExtra("VaccinationList", vaccinations);

        startActivity(intent);*/

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, myVaccsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onImpExpVacc(View view) {

    }

    public void onAddVacc(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, addNewVaccination)
                .addToBackStack(null)
                .commit();
    }

    public void onAddVirus(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, displayVirusesFragment)
                .addToBackStack(null)
                .commit();
    }

    public void onTestData(View view) {
        db = MyVaccRegDb.getDbInstance(getApplicationContext());
        if (db.vaccinationDao().getAllVaccines().isEmpty()) {
            Toast.makeText(this, "Daten erfolgreich geladen!", Toast.LENGTH_SHORT).show();

            VaccinationRoom covid19 = new VaccinationRoom("Covid 19-Pfizer","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Biontech Pfizer");

            VaccinationRoom covid191 = new VaccinationRoom("Covid 19-Moderna","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Moderna");

            VaccinationRoom covid192 = new VaccinationRoom("Covid 19-Sputnik","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Sputnik");

            VaccinationRoom covid193 = new VaccinationRoom("Covid 19-Astrazenica","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Astrazenica");

            VaccinationRoom ebola = new VaccinationRoom("rVSV-ZEBOV","Die Ebolaviren verursachen das Ebolafieber. Neben dem Menschen infizieren sie andere Primaten und lösen bei ihnen ein hämorrhagisches Fieber aus.",
                    " ", "9-8-2026", "Ebola","Bavarian Nordic");

            db.vaccinationDao().insertVaccine(covid19);
            db.vaccinationDao().insertVaccine(covid191);
            db.vaccinationDao().insertVaccine(covid192);
            db.vaccinationDao().insertVaccine(covid193);
            db.vaccinationDao().insertVaccine(ebola);
        } else Toast.makeText(this, "Laden fehlgeschlagen, bereits vorhanden", Toast.LENGTH_SHORT).show();


    }
}
