package at.rene.myvaccreg.data;

import java.io.Serializable;

public class VirusOLD implements Serializable {
    private String virusName;
    private String virusDesc;

    public VirusOLD(String name, String desc) {
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
