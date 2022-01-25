package at.rene.myvaccreg.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.myVacc.MyVaccAdapter;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    private Switch onlyNewestVaccs;
    private EditText csvName;
    private Button saveName;
    private RecyclerView recyclerView;
    private MyVaccAdapter myVaccAdapter;

    private List<VaccinationRoom> allVaccs;
    private MyVaccRegDb db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());

        SharedPreferences sharedPref = getActivity().getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Buttons und Views hinzufügen
        onlyNewestVaccs = getActivity().findViewById(R.id.showNewestVaccSwitch);
        csvName = getActivity().findViewById(R.id.editCsvName);
        saveName = getActivity().findViewById(R.id.csvNameSaveButton);

        csvName.setText("vaccines.csv");

        onlyNewestVaccs.setOnClickListener(v -> {
            if (onlyNewestVaccs.isChecked())
                editor.putBoolean(getString(R.string.saved_only_latest_vaccs), true);
            else editor.putBoolean(getString(R.string.saved_only_latest_vaccs), false);

            editor.apply();
        });

        saveName.setOnClickListener(v -> {
            if (csvName.isEnabled()) csvName.setEnabled(false);
            else csvName.setEnabled(true);

            editor.putString(getString(R.string.saved_csv_name), csvName.getText().toString());
            editor.apply();
        });


    }
}