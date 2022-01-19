package at.rene.myvaccreg.myVacc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.allVaccs.AddVaccineToUser;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;
import at.rene.myvaccreg.roomdb.VaccinationUser;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyVaccsFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyVaccAdapter myVaccAdapter;
    private FloatingActionButton addVaccines;
    private ImageView deleteImg;

    private AddVaccineToUser addVaccToUserf;
    private List<VaccinationUser> userVaccs;
    private List<VaccinationRoom> allVaccs;
    private List<VaccinationRoom> displayedVaccs;

    private MyVaccRegDb db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_vaccs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        userVaccs = new ArrayList<>();
        allVaccs = new ArrayList<>();
        displayedVaccs = new ArrayList<>();
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());


        addVaccines = getActivity().findViewById(R.id.add_button);
        recyclerView = getActivity().findViewById(R.id.myVaccsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addVaccToUserf = new AddVaccineToUser();

        userVaccs = db.vaccinationUserDao().getAllVaccinations();
        allVaccs = db.vaccinationDao().getAllVaccines();

        for (VaccinationRoom v : allVaccs) {
            for (VaccinationUser u : userVaccs) {
                if (v.getName().equals(u.getVaccination())) {
                    displayedVaccs.add(v);
                    v.setDate(u.getDate());
                }
            }
        }


        myVaccAdapter = new MyVaccAdapter(getActivity(), displayedVaccs);
        recyclerView.setAdapter(myVaccAdapter);

        addVaccines.setOnClickListener(v ->
                getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragmentView, addVaccToUserf)
                .addToBackStack(null)
                .commit());

        // Buttons und Views hinzufügen
    }

    public void onAddVaccine(View view) {

    }
}