package at.rene.myvaccreg.allVaccs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.rene.myvaccreg.R;

public class AllVaccsHolder extends RecyclerView.ViewHolder {
    public TextView vaccTitle;
    public TextView vaccDesc;
    public TextView vaccRenewDate;
    public TextView vaccManufacturer;
    public ImageView imgView;

    public AllVaccsHolder(@NonNull View itemView) {
        super(itemView);

        vaccTitle = itemView.findViewById(R.id.vaccTitle);
        vaccDesc = itemView.findViewById(R.id.vaccDesc);
        vaccRenewDate = itemView.findViewById(R.id.vaccRenewDate);
        vaccManufacturer = itemView.findViewById(R.id.vaccManufacturer);
        imgView = itemView.findViewById(R.id.vaccinePic);
    }
}
