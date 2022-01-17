package at.rene.myvaccreg.addVirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.rene.myvaccreg.R;
import at.rene.myvaccreg.roomdb.Virus;

public class AddVirusAdapter extends RecyclerView.Adapter<AddVirusHolder> {
    private Context context;
    private List<Virus> viruses;

    public AddVirusAdapter(Context context, List<Virus> viruses) {
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
        addVirusHolder.virusCategory.setText("Virus Kategorie: " + viruses.get(i).getCategory());

        addVirusHolder.itemView.setOnClickListener(v -> {

            if (addVirusHolder.virusDesc.getVisibility() == View.GONE)
                addVirusHolder.virusDesc.setVisibility(View.VISIBLE);
            else addVirusHolder.virusDesc.setVisibility(View.GONE);

        });
    }

    @Override
    public int getItemCount() {
        return viruses.size();
    }
}
