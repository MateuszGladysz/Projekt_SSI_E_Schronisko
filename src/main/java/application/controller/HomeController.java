package application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HttpSession session;



    @RequestMapping(method= RequestMethod.GET)
    public String home(){

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

    @RequestMapping(value = "/tips", method = {RequestMethod.GET})
    public String tips() {return "tips"; }

    @RequestMapping(value = "/psy", method = {RequestMethod.GET})
    public String psy() {return "psy"; }

    @RequestMapping(value = "/koty", method = {RequestMethod.GET})
    public String koty() {return "koty"; }

    @RequestMapping(value = "/inne", method = {RequestMethod.GET})
    public String inne() {return "inne"; }

}
