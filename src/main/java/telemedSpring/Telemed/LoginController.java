package telemedSpring.Telemed;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/")
    public String loginIndex(){
        return "redirect:/login";
    }

}
