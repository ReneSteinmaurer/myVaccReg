package at.rene.myvaccreg.myVacc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class MyVaccAdapter extends RecyclerView.Adapter<MyVaccHolder> {
    private Context context;
    private List<VaccinationRoom> vaccinations;

    public MyVaccAdapter(Context context, List<VaccinationRoom> vaccinations) {
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
        myVaccHolder.renewDate.setText("Auffrischungs-Termin: " + vaccinations.get(i).getRenewDate());
        myVaccHolder.vaccVirus.setText("Schützt vor: " + vaccinations.get(i).getVirus());
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }
}
