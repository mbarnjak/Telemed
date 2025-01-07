package telemedSpring.Telemed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @GetMapping("/pacijenti")
    public String patientList() {
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
}
