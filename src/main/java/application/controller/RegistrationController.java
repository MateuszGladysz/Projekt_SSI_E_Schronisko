package application.controller;

import application.model.UserAccount;
import application.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
    public String submit(UserAccount userAcc){

        System.out.println(userAcc.getUserCity());
        if(userAcc != null){
            System.out.println(userAccountServ.addUser(userAcc));
            return "redirect:/";
        }

         return "/registry";

    }
}
