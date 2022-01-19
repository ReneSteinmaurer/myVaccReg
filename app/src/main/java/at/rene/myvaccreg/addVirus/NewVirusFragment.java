package at.rene.myvaccreg.addVirus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.Virus;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewVirusFragment extends Fragment {
    private ExtendedFloatingActionButton addVirusBtn;
    private EditText name;
    private EditText desc;
    private EditText category;
    private TextView errorMsg;

    private MyVaccRegDb db;
    private List<Virus> virusList;
    private boolean exists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_virus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        exists = false;
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());

        virusList = db.virusDao().getAllViruses();

        // Buttons und Views hinzufügen
        addVirusBtn = getActivity().findViewById(R.id.addNewVirusBtn);
        name = getActivity().findViewById(R.id.editTextVirusName);
        desc = getActivity().findViewById(R.id.editTextVirusDesc);
        category = getActivity().findViewById(R.id.editTextVirusCategory);
        errorMsg = getActivity().findViewById(R.id.virusErrorMsg);
        errorMsg.setVisibility(View.INVISIBLE);

        /**
         * Wenn alles ausgefüllt ist wird ein neuer Virus in der Datenbank gespeichert
         */
        addVirusBtn.setOnClickListener(v -> {
            Log.i("Virus", "onClick Event");

            if (isFilled()) {
                Virus virus = new Virus();
                virus.name = name.getText().toString();
                virus.desc = desc.getText().toString();
                virus.category = category.getText().toString();

                for (Virus selectedVirus : virusList) {
                    if (selectedVirus.getName().equals(virus.getName()))
                        exists = true;
                }

                if (!exists) {
                    db.virusDao().insertVirus(virus);
                    clearFields();
                    errorMsg.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.i("Virus", "Virus nicht angelegt!");
                    // TODO: wenn Virus schon vorhanden dann -->
                    errorMsg.setText("Virus schon vorhanden!");
                    errorMsg.setVisibility(View.VISIBLE);
                }
            } else {
                errorMsg.setText("Alle Felder müssen ausgefüllt sein!");
                errorMsg.setVisibility(View.VISIBLE);
            }

        });


    }

    private void clearFields() {
        name.setText("");
        desc.setText("");
        category.setText("");
    }

    /**
     * Überprüft ob alle Felder ausgefüllt sind, wenn ja wird true zurückgeliefert
     * @return
     */
    private boolean isFilled() {
        boolean ret = false;
        if (!name.getText().toString().equals("") && !desc.getText().toString().equals("") &&
            !category.getText().toString().equals("")) ret = true;
        return ret;
    }
}