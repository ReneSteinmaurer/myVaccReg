package at.rene.myvaccreg.myVacc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import at.rene.myvaccreg.AddVacc;
import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.Vaccination;
import at.rene.myvaccreg.data.VaccinationAdapterRecycleView;

public class MyVacc extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Intent intent;
    private int listSize;
    private Vaccination vaccClass;
    private ArrayList<Vaccination> vaccinationArrayList;
    private ArrayList<Vaccination> shownVaccList;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vacc);
        shownVaccList = new ArrayList<>();

        addBtn = findViewById(R.id.add_button);
        recyclerView = (RecyclerView) findViewById(R.id.vaccView);

        Log.d("MyVacc: ", "onCreate()");
        shownVaccList = initVaccs();
        displayData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        /*initVaccs();
        Log.d("MyVacc: ", "Called Method onResume()");
        displayData();*/
    }

    private void displayData() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new VaccinationAdapterRecycleView(shownVaccList, R.layout.item_vaccine_myvacc);
        recyclerView.setAdapter(adapter);
        for (int i = 0; i < shownVaccList.size(); i++) {
            adapter.notifyItemChanged(i);
            System.out.println(shownVaccList.get(i).getName());
        }

    }

    private ArrayList<Vaccination> initVaccs() {

        // Bekommt die Daten von der letzten Klasse
        vaccinationArrayList = new ArrayList<>();
        vaccinationArrayList = (ArrayList<Vaccination>) getIntent().getSerializableExtra("VaccinationList");

        for (Vaccination vac : vaccinationArrayList) {
            if (vac.isActive()) shownVaccList.add(vac);

            Log.d("MyVacc","Vaccine: "+vac.getName() +", Active: " +vac.isActive());
        }

        return shownVaccList;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * Übergibt die Daten an die AddVacc Klasse
     * @param view
     */
    public void onAddVaccine(View view) {
        intent = new Intent(this, AddVacc.class);
        intent.putExtra("VaccinationList", vaccinationArrayList);

        startActivity(intent);
        finish();
    }
}