package at.rene.myvaccreg.data;

public class UserMemo {
    private String name;
    private String vaccinations;

    public UserMemo(String name, String vaccinations) {
        this.name = name;
        this.vaccinations = vaccinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(String vaccinations) {
        this.vaccinations = vaccinations;
    }
}
