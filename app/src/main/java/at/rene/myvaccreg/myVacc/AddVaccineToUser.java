package at.rene.myvaccreg.myVacc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddVaccineToUser extends Fragment {
    private RecyclerView recyclerView;
    private AppCompatButton displayVaccine;
    private AllVaccsAdpater allVaccsAdapter;
    private MyVaccRegDb db;
    private List<VaccinationRoom> allVaccs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vaccine_to_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());

        recyclerView = getActivity().findViewById(R.id.allVaccsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        allVaccs = db.vaccinationDao().getAllVaccines();

        allVaccsAdapter = new AllVaccsAdpater(getActivity(), allVaccs);
        recyclerView.setAdapter(allVaccsAdapter);


        // Buttons und Views hinzufügen
    }
}