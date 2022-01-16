package at.rene.myvaccreg.myVacc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import at.rene.myvaccreg.ImpExpVacc;
import at.rene.myvaccreg.R;
import at.rene.myvaccreg.addVirus.DisplayVirusesFragment;
import at.rene.myvaccreg.data.Vaccination;

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
        startActivity(new Intent(this, ImpExpVacc.class));
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
}
