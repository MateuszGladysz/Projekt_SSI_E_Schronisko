package application.service;

import application.model.UserAccount;
import application.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepo;

    public String addUser(UserAccount userAcc){

        if(userAcc != null) {
            userAccountRepo.save(userAcc);
            return "dodano";
        }

        return "nie dodano";
    }

    public String loginUser(String userEmail, String userPassword){

        System.out.println(userEmail);
        System.out.println(userPassword);


            if(userAccountRepo.findOneByUserEmail(userEmail) != null){
                UserAccount userToLogin = userAccountRepo.findOneByUserEmail(userEmail);
                if(userToLogin.getUserPassword().equals(userPassword)){
                    return "zalogowano";
                } else return "niezalogowano";
            }else return "brak konta z tym mailem";
        }

     public UserAccount findUserByEmail(String email){

        UserAccount userAcc = userAccountRepo.findOneByUserEmail(email);

        return userAcc;
     }

     public String editUserAccount(UserAccount userAcc){

         userAccountRepo.save(userAcc);
         return "";
     }

}
