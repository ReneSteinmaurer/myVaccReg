package at.rene.myvaccreg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.rene.myvaccreg.data.Vaccination;
import at.rene.myvaccreg.myVacc.MyVacc;

public class AddVacc extends AppCompatActivity {
    private ListView listView;
    private CheckBox button;
    private ArrayList<Vaccination> vaccinationArrayList;
    private int listSize;
    private RecyclerView.Adapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacc);

        // Liefert die Daten von der MainActivity
        displayData();


        // onClickListener für die listView (Checkboxes)
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Log.i("ListViewExample","onItemClick: " +position);
            CheckedTextView v = (CheckedTextView) view;
            boolean currentCheck = v.isChecked();
            for (Vaccination listVacc : vaccinationArrayList) {
                if (!listVacc.isActive()) listVacc.setActive(!currentCheck);
                Log.d("AddVacc: ", listVacc.getName() +" " + listVacc.isActive());
            }
            //vacc.setActive(!currentCheck);

           // printSelectedItems();
        });
    }

    private void displayData() {
        vaccinationArrayList = new ArrayList<>();
        intent = getIntent();

        vaccinationArrayList = (ArrayList<Vaccination>) getIntent().getSerializableExtra("VaccinationList");

        // TODO Error
        //listView = (ListView)findViewById(R.id.addVaccView);

        initVaccs();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    /**
     * Zeigt alle Impfungen in der activity_add_vacc.xml an
     * @auhtor Rene Steinmaurer
     * @return
     */

    private ArrayList<Vaccination> initVaccs() {
        ArrayAdapter<Vaccination> arrayAdapter;

        // android.R.layout.simple_list_item_checked:
        // ListItem is very simple (Only one CheckedTextView).

        arrayAdapter = new ArrayAdapter<Vaccination>(this, android.R.layout.simple_list_item_multiple_choice , vaccinationArrayList);
        listView.setAdapter(arrayAdapter);


        for(int i=0;i< vaccinationArrayList.size(); i++ )  {
            listView.setItemChecked(i, vaccinationArrayList.get(i).isActive());
        }

        return vaccinationArrayList;
    }

    /**
     * Erstellt einen Toast für die ausgewählt Impfung (nicht in Verwendung)
     * @author Rene Steinmaurer
     */

    public void printSelectedItems()  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){
                Vaccination vacc= (Vaccination) listView.getItemAtPosition(i);
                String s= vacc.getName();
                sb = sb.append(" "+s);
            }
        }
        Toast.makeText(this, "Selected items are: "+sb.toString(), Toast.LENGTH_LONG).show();
    }


    /**
     * onClick Methode, dient zur übergabe der neuen Liste zur MyVacc
     * @param view
     */

    public void addVacToMyVaccs(View view) {
        intent = new Intent(this, MyVacc.class);
        intent.putExtra("VaccinationList", vaccinationArrayList);
        startActivity(intent);

        finish();
    }
}