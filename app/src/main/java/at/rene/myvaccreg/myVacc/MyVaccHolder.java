package at.rene.myvaccreg.myVacc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.rene.myvaccreg.R;

public class MyVaccHolder extends RecyclerView.ViewHolder {
    public TextView vaccTitle;
    public TextView vaccDesc;
    public TextView vaccDate;
    public TextView renewDate;
    public TextView vaccVirus;
    public TextView vaccManufacturer;
    public ImageView deleteImg;

    public MyVaccHolder(@NonNull View itemView) {
        super(itemView);

        vaccTitle = itemView.findViewById(R.id.vaccTitle);
        vaccDesc = itemView.findViewById(R.id.vaccDesc);
        vaccDate = itemView.findViewById(R.id.vaccDate);
        renewDate = itemView.findViewById(R.id.vaccRenewDate);
        vaccVirus = itemView.findViewById(R.id.vaccVirus);
        vaccManufacturer = itemView.findViewById(R.id.vaccManufacturer);
        deleteImg = itemView.findViewById(R.id.vaccDelete);
    }

}
