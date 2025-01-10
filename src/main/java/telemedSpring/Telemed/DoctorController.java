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
    public String patientRecords() {
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
