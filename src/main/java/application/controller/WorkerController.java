package application.controller;

import application.model.Animal;
import application.model.UserAccount;
import application.service.AnimalService;
import application.service.UserAccountService;
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
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/addAnimal", method = {RequestMethod.GET})
    public String animal() {return "addAnimal"; }



    @RequestMapping(value = "/addMoney", method = {RequestMethod.GET})
    public String money() {return "addMoney"; }


    @RequestMapping(value = "/addAnimal", method = {RequestMethod.POST})
    public String addAnimal(Animal animal,@RequestParam("file")MultipartFile photo, Map<String,Object> model){



        if(!photo.getOriginalFilename().contains(".jpg")){
            model.put("addingFailureMessage","Proszę podać plik z roszerzeniem jpg");
            return "/addAnimal";
        }

        try {
            animalService.addAnimal(animal, photo);
        } catch (IOException e) {
            model.put("addingFailureMessage","Problem z zapisem do bazy");
           return "/addAnimal";
        }


        return "redirect:/worker";

    }
}
