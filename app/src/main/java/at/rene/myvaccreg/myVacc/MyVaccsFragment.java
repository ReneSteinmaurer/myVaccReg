package at.rene.myvaccreg.myVacc;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.VaccinesMemo;
import at.rene.myvaccreg.data.VaccinesMemoDAO;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyVaccsFragment extends Fragment {
    private FloatingActionButton addVaccines;
    private LinearLayout vaccines;
    private AppCompatButton displayFood;

    private VaccinesMemoDAO dataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_vaccs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        addVaccines = getActivity().findViewById(R.id.add_button);
        vaccines = getActivity().findViewById(R.id.myVaccsList);

        displayUsersVaccines();

        VaccinesMemo testMemo = new VaccinesMemo("Covid 19", "Pfizer", "02-12-2021");
        Log.d("LOG_TAG", "Inhalt der Testmemo: " + testMemo.toString());

        dataSource = new VaccinesMemoDAO(getActivity());


        addVaccines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Buttons und Views hinzufügen
    }

    public void onAddVaccine(View view) {

    }

    public void displayUsersVaccines() {
        VaccinesMemoDAO vaccinesMemoDAO = new VaccinesMemoDAO(getActivity());
        String[] data;

        /*vaccinesMemoDAO.insertData("Pfizer","Vaccine against the corona virus", "12-02-2020");
        vaccinesMemoDAO.insertData("Moderna","Vaccine against the corona virus", "12-02-2020");
        vaccinesMemoDAO.insertData("Astrazenica","Vaccine against the corona virus", "12-02-2020");

        vaccinesMemoDAO.insertData("Covid19", "Die Erkrankung, die das Virus hervorruft, wird als COVID-19 bezeichnet. Die Beschwerden betreffen nicht nur - wie ursprünglich angenommen - die Atemwege, es ist ein breites Spektrum an Symptomen möglich.", "19-10-2020");
        vaccinesMemoDAO.insertData("Anthrax", "Vaxchora, Anthrax (Milzbrand) ist primär eine Erkrankung von Huftieren wie Rindern oder Schafen, die durch das Bakterium Bacillus anthracis verursacht wird. Dieses bildet Sporen, die unter günstigen Bedingungen mehrere Jahrzehnte in der Umwelt überleben können.", "30-10-2016");
        vaccinesMemoDAO.insertData("Cholera", "AVA, Cholera ist eine Infektionskrankheit, die durch den Erreger Vibrio cholerae hervorgerufen wird. Dieser führt zu schweren Durchfällen und erfordert eine rasche Behandlung. Cholera zählt zu den meldepflichtigen Infektionskrankheiten.", "06-07-2002");
        vaccinesMemoDAO.insertData("HepatitisA", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("HepatitisB", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("Covid19", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("AnthraxB", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("CholeraB", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("HepatitisA", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");
        vaccinesMemoDAO.insertData("HepatitisC", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006");*/

        Log.d("Vaccines", vaccinesMemoDAO.getData());

        // Speichert die Daten in ein String-Array
        data = vaccinesMemoDAO.getData().split("\n");

        for (int i = 0; i < data.length; i++) {

            // Dient dazu aus dem String den Namen der Impfung herauszunehmen
            data[i] = data[i].substring(0, data[i].indexOf(" "));


            displayFood = new AppCompatButton(getActivity());

            displayFood.setGravity(View.TEXT_ALIGNMENT_CENTER);

            displayFood.setText(data[i]);
            displayFood.setTextSize(18);
            displayFood.setHeight(160);
            displayFood.setBackgroundResource(R.drawable.add_button_blue);
            displayFood.setTextColor(Color.WHITE);
            displayFood.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            displayFood.setTop(10);

            displayFood.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });

            vaccines.addView(displayFood);
            vaccines.invalidate();

        }
    }
}