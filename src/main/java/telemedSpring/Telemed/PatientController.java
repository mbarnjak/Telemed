package telemedSpring.Telemed;

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


    @GetMapping("/unostlaka")
    public String formPacijent(){
        return "formPacijent";
    }


    @GetMapping("/proslizapisi")
    public String prosliZapisi(Model model){
        model.addAttribute(patientRecordList);
        return "patient_details";
    }

    @GetMapping("/unesiNoviZapis")
    public String unesiNoviZapis(int sistolickiTlak, int dijastolickiTlak, int otkucajiSrca, String opis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String datum = LocalDateTime.now().format(formatter);
        patientRecordList.add(new PatientRecord(datum, sistolickiTlak, dijastolickiTlak, otkucajiSrca, opis));
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
