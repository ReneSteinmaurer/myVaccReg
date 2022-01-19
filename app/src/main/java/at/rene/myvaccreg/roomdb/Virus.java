package at.rene.myvaccreg.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Virus {

    @PrimaryKey @NonNull
    public String name;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "category")
    public String category;

    public Virus() {
        this("","","");
    }

    public Virus(String name, String desc, String category) {
        this.name = name;
        this.desc = desc;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
