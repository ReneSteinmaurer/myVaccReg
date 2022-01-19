package at.rene.myvaccreg.myVacc;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.addVacc.AddNewVaccination;
import at.rene.myvaccreg.addVirus.DisplayVirusesFragment;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class MainActivity extends AppCompatActivity {
    private MainWindowFragment mainWindowFragment;
    private AddNewVaccination addNewVaccination;
    private MyVaccsFragment myVaccsFragment;
    private DisplayVirusesFragment displayVirusesFragment;
    private LinearLayout vaccines;
    private MyVaccRegDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWindowFragment = new MainWindowFragment();
        myVaccsFragment = new MyVaccsFragment();
        addNewVaccination = new AddNewVaccination();
        displayVirusesFragment = new DisplayVirusesFragment();

        vaccines = findViewById(R.id.myVaccsRecyclerView);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, mainWindowFragment)
                .commit();
    }

    /**
     * Öffnet das MyVaccFragment, dort werden alle Impfungen des Users angezeigt
     * @param view
     */
    public void onMyVacc(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, myVaccsFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Exportiert oder Importiert Daten mithilfe einer CSV-Datei
     * @param view
     */
    public void onImpExpVacc(View view) {

    }

    /**
     * Öffnet das AddNewVaccination Fragment, mit diesem kann man eine neue Impfung anlegen
     * @param view
     */
    public void onAddVacc(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, addNewVaccination)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Öffnet das DisplayVirusesFragment, mit diesem kann man einen neuen Virus anlegen.
     * Bereits angelegte Viren werden auch angezeigt
     * @param view
     */
    public void onAddVirus(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, displayVirusesFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Speichert Test-Daten in die Datenbank
     * @param view
     */
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
