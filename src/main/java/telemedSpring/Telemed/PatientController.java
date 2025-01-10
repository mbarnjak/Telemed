package telemedSpring.Telemed;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {


    List<PatientRecord> patientRecordList = new ArrayList<>();

    @Autowired
    UserRepository userRepoDB;

    @Autowired
    PatientRecordRespository patientRecordRespository;

    @GetMapping("/unostlaka")
    public String formPacijent(){
        return "formPacijent";
    }


    @GetMapping("/proslizapisi")
    public String prosliZapisi(Model model, HttpSession session){
        AppUser user = (AppUser) session.getAttribute("user");

        List<PatientRecord> patientRecords = patientRecordRespository.findByAppUser(user);

        model.addAttribute("patientRecords", patientRecords);
        return "patient_details";
    }



    @GetMapping("/unesiNoviZapis")
    public String unesiNoviZapis(@RequestParam("sistolickiTlak") int sistolickiTlak, @RequestParam("dijastolickiTlak") int dijastolickiTlak,
                                 @RequestParam("otkucajiSrca") int otkucajiSrca, @RequestParam("opis") String opis, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String datum = LocalDateTime.now().format(formatter);
        PatientRecord newPatientRecord = new PatientRecord(datum, sistolickiTlak, dijastolickiTlak, otkucajiSrca, opis);

        newPatientRecord.setAppUser(user);

        patientRecordRespository.save(newPatientRecord);
        return "redirect:/proslizapisi";
    }

    @GetMapping("/delete")
    public String deleteRecord(@RequestParam int index) {
        if (index >= 0 && index < patientRecordList.size()) {
            patientRecordList.remove(index); // Uklanja zapis prema indeksu
        }
        return "redirect:/proslizapisi"; // Preusmjerava na stranicu s popisom zapisa
    }



}
