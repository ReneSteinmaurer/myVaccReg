package at.rene.myvaccreg.myVacc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.VaccinesMemoDAO;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddNewVaccination extends Fragment {
    private ExtendedFloatingActionButton addVaccine;
    private EditText name;
    private EditText desc;
    private EditText date;
    private EditText manufacturer;
    private EditText illness;
    private VaccinesMemoDAO vaccinesMemoDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_new_vaccination, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Buttons und Views hinzufügen


        name = getActivity().findViewById(R.id.editTextVaccineName);
        desc = getActivity().findViewById(R.id.editTextDescVaccine);
        date = getActivity().findViewById(R.id.editTextDateVaccine);
        illness = getActivity().findViewById(R.id.editTextIllnessVaccine);
        manufacturer = getActivity().findViewById(R.id.editTextManufacturerVaccine);

        addVaccine = getActivity().findViewById(R.id.addNewVaccineBtn);


        addVaccine.setOnClickListener(v -> {
            // TODO: Impfstoff in der DB anlegen
            if (isFilled()) {
                vaccinesMemoDAO.insertData(name.getText().toString(), desc.getText().toString(),
                        date.getText().toString(), illness.getText().toString(),
                        manufacturer.getText().toString());
            } else {
                Toast.makeText(getActivity(),"Felder ausfüllen!", Toast.LENGTH_LONG);
            }

        });

        vaccinesMemoDAO = new VaccinesMemoDAO(getActivity());

    }

    private boolean isFilled() {
        boolean isFilled = false;
        if (!name.getText().toString().equals("") && !desc.getText().toString().equals("")
                && !illness.getText().toString().equals("") && !date.toString().equals("")
                && !manufacturer.getText().toString().equals("")) {
            isFilled = true;
        }

        return isFilled;
    }
}