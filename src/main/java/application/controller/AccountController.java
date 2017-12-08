package application.controller;


import application.model.UserAccount;
import application.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserAccountService userAccountServ;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(@Valid UserAccount userAcc, BindingResult result){

        UserAccount userFromSession = (UserAccount) session.getAttribute("loggedUser");


        if(!userAcc.getUserFirstName().equals("")) userFromSession.setUserFirstName(userAcc.getUserFirstName());
        if(!userAcc.getUserLastName().equals("")) userFromSession.setUserLastName(userAcc.getUserLastName());
        if(!userAcc.getUserAddress().equals("")) userFromSession.setUserAddress(userAcc.getUserAddress());
        if(!userAcc.getUserPostCode().equals("")) userFromSession.setUserPostCode(userAcc.getUserPostCode());
        if(!userAcc.getUserCity().equals("")) userFromSession.setUserCity(userAcc.getUserCity());
        if(!userAcc.getUserEmail().equals("")) userFromSession.setUserEmail(userAcc.getUserEmail());


        session.setAttribute("loggedUser",userFromSession);
        userAccountServ.editUserAccount(userFromSession);

        return "/account";

    }


}
