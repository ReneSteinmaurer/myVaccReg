package at.rene.myvaccreg.myVacc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.Vaccination;

public class MyVaccAdapter extends RecyclerView.Adapter<MyVaccHolder> {
    private Context context;
    private ArrayList<Vaccination> vaccinations;

    public MyVaccAdapter(Context context, ArrayList<Vaccination> vaccinations) {
        this.context = context;
        this.vaccinations = vaccinations;
    }

    @NonNull
    @Override
    public MyVaccHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_vaccs_row, null);

        return new MyVaccHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVaccHolder myVaccHolder, int i) {
        myVaccHolder.vaccTitle.setText(vaccinations.get(i).getName());
        myVaccHolder.vaccDesc.setText(vaccinations.get(i).getDesc());
        myVaccHolder.vaccDate.setText(vaccinations.get(i).getDate());

    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }
}
