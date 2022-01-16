package at.rene.myvaccreg.addVirus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.Virus;
import at.rene.myvaccreg.myVacc.AddVaccineToUser;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddVirusFragment extends Fragment {
    private ArrayList<Virus> virusArrayList;
    private RecyclerView recyclerView;
    private AddVirusAdapter addVirusAdapter;
    private FloatingActionButton addVirus;
    //private LinearLayout vaccines;
    private AppCompatButton displayFood;
    private AddVaccineToUser addVaccToUserf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_virus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        virusArrayList = new ArrayList<>();

        addVirus = getActivity().findViewById(R.id.addVirusButton);
        //vaccines = getActivity().findViewById(R.id.myVaccsRecyclerView);
        recyclerView = getActivity().findViewById(R.id.addVirusRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // displayUsersVaccines();

        virusArrayList.add(new Virus("Corona", "lsjdlf sodifj asd asdf iej salkdkf."));
        virusArrayList.add(new Virus("Flu", "asdlfj sdf  sdjf asdklf sldkfj sfs."));

        addVirusAdapter = new AddVirusAdapter(getActivity(), virusArrayList);
        recyclerView.setAdapter(addVirusAdapter);
    }


}