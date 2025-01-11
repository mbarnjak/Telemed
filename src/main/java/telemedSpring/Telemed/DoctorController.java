package telemedSpring.Telemed;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    UserRepository userRepoDB;

    @Autowired
    PatientRecordRespository patientRecordRepo;


    @GetMapping("/pacijenti")
    public String patientList(Model model, HttpSession session) {
        List<AppUser> appUsersList = userRepoDB.findByType(0);

        // Proslijedite listu korisnika Thymeleaf šabloni
        model.addAttribute("appUsersList", appUsersList);
        return "patientList";
    }

    @GetMapping("/dodajPacijenta")
    public String addPatient() {
        return "addPatient";
    }

    @GetMapping("/zapisiPacijenta")
    public String patientRecords(@RequestParam("email") String email, Model model) {
        // Dohvat pacijenta putem emaila
        AppUser patient = userRepoDB.findByEmail(email);
        if (patient == null) {
            return "redirect:/error"; // Ako pacijent nije pronađen
        }

        // Dodavanje podataka pacijenta u model
        model.addAttribute("patientName", patient.getName() + " " + patient.getSurname());
        model.addAttribute("patientEmail", patient.getEmail());
        model.addAttribute("patientPhone", patient.getNumber());
        model.addAttribute("patientAddress", patient.getAddress());
        model.addAttribute("patientCity", patient.getAddress());

        // Dohvat zapisa pacijenta
        List<PatientRecord> patientRecords = patientRecordRepo.findByAppUser(patient);
        model.addAttribute("patientRecords", patientRecords);

        model.addAttribute("patient", patient);


        return "patientRecords";
    }

    @GetMapping("/dodajNovogPacijenta")
    public String addNewPatient(@RequestParam("ime") String firstName, @RequestParam("prezime") String lastName,
                                @RequestParam("email") String email, @RequestParam("telefon") String phoneNumber, @RequestParam("adresa") String address,
                                @RequestParam("lozinka") String password) {
        AppUser newUser = new AppUser(firstName, lastName, email, password, address, phoneNumber, 0);
        userRepoDB.save(newUser);
        return "redirect:/pacijenti";
    }
}
