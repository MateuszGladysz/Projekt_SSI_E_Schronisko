package application.service;

import application.model.Animal;
import application.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public void addAnimal(Animal animal, MultipartFile photo) throws IOException{

        animal.setDonation(0);
        animal.setPhoto("Projekt_SSI_E_Schronisko\\images\\" + animal.getName() + ".jpg");

        Animal savedAnimal= animalRepository.save(animal);

        String destination = "D:\\projekt_ssi\\Projekt_SSI_E_Schronisko\\images\\"+savedAnimal.getName()+".jpg";

        photo.transferTo(new File(destination));

    }



}
