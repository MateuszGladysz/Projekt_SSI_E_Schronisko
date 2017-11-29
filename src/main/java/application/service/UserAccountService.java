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
}
