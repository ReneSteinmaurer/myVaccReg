package at.rene.myvaccreg.myVacc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.rene.myvaccreg.R;

public class AllVaccsHolder extends RecyclerView.ViewHolder {
    public TextView vaccTitle;
    public TextView vaccDesc;
    public TextView vaccRenewDate;
    public TextView vaccManufacturer;

    public AllVaccsHolder(@NonNull View itemView) {
        super(itemView);

        vaccTitle = itemView.findViewById(R.id.vaccTitle);
        vaccDesc = itemView.findViewById(R.id.vaccDesc);
        vaccRenewDate = itemView.findViewById(R.id.vaccRenewDate);
        vaccManufacturer = itemView.findViewById(R.id.vaccManufacturer);
    }
}
