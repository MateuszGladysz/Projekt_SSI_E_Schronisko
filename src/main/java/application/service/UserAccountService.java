package application.service;

import application.model.UserAccount;
import application.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepo;

    @Autowired
    private HttpSession session;

    public String addUser(UserAccount userAcc, String passwordToCheck){


            if(findUserByEmail(userAcc.getUserEmail()) == null){

                if(passwordChangeValidation(userAcc.getUserPassword()
                        ,passwordToCheck).equals("changePasswordGood")){

                        userAccountRepo.save(userAcc);
                        return "registrationGood";

                }else return "noMatchPasswords";

            } else return "emailAlreadyUsed";


    }

    public String loginUser(String userEmail, String userPassword){

        System.out.println(userEmail);
        System.out.println(userPassword);


            if(userAccountRepo.findOneByUserEmail(userEmail) != null){
                UserAccount userToLogin = userAccountRepo.findOneByUserEmail(userEmail);
                if(userToLogin.getUserPassword().equals(userPassword)){
                    return "logged";
                } else return "notLogged";
            }else return "NoAccountWithThisEmail";
        }

     public UserAccount findUserByEmail(String email){

        UserAccount userAcc = userAccountRepo.findOneByUserEmail(email);

        return userAcc;
     }

     public String editUserAccount(UserAccount userAcc, String newPassword, String newPassword2) {

         UserAccount userFromSession = (UserAccount) session.getAttribute("loggedUser");

         if (!userAcc.getUserFirstName().equals("")) userFromSession.setUserFirstName(userAcc.getUserFirstName());
         if (!userAcc.getUserLastName().equals("")) userFromSession.setUserLastName(userAcc.getUserLastName());
         if (!userAcc.getUserAddress().equals("")) userFromSession.setUserAddress(userAcc.getUserAddress());
         if (!userAcc.getUserPostCode().equals("")) userFromSession.setUserPostCode(userAcc.getUserPostCode());
         if (!userAcc.getUserCity().equals("")) userFromSession.setUserCity(userAcc.getUserCity());
         if (!userAcc.getUserEmail().equals("")) userFromSession.setUserEmail(userAcc.getUserEmail());

         if (!userAcc.getUserPassword().equals("") ) {
             if (userAcc.getUserPassword().equals(userFromSession.getUserPassword())) {

                 String message;
                 message = passwordChangeValidation(newPassword, newPassword2);

                 if (message.equals("changePasswordGood")) {
                     userFromSession.setUserPassword(newPassword);
                     session.setAttribute("loggedUser", userFromSession);

                     userAccountRepo.save(userFromSession);

                     return "changePasswordGood";
                 }

             } else {

                 session.setAttribute("loggedUser", userFromSession);

                 userAccountRepo.save(userFromSession);

                 return "badCurrentPassword";
             }
         }
            return "";
     }


     public String passwordChangeValidation(String password1, String password2){

         if(password1.length() > 5 && password1.length() < 15 &&
                 password2.length() > 5 && password2.length() < 15){

              if (password1.equals(password2)) return "changePasswordGood";
              else return "noMatchPasswords";

         }else return "toShortPasswords";

     }

}
