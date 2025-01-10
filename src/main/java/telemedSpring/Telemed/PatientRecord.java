package telemedSpring.Telemed;

import jakarta.persistence.*;

@Entity
public class PatientRecord {
    @Id
    @GeneratedValue
    private int patientRecordId;
    private String date;
    private int otkucaji;
    private int sistolickiTlak;
    private int dijastolickiTlak;
    private String opis;

    @ManyToOne
    @JoinColumn(name="userId")
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public PatientRecord() {
    }

    public PatientRecord(int otkucaji) {
        this.otkucaji = otkucaji;
    }

    public PatientRecord(String date, int otkucaji, int sistolickiTlak, int dijastolickiTlak, String opis) {
        this.date = date;
        this.otkucaji = otkucaji;
        this.sistolickiTlak = sistolickiTlak;
        this.dijastolickiTlak = dijastolickiTlak;
        this.opis = opis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOtkucaji() {
        return otkucaji;
    }

    public void setOtkucaji(int otkucaji) {
        this.otkucaji = otkucaji;
    }

    public int getSistolickiTlak() {
        return sistolickiTlak;
    }

    public void setSistolickiTlak(int sistolickiTlak) {
        this.sistolickiTlak = sistolickiTlak;
    }

    public int getDijastolickiTlak() {
        return dijastolickiTlak;
    }

    public void setDijastolickiTlak(int dijastolickiTlak) {
        this.dijastolickiTlak = dijastolickiTlak;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
