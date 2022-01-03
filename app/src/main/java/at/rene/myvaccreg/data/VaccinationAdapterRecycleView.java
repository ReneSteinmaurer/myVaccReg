package at.rene.myvaccreg.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.rene.myvaccreg.R;

public class VaccinationAdapterRecycleView extends RecyclerView.Adapter<VaccinationAdapterRecycleView.ViewHolder> {
    private ArrayList<Vaccination> vaccs;
    private int layout;

    public VaccinationAdapterRecycleView(ArrayList<Vaccination> vaccs, int layout) {
        this.vaccs = vaccs;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        System.out.println("error");

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vaccination vacc = vaccs.get(position);

        holder.name.setText(vacc.getName());
        holder.desc.setText(vacc.getDesc());
        holder.date.setText(vacc.getDate());
        holder.vaccine.setText(vacc.getVaccination());
    }

    @Override
    public int getItemCount() {
        if (vaccs != null) {
            return vaccs.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView desc;
        public final TextView date;
        public final TextView vaccine;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.VaccName);
            desc = view.findViewById(R.id.VaccDesc);
            date = view.findViewById(R.id.VaccDate);
            vaccine = view.findViewById(R.id.Vaccine);
        }
    }
}