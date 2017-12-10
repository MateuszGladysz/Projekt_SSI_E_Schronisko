package application.controller;


import application.model.UserAccount;
import application.service.AnimalService;
import application.service.UserAccountService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Log4j
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private UserAccountService userAccountService;



    @RequestMapping(method= RequestMethod.GET)
    public String home(Model model){

        model.addAttribute("animals",animalService.getAllAnimals());


        return "home";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String register() {return "register"; }

    @RequestMapping(value = "/account", method = {RequestMethod.GET})
    public String account() {return "account"; }

    @RequestMapping(value = "/tips", method = {RequestMethod.GET})
    public String tips() {return "tips"; }

    @RequestMapping(value = "/psy", method = {RequestMethod.GET})
    public String psy() {return "psy"; }

    @RequestMapping(value = "/koty", method = {RequestMethod.GET})
    public String koty() {return "koty"; }

    @RequestMapping(value = "/inne", method = {RequestMethod.GET})
    public String inne() {return "inne"; }

    @RequestMapping(value = "/worker", method = {RequestMethod.GET})
    public String worker() {

        UserAccount user = (UserAccount)session.getAttribute("loggedUser");
        String workerCode = "5555";



        if(user!= null) {
            UserAccount account = userAccountService.getUserByEmail(user.getUserEmail());
            if (workerCode.equals(account.getWorkerCode()))
                return "worker";
        }
            return "redirect:/";
    }

}
