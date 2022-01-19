package at.rene.myvaccreg.myVacc;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.addVacc.AddNewVaccination;
import at.rene.myvaccreg.addVirus.DisplayVirusesFragment;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;
import at.rene.myvaccreg.roomdb.Virus;

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
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
                    " ", "6-12-2022", "SARS-CoV-2","Biontech");

            VaccinationRoom covid191 = new VaccinationRoom("Covid 19-Moderna","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Moderna");

            VaccinationRoom covid192 = new VaccinationRoom("Covid 19-Sputnik","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Sputnik");

            VaccinationRoom covid193 = new VaccinationRoom("Covid 19-Astrazenica","COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    " ", "6-12-2022", "SARS-CoV-2","Astrazenica");

            VaccinationRoom ebola = new VaccinationRoom("rVSV-ZEBOV","Die Ebolaviren verursachen das Ebolafieber. Neben dem Menschen infizieren sie andere Primaten und lösen bei ihnen ein hämorrhagisches Fieber aus.",
                    " ", "9-8-2026", "Ebola","Bavarian Nordic");

            VaccinationRoom herpesV = new VaccinationRoom("Herpes zoster","Herpes zoster (Gürtelrose) ist eine Viruserkrankung, die bei Menschen auftritt, die zuvor an Windpocken erkrankt sind. Das Windpocken-Virus (Varicella-zoster-Virus) kann im Körper jahrelang überleben. Wird es reaktiviert, entsteht eine Gürtelrose",
                    " ", "21-5-2034", "Herpesviridae","Biontech");

            VaccinationRoom influenzaV = new VaccinationRoom("Influenza Spaltimpfstoff","Die Ständige Impfkommission (STIKO) empfiehlt die jährliche Impfung gegen Grippe für alle, die ein erhöhtes Risiko haben, besonders schwer zu erkranken. Hierzu gehören Menschen ab 60 Jahre, chronisch Kranke jeden Alters, Schwangere sowie Bewohner von Alten- und Pflegeheimen.",
                    " ", "10-9-2023", "Saisionale Grippe","Bavarian Nordic");

            VaccinationRoom masernV = new VaccinationRoom("Masern Impfung","Die Maser-Mumps-Röteln-Impfung ist ein Lebendimpfstoff. Nach zweimaliger Impfung besteht eine lang anhaltende Immunität. ",
                    " ", "3-4-2030", "Masern","A.C.A. Müller ADAG Pharma AG");



            Virus covid = new Virus("Covid 19", "COVID-19 ist eine meldepflichtige Infektionskrankheit. Sie wird verursacht vom Coronavirus SARS-CoV-2 und hat ein breites, unspezifisches Symptomspektrum.",
                    "SARS-CoV");

            Virus herpes = new Virus("Herpes-simplex",
                    "Die Herpes-simplex-Viren verursachen sehr verschiedene Erkrankungen des Herpes simplex," +
                            " darunter die sogenannte Mundfäule bei Kleinkindern. Die häufigsten Symptome einer" +
                            " Infektion dieser Viren sind jedoch Lippenherpes (Herpes labialis) und Genitalherpes (Herpes genitalis). ",
                    "Herpesviridae");

            Virus influenza = new Virus("Saisionale Grippe", "Klinisch ist ein plötzlicher Beginn " +
                    "mit hohem Fieber und starkem subjektiven Krankheitsgefühl bei nur gering erhöhten Entzündungszeichen typisch. " +
                    "Hohe Entzündungswerte und produktiver Husten können Hinweise auf eine bakterielle" +
                    " Superinfektion sein (meist durch Staphylococcus aureus). Therapeutisch stehen mit den Neuraminidase-Hemmern Oseltamivir und Zanamivir zwei Medikamente zur kausalen Behandlung der Influenza zur Verfügung. ",
                    "Influenza");

            Virus hiv = new Virus("HIV", "Das Humane Immundefizienz-Virus, zumeist abgekürzt als HIV oder auch bezeichnet als Menschliches Immunschwäche-Virus oder Menschliches Immundefekt-Virus, ist ein behülltes Virus, das zur Familie der Retroviren und zur Gattung der Lentiviren gehört. ",
                    "Retroviren");

            Virus masern = new Virus("Masern", "Masern werden durch Viren ausgelöst und kommen weltweit vor. Sie sind hoch ansteckend. Eine Masern-Infektion ist keine harmlose Krankheit, denn bei etwa jedem zehnten Betroffenen treten Komplikationen auf. In Deutschland ist die Häufigkeit von Masern-Erkrankungen durch Impfungen stark zurückgegangen. Trotzdem kommt es immer wieder zu Häufungen von Krankheitsfällen bei ungeschützten Personen. ",
                    "Paramyxoviren");


            db.vaccinationDao().insertVaccine(covid19);
            db.vaccinationDao().insertVaccine(covid191);
            db.vaccinationDao().insertVaccine(covid192);
            db.vaccinationDao().insertVaccine(covid193);
            db.vaccinationDao().insertVaccine(ebola);
            db.vaccinationDao().insertVaccine(herpesV);
            db.vaccinationDao().insertVaccine(influenzaV);
            db.vaccinationDao().insertVaccine(masernV);

            db.virusDao().insertVirus(covid);
            db.virusDao().insertVirus(herpes);
            db.virusDao().insertVirus(influenza);
            db.virusDao().insertVirus(hiv);
            db.virusDao().insertVirus(masern);
        } else Toast.makeText(this, "Laden fehlgeschlagen, bereits vorhanden", Toast.LENGTH_SHORT).show();


    }
}
