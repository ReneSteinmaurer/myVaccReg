package at.rene.myvaccreg.addVirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.data.Virus;

public class AddVirusAdapter extends RecyclerView.Adapter<AddVirusHolder> {
    private Context context;
    private ArrayList<Virus> viruses;

    public AddVirusAdapter(Context context, ArrayList<Virus> viruses) {
        this.context = context;
        this.viruses = viruses;
    }

    @NonNull
    @Override
    public AddVirusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_virus_row, null);

        return new AddVirusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddVirusHolder addVirusHolder, int i) {
        addVirusHolder.virusName.setText(viruses.get(i).getName());
        addVirusHolder.virusDesc.setText(viruses.get(i).getDesc());
    }

    @Override
    public int getItemCount() {
        return viruses.size();
    }
}
