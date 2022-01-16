package at.rene.myvaccreg.addVirus;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.rene.myvaccreg.R;

public class AddVirusHolder extends RecyclerView.ViewHolder {
    public TextView virusName;
    public TextView virusDesc;
    public TextView virusCategory;

    public AddVirusHolder(@NonNull View itemView) {
        super(itemView);

        virusName = itemView.findViewById(R.id.virusTitle);
        virusDesc = itemView.findViewById(R.id.virusDesc);
        virusCategory = itemView.findViewById(R.id.virusCategory);
    }
}
