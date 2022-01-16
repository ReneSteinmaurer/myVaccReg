package at.rene.myvaccreg.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Vaccination {
    @PrimaryKey
    @NonNull
    private String name;
    private String desc;
    private String date;
    private boolean active;
    private String vaccination;
    private ArrayList<Vaccination> vaccs = new ArrayList<>();

    @Ignore
    public Vaccination(String name, String desc, String date, String vaccination, boolean active) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.vaccination = vaccination;
        this.active = active;
    }

    public Vaccination() {
        this (null,null,null,null,false);
    }

    public String getVaccination() {
        return vaccination;
    }

    public ArrayList<Vaccination> initVaccines() { ;
        vaccs.add(new Vaccination("Covid19", "Die Erkrankung, die das Virus hervorruft, wird als COVID-19 bezeichnet. Die Beschwerden betreffen nicht nur - wie ursprünglich angenommen - die Atemwege, es ist ein breites Spektrum an Symptomen möglich.", "19-10-2020", "Impfstoff: Biontech Pfizer",true));
        vaccs.add(new Vaccination("Anthrax", "Vaxchora, Anthrax (Milzbrand) ist primär eine Erkrankung von Huftieren wie Rindern oder Schafen, die durch das Bakterium Bacillus anthracis verursacht wird. Dieses bildet Sporen, die unter günstigen Bedingungen mehrere Jahrzehnte in der Umwelt überleben können.", "30-10-2016", "Impfstoff: Hammervacc BAC",false));
        vaccs.add(new Vaccination("Cholera", "AVA, Cholera ist eine Infektionskrankheit, die durch den Erreger Vibrio cholerae hervorgerufen wird. Dieser führt zu schweren Durchfällen und erfordert eine rasche Behandlung. Cholera zählt zu den meldepflichtigen Infektionskrankheiten.", "06-07-2002", "Impfstoff: Zieradik CHO",false));
        vaccs.add(new Vaccination("Hepatitis A", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Perita",false));
        vaccs.add(new Vaccination("Hepatitis B", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",true));
        vaccs.add(new Vaccination("Covid19", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",false));
        vaccs.add(new Vaccination("Anthrax B", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",false));
        vaccs.add(new Vaccination("Cholera B", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",true));
        vaccs.add(new Vaccination("Hepatitis A", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",false));
        vaccs.add(new Vaccination("Hepatitis C", "Hepatitis-A-Viren (HAV) verursachen eine akute Entzündung des Lebergewebes. Die Hepatitis A ist zwar eine ernst zu nehmende Erkrankung, verläuft aber im Allgemeinen recht harmlos. Sie heilt meist vollständig aus und hinterlässt eine lebenslange Immunität", "10-04-2006", "Impfstoff: Moderna Bettletus",false));
        return vaccs;
    }

    public ArrayList<Vaccination> getVaccs() {
        return vaccs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vaccination: "+ name;
    }
}
