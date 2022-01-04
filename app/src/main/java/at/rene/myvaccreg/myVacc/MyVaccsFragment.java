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

        for (int i = 0; i < 100; i++) {
            displayFood = new AppCompatButton(getActivity());

            displayFood.setGravity(View.TEXT_ALIGNMENT_CENTER);

            displayFood.setText("test");
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