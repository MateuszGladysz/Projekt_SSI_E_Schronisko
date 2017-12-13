package application.controller;

import application.model.Animal;
import application.service.AnimalService;
import application.service.UserAccountService;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

//@Slf4j

@Controller
@RequestMapping({"/worker"})
public class WorkerController {
    private static final Logger log = LoggerFactory.getLogger(WorkerController.class);
    @Autowired
    private AnimalService animalService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private HttpSession session;

    public WorkerController() {
    }

    @RequestMapping(
            value = {"/logout"},
            method = {RequestMethod.GET}
    )
    public String logout() {
        this.session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(
            value = {"/addAnimal"},
            method = {RequestMethod.GET}
    )
    public String animal() {
        return "addAnimal";
    }

    @RequestMapping(
            value = {"/addMoney"},
            method = {RequestMethod.GET}
    )
    public String money() {
        return "addMoney";
    }

    @RequestMapping(
            value = {"/addMoney"},
            method = {RequestMethod.POST}
    )
    public String submit(HttpServletRequest request, Map<String, Object> model) {
        String message = this.userAccountService.updateBalance(request.getParameter("userEmail"), Integer.parseInt(request.getParameter("balance")));
        if (message.equals("Balance add correctly")) {
            model.put("balanceUpdateMessage", "Konto zostalo zaktualizowane");
        } else {
            model.put("balanceUpdateMessage", "Konto nie zostalo zaktualizowane");
        }

        return "addMoney";
    }

    @RequestMapping(
            value = {"/addAnimal"},
            method = {RequestMethod.POST}
    )
    public String addAnimal(Animal animal, @RequestParam("file") MultipartFile photo, Map<String, Object> model) {
        if (!photo.getOriginalFilename().contains(".jpg")) {
            model.put("addingFailureMessage", "Proszę podać plik z roszerzeniem jpg");
            return "/addAnimal";
        } else {
            try {
                this.animalService.addAnimal(animal, photo);
                return "redirect:/worker";
            } catch (IOException var5) {
                model.put("addingFailureMessage", "Problem z zapisem do bazy");
                return "/addAnimal";
            }
        }
    }
}
