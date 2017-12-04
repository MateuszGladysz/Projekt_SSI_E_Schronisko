package application.controller;


import application.model.UserAccount;
import application.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserAccountService userAccountServ;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {return "login"; }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc, Map<String,Object> model){

        String message;

        model.put("loginFailureMessage","");
        if(userAcc != null){
            message = userAccountServ.loginUser(userAcc.getUserEmail(),userAcc.getUserPassword());

            if(message.equals("zalogowano")){
                session.setAttribute("loggedUser", userAcc);
                return "redirect:/";
            }
            if(message.equals("niezalogowano")){
                model.put("loginFailureMessage","Błędny login lub hasło");
                return "/login";
            }
            if(message.equals("brak konta z tym mailem")){
                model.put("loginFailureMessage","Brak konta o podanym adresie E-mail");
                return "/login";
            }

        }

        return "/login";

    }
}
