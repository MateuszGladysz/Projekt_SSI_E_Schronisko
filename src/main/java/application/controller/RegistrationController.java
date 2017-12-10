package application.controller;

import application.model.UserAccount;
import application.service.UserAccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserAccountService userAccountServ;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc, Map<String, Object> model, HttpServletRequest request) {

        String message;
        message = userAccountServ.addUser(userAcc, request.getParameter("userPassword1"));

        if(message.equals("registrationGood")) return "redirect:/";

        if(message.equals("noMatchPasswords")){
            model.put("registerMessage", "Błędne powtórzenie hasła");
            return "register";
        }

        if(message.equals("emailAlreadyUsed")){
            model.put("registerMessage", "Konto o podanym emailu już istnieje");
            return "register";
        }

        return "register";


    }
}
