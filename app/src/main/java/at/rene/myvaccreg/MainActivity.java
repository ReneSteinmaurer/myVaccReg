package at.rene.myvaccreg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import at.rene.myvaccreg.data.Vaccination;

public class MainActivity extends AppCompatActivity {
    private Intent mainIntent;
    private Intent intent;
    private Vaccination vaccClass;
    private ArrayList<Vaccination> vaccinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainIntent = new Intent(this, MainActivity.class);
        vaccClass = new Vaccination();
        vaccinations = vaccClass.initVaccines();

        for (int i = 0; i < vaccinations.size(); i++) {
            System.out.println(vaccinations.get(i).toString());
        }

        Log.d("MainActivity: ","Array index(6): "+vaccinations.get(6));

    }

    /**
     * Übergibt die Daten an die MyVacc Klasse
     * @param view
     */
    public void onMyVacc(View view) {
        intent = new Intent(this, MyVacc.class);
        intent.putExtra("VaccinationList", vaccinations);

        startActivity(intent);
    }

    public void onImpExpVacc(View view) {
        startActivity(new Intent(this, ImpExpVacc.class));
    }

    public void onAddVacc(View view) {
        startActivity(new Intent(this, CreateVacc.class));
    }

    public void onAddVirus(View view) {
        startActivity(new Intent(this, AddVirus.class));
    }
}