package at.rene.myvaccreg.addVacc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;
import at.rene.myvaccreg.roomdb.Virus;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddNewVaccination extends Fragment implements AdapterView.OnItemSelectedListener {
    private ExtendedFloatingActionButton addVaccine;
    private EditText name;
    private EditText desc;
    private EditText date;
    private EditText manufacturer;
    private ArrayAdapter aa;
    private TextView errorView;
    private DatePickerDialog picker;
    private Spinner chooseVirusSpinner;
    private MyVaccRegDb db;
    private List<Virus> allViruses;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_new_vaccination, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        // löscht die gesetzten Daten
        clearEditText();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String[] allVirusesNames;
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());
        allViruses = db.virusDao().getAllViruses();

        // erstellt Array mit allen Virus-Namen
        allVirusesNames = new String[allViruses.size()];

        for (int i = 0; i < allViruses.size(); i++) {
            allVirusesNames[i] = allViruses.get(i).getName();
        }

        // Buttons und Views hinzufügen
        name = getActivity().findViewById(R.id.editTextVaccineName);
        desc = getActivity().findViewById(R.id.editTextDescVaccine);
        date = getActivity().findViewById(R.id.editTextDateVaccine);
        date.setInputType(InputType.TYPE_NULL);
        manufacturer = getActivity().findViewById(R.id.editTextManufacturerVaccine);
        chooseVirusSpinner = getActivity().findViewById(R.id.spinnerViruses);
        errorView = getActivity().findViewById(R.id.errorTextview);
        errorView.setVisibility(View.INVISIBLE);

        // setzt die VirusSpinner onClick()
        chooseVirusSpinner.setOnItemSelectedListener(this);

        // setzt den Array-Adapter für die Dropdown-Liste
        aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, allVirusesNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        chooseVirusSpinner.setAdapter(aa);


        addVaccine = getActivity().findViewById(R.id.addNewVaccineBtn);

        clearEditText();

        Log.i("VACC", "INITIAL");

        /**
         * OnClick Listener für das date-Feld, es wird der Datepicker aufgerufen und übergibt
         * die Daten in das Edittext mit einer bestimmten Formatierung
         */
        date.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            // date picker dialog
            picker = new DatePickerDialog(getActivity(),
                    (view1, year1, monthOfYear, dayOfMonth) ->
                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1), year, month, day);
            picker.show();
        });

        addVaccine.setOnClickListener(v -> {
            if (isFilled()) {
                saveNewVaccine(name.getText().toString(), desc.getText().toString(),
                        date.getText().toString(), manufacturer.getText().toString());

                errorView.setVisibility(View.INVISIBLE);
            } else {
                errorView.setVisibility(View.VISIBLE);
                Log.i("VACC", "ERROR");
            }
        });
    }


    /**
     * Dient zur Speicherung der neuen Impfung, aus den Attributen wird eine neue Impfung erstellt
     * und diese wird in der Datenbank gespeichert.
     * @param name
     * @param desc
     * @param renewDate
     * @param manufacturer
     */
    private void saveNewVaccine(String name, String desc, String renewDate, String manufacturer) {
        boolean exists = false;
        MyVaccRegDb myVaccRegDb = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());
        List<VaccinationRoom> vaccinationList = new ArrayList<>();

        VaccinationRoom vaccination = new VaccinationRoom();
        vaccination.name = name;
        vaccination.desc = desc;
        vaccination.renewDate = renewDate;
        vaccination.virus = chooseVirusSpinner.getSelectedItem().toString();
        vaccination.manufacturer = manufacturer;

        vaccinationList = myVaccRegDb.vaccinationDao().getAllVaccines();

        // Überprüft ob die Impfung schon vorhanden ist
        for (VaccinationRoom v : vaccinationList) {
            Log.i("VACC", v.name);
            Log.i("VACC", v.desc);
            if (v.getName().equals(vaccination.getName()))
               exists = true;
        }

        // Nicht vorhanden --> erstellt
        if (!exists) {
            clearEditText();
            myVaccRegDb.vaccinationDao().insertVaccine(vaccination);
        }
    }

    /**
     * Überprüft ob alle Felder ausgefüllt sind, ist dies nicht der Fall bekommt man false zurück
     * @return
     */
    private boolean isFilled() {
        boolean isFilled = false;
        if (!name.getText().toString().equals("") && !desc.getText().toString().equals("") && !date.toString().equals("")
                && !manufacturer.getText().toString().equals("") && chooseVirusSpinner.getSelectedItem() != null) {
            isFilled = true;
        }

        return isFilled;
    }

    /**
     * Löscht alle Eingaben des Users
     */
    private void clearEditText() {
        name.setText("");
        desc.setText("");
        date.setText("");
        manufacturer.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}