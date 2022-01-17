package at.rene.myvaccreg.myVacc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.MyVaccRegDb;
import at.rene.myvaccreg.roomdb.VaccinationRoom;
import at.rene.myvaccreg.roomdb.VaccinationUser;

public class AllVaccsAdpater extends RecyclerView.Adapter<AllVaccsHolder> {
    private Context context;
    private List<VaccinationRoom> vaccinations;
    private MyVaccRegDb db;
    private List<VaccinationUser> allVaccs;

    public AllVaccsAdpater(Context context, List<VaccinationRoom> vaccinations) {
        db = MyVaccRegDb.getDbInstance(context.getApplicationContext());
        this.context = context;
        this.vaccinations = vaccinations;
    }

    @NonNull
    @Override
    public AllVaccsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_vaccs_row, null);

        return new AllVaccsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVaccsHolder allVaccsHolder, int i) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        allVaccsHolder.vaccTitle.setText(vaccinations.get(i).getName());
        allVaccsHolder.vaccDesc.setText(vaccinations.get(i).getDesc());
        allVaccsHolder.vaccRenewDate.setText("Auffrischung: " + vaccinations.get(i).getRenewDate());
        allVaccsHolder.vaccManufacturer.setText("Hersteller: " + vaccinations.get(i).getManufacturer());

        allVaccsHolder.itemView.setOnClickListener(v -> {
            boolean hasVaccine = false;
            Log.i("OnClick", "VaccHolder Onclick!");

            // Überprüfen ob der User die Impfung schon bekommen hat
            allVaccs = db.vaccinationUserDao().getAllVaccinations();

            VaccinationUser u = new VaccinationUser();
            u.setVaccination(vaccinations.get(i).getName());
            // Setzt das heutige Datum als Impftermin
            u.setDate(formatter.format(date));

            for (VaccinationUser vu : allVaccs) {
                if (vu.getVaccination().equals(u.getVaccination()))
                    hasVaccine = true;
            }

            // Wenn der User die Impfung noch nicht hat wird sie eingefügt
            if (!hasVaccine) db.vaccinationUserDao().addVaccination(u);

        });
    }

    public void filteredList(List<VaccinationRoom> filteredList) {
        vaccinations = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
    }
}
