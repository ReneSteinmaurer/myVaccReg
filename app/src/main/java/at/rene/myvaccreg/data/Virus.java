package at.rene.myvaccreg.data;

import java.io.Serializable;

public class Virus implements Serializable {
    private String virusName;
    private String virusDesc;

    public Virus(String name, String desc) {
        this.virusName = name;
        this.virusDesc = desc;
    }

    public String getName() {
        return virusName;
    }

    public void setName(String name) {
        this.virusName = name;
    }

    public String getDesc() {
        return virusDesc;
    }

    public void setDesc(String desc) {
        this.virusDesc = desc;
    }
}
