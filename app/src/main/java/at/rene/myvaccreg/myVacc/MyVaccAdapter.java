package at.rene.myvaccreg.myVacc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;

public class MyVaccAdapter extends RecyclerView.Adapter<MyVaccHolder> {
    private final Context context;
    private List<VaccinationRoom> vaccinations;
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
        Log.i("MyVaccAdapter", "VaccVirus: " + vaccinations.get(i).getVirus());

        myVaccHolder.vaccTitle.setText(vaccinations.get(i).getName());
        myVaccHolder.vaccDesc.setText(vaccinations.get(i).getDesc());
        myVaccHolder.vaccDate.setText(vaccinations.get(i).getDate());
        myVaccHolder.renewDate.setText("Auffrischung: " + vaccinations.get(i).getRenewDate());
        myVaccHolder.vaccVirus.setText("Schützt vor: " + vaccinations.get(i).getVirus());
        myVaccHolder.vaccManufacturer.setText("Hersteller: " + vaccinations.get(i).getManufacturer());

        /**
         * Dieser Code dient dazu die angelegten Impfungen löschen zu können,
         * sie werden aus der DB wie auch aus der View gelöscht
         */
        myVaccHolder.deleteImg.setOnClickListener(v -> {
            db.vaccinationUserDao().deleteVacc(vaccinations.get(i).name);

            vaccinations.remove(myVaccHolder.getAdapterPosition());
            notifyItemRemoved(myVaccHolder.getAdapterPosition());
            notifyItemRangeChanged(myVaccHolder.getAdapterPosition(), vaccinations.size());
        });

        /**
         * Zeigt weitere Daten zu den Impfungen an
         */
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

    /**
     * Die Liste mit den Impfungen, wird neu gesetzt mit den Werten der gefilterten Liste
     *
     * @param filteredList
     */
    public void filteredList(List<VaccinationRoom> filteredList) {
        vaccinations = filteredList;
        notifyDataSetChanged();
    }

    public List<VaccinationRoom> getLatestVaccinations(List<VaccinationRoom> allVaccs) {
        List<VaccinationRoom> latestVaccs = new ArrayList<>();
        List<VaccinationRoom> usedVaccs = new ArrayList<>();
        String[] datav1;
        String[] datav2;
        boolean isGreater;


        for (VaccinationRoom v : allVaccs) {
            if (v.getName().contains(".")) usedVaccs.add(v);
            if (v.getDate().contains("-")) latestVaccs.add(v);
        }

        for (VaccinationRoom v : usedVaccs) {
            isGreater = true;
            for (VaccinationRoom v2 : usedVaccs) {
                datav1 = getArray(v.getName());
                datav2 = getArray(v2.getName());

                if (Integer.parseInt(datav1[0]) < Integer.parseInt(datav2[0])) isGreater = false;
            }

            if (!isGreater) latestVaccs.remove(v);
        }

        return latestVaccs;
    }

    /**
     * Gibt ein String-Array zurück, dieses besteht entweder aus zwei oder einem String.
     * Beispiel: 1. Covid 19 Impfung --> data[0]: 1. data[1]: Covid 19 Impfung
     *
     * @param s Name der Impfung
     * @return Array mit 1 oder 2 Werten
     */
    private String[] getArray(String s) {
        String[] data;
        if (s.contains(".")) data = s.split("\\.");
        else {
            data = new String[2];
            data[1] = s;
        }
        return data;
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }
}
