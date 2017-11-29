package application.controller;

import application.model.UserAccount;
import application.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserAccountService userAccountServ;

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register() {return "register"; }


    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc){


        if(userAcc != null){
            System.out.println(userAccountServ.addUser(userAcc));
            return "redirect:/";
        }

         return "/registry";

    }
}
