package telemedSpring.Telemed;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepoDB;

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/")
    public String loginIndex(){
        return "redirect:/login";
    }

    @GetMapping("/loginProcess")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password,
                               Model model, HttpSession session) {
        AppUser user = null;

        for (AppUser u : userRepoDB.findAll()) {
            System.out.println("Users: " + u);
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                user = u;
            }
        }
        if (user == null) {
            model.addAttribute("loginMessage", "Wrong username or password");
            return "login";
        } else {
            System.out.println("User logged in: " + user);
            if (user.getType() == 1) {
                session.setAttribute("user", user);
                return "redirect:/pacijenti";
            } else if (user.getType() == 0) {
                session.setAttribute("user", user);
                return "redirect:/proslizapisi";
            }
        }
        return "";
    }

}
