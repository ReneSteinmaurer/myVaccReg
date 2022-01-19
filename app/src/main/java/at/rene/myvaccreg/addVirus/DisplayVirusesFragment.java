package at.rene.myvaccreg.addVirus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.Virus;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DisplayVirusesFragment extends Fragment {
    private ArrayList<Virus> virusArrayList;
    private RecyclerView recyclerView;
    private AddVirusAdapter addVirusAdapter;
    private FloatingActionButton addVirus;
    private NewVirusFragment newVirusFragment;

    private MyVaccRegDb db;
    private List<Virus> virusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_virus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        db = MyVaccRegDb.getDbInstance(getActivity().getApplicationContext());
        virusList = db.virusDao().getAllViruses();

        virusArrayList = new ArrayList<>();
        newVirusFragment = new NewVirusFragment();

        addVirus = getActivity().findViewById(R.id.addVirusButton);
        //vaccines = getActivity().findViewById(R.id.myVaccsRecyclerView);
        recyclerView = getActivity().findViewById(R.id.addVirusRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /**
         * onClick(), um neue Virusarten anzulegen
         */
        addVirus.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragmentView, newVirusFragment)
                    .addToBackStack(null)
                    .commit();
        });

        addVirusAdapter = new AddVirusAdapter(getActivity(), virusList);
        recyclerView.setAdapter(addVirusAdapter);
    }


}