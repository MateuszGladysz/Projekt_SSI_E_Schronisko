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

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {return "login"; }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc, Map<String,Object> model){

        String message;
        UserAccount userToSession;
        model.put("loginFailureMessage","");
        if(userAcc != null){
            message = userAccountServ.loginUser(userAcc.getUserEmail(),userAcc.getUserPassword());

            userToSession = userAccountServ.findUserByEmail(userAcc.getUserEmail());
            if(message.equals("logged")){
                session.setAttribute("loggedUser", userToSession);
               
                if(userToSession.getWorkerCode().equals("5555"))
                    return "/worker";

                return "redirect:/";
            }
            if(message.equals("notLogged")){
                model.put("loginFailureMessage","Błędny login lub hasło");
                return "/login";
            }
            if(message.equals("NoAccountWithThisEmail")){
                model.put("loginFailureMessage","Brak konta o podanym adresie E-mail");
                return "/login";
            }

        }

        return "/login";

    }
}
