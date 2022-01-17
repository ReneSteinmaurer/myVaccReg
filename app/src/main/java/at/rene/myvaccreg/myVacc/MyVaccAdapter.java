package at.rene.myvaccreg.myVacc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class MyVaccAdapter extends RecyclerView.Adapter<MyVaccHolder> {
    private final Context context;
    private final List<VaccinationRoom> vaccinations;
    private MyVaccRegDb db;

    public MyVaccAdapter(Context context, List<VaccinationRoom> vaccinations) {
        this.context = context;
        this.vaccinations = vaccinations;
    }

    @NonNull
    @Override
    public MyVaccHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_vaccs_row, null);
        db = MyVaccRegDb.getDbInstance(context.getApplicationContext());

        return new MyVaccHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVaccHolder myVaccHolder, int i) {
        Log.i("MyVaccAdapter", "VaccVirus: "+ vaccinations.get(i).getVirus());


        myVaccHolder.vaccTitle.setText(vaccinations.get(i).getName());
        myVaccHolder.vaccDesc.setText(vaccinations.get(i).getDesc());
        myVaccHolder.vaccDate.setText(vaccinations.get(i).getDate());
        myVaccHolder.renewDate.setText("Auffrischung: " + vaccinations.get(i).getRenewDate());
        myVaccHolder.vaccVirus.setText("Schützt vor: " + vaccinations.get(i).getVirus());
        myVaccHolder.vaccManufacturer.setText("Hersteller: "+ vaccinations.get(i).getManufacturer());

        myVaccHolder.deleteImg.setOnClickListener(v -> {
            if (vaccinations.size() == 1) {
                db.vaccinationUserDao().deleteVacc(vaccinations.get(0).name);
                vaccinations.remove(0);
                this.notifyItemRemoved(0);
            } else {
                db.vaccinationUserDao().deleteVacc(vaccinations.get(i).name);
                vaccinations.remove(i);
                this.notifyItemRemoved(i);
            }
            myVaccHolder.itemView.setVisibility(View.GONE);

        });

        myVaccHolder.itemView.setOnClickListener(v -> {

            if (myVaccHolder.vaccDesc.getVisibility() == View.GONE) {
                myVaccHolder.vaccDesc.setVisibility(View.VISIBLE);
                myVaccHolder.vaccVirus.setVisibility(View.VISIBLE);
                myVaccHolder.renewDate.setVisibility(View.VISIBLE);
                myVaccHolder.vaccManufacturer.setVisibility(View.VISIBLE);
            } else {
                myVaccHolder.vaccDesc.setVisibility(View.GONE);
                myVaccHolder.vaccVirus.setVisibility(View.GONE);
                myVaccHolder.renewDate.setVisibility(View.GONE);
                myVaccHolder.vaccManufacturer.setVisibility(View.GONE);
            }

        });
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }
}
