package at.rene.myvaccreg.allVaccs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    private EditText searchText;
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
        searchText = getActivity().findViewById(R.id.editTextSearch);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        allVaccs = db.vaccinationDao().getAllVaccines();

        allVaccsAdapter = new AllVaccsAdpater(getActivity(), allVaccs);
        recyclerView.setAdapter(allVaccsAdapter);


        // Buttons und Views hinzufügen
    }

    /**
     * Übernimmt die Funktion der Suche, der Algorithmus schaut ob die namen übereinstimmen,
     * wenn ja, stellt die Funktion eine Liste aus der Infrage Stehenden Impfungen zusammen.
     * Diese Liste wird dem Adapter mitgegeben.
     * @param text
     */
    private void filter(String text) {
        List<VaccinationRoom> filteredList = new ArrayList<>();

        for (VaccinationRoom v : allVaccs) {
            if (v.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(v);
            }
        }

        allVaccsAdapter.filteredList(filteredList);
    }
}