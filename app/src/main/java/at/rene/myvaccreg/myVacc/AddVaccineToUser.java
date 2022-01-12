package at.rene.myvaccreg.myVacc;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.UserMemoDAO;
import at.rene.myvaccreg.data.VaccinesMemoDAO;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddVaccineToUser extends Fragment {
    private LinearLayout allVaccinesLayout;
    private AppCompatButton displayVaccine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vaccine_to_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        allVaccinesLayout = getActivity().findViewById(R.id.allVaccinesList);


        displayAllVaccines();


        // Buttons und Views hinzufügen
    }

    /**
     * Zeigt die Impfungen des Benutzers an
     */

    private void displayAllVaccines() {
        VaccinesMemoDAO vaccinesMemoDAO = new VaccinesMemoDAO(getActivity());
        UserMemoDAO userMemoDAO = new UserMemoDAO(getActivity());
        String[] data;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formatter.format(date));

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


        Log.i("All Vaccines", vaccinesMemoDAO.getData());

        // Speichert die Daten in ein String-Array
        data = vaccinesMemoDAO.getData().split("\n");

        for (int i = 0; i < data.length; i++) {
            String[] partialData;

            // Dient dazu aus dem String den Namen der Impfung herauszunehmen
            partialData = data[i].split(";");
            String vaccine = partialData[0];


            displayVaccine = new AppCompatButton(getActivity());

            displayVaccine.setGravity(View.TEXT_ALIGNMENT_CENTER);

            displayVaccine.setText(vaccine);
            displayVaccine.setTextSize(18);
            displayVaccine.setHeight(160);
            displayVaccine.setBackgroundResource(R.drawable.add_button_blue);
            displayVaccine.setTextColor(Color.WHITE);
            displayVaccine.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            displayVaccine.setTop(10);

            displayVaccine.setOnClickListener(new View.OnClickListener() {

                // TODO: Error in der OnClick --> wird nicht ausgeführt
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clicked!",Toast.LENGTH_LONG);
                    boolean isSaveable = true;
                    String[] userVaccines = userMemoDAO.getData().split("\n");
                    String[] vaccines;

                    for (int i = 0; i < userVaccines.length; i++) {
                        vaccines = userVaccines[i].split(";");

                        if (vaccine.equals(vaccines[0])) {
                            Toast.makeText(getActivity(), "Vaccine is already in the user-table",Toast.LENGTH_SHORT);
                        } else {
                            isSaveable = false;
                        }
                    }
                    if (isSaveable) userMemoDAO.insertData("Daniel",vaccine,formatter.format(date));
                }
            });

            allVaccinesLayout.addView(displayVaccine);
            allVaccinesLayout.invalidate();

        }
    }
}