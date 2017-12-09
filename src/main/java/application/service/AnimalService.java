package application.service;

import application.model.Animal;
import application.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public void addAnimal(Animal animal, MultipartFile photo) throws IOException{

        animal.setDonation(0);
        animal.setPhoto(animal.getName() + ".jpg");

        Animal savedAnimal= animalRepository.save(animal);

        String destination = "D:\\projekt_ssi\\Projekt_SSI_E_Schronisko\\src\\main\\resources\\public\\images\\"+savedAnimal.getName()+".jpg";

        photo.transferTo(new File(destination));

    }

    public List<Animal> getAllAnimals(){

        return (List)animalRepository.findAll();
    }



}
