package application.service;

import application.model.UserAccount;
import application.repository.UserAccountRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepo;
    @Autowired
    private HttpSession session;

    public UserAccountService() {
    }

    public String addUser(UserAccount userAcc, String passwordToCheck) {
        if (this.findUserByEmail(userAcc.getUserEmail()) == null) {
            if (this.passwordChangeValidation(userAcc.getUserPassword(), passwordToCheck).equals("changePasswordGood")) {
                this.userAccountRepo.save(userAcc);
                return "registrationGood";
            } else {
                return "noMatchPasswords";
            }
        } else {
            return "emailAlreadyUsed";
        }
    }

    public String loginUser(String userEmail, String userPassword) {
        System.out.println(userEmail);
        System.out.println(userPassword);
        if (this.userAccountRepo.findOneByUserEmail(userEmail) != null) {
            UserAccount userToLogin = this.userAccountRepo.findOneByUserEmail(userEmail);
            return userToLogin.getUserPassword().equals(userPassword) ? "logged" : "notLogged";
        } else {
            return "NoAccountWithThisEmail";
        }
    }

    public UserAccount findUserByEmail(String email) {
        UserAccount userAcc = this.userAccountRepo.findOneByUserEmail(email);
        return userAcc;
    }

    public String editUserAccount(UserAccount userAcc, String newPassword, String newPassword2) {
        UserAccount userFromSession = (UserAccount)this.session.getAttribute("loggedUser");
        if (!userAcc.getUserFirstName().equals("")) {
            userFromSession.setUserFirstName(userAcc.getUserFirstName());
        }

        if (!userAcc.getUserLastName().equals("")) {
            userFromSession.setUserLastName(userAcc.getUserLastName());
        }

        if (!userAcc.getUserAddress().equals("")) {
            userFromSession.setUserAddress(userAcc.getUserAddress());
        }

        if (!userAcc.getUserPostCode().equals("")) {
            userFromSession.setUserPostCode(userAcc.getUserPostCode());
        }

        if (!userAcc.getUserCity().equals("")) {
            userFromSession.setUserCity(userAcc.getUserCity());
        }

        if (!userAcc.getUserEmail().equals("")) {
            userFromSession.setUserEmail(userAcc.getUserEmail());
        }

        if (!userAcc.getUserPassword().equals("")) {
            if (userAcc.getUserPassword().equals(userFromSession.getUserPassword())) {
                String message = this.passwordChangeValidation(newPassword, newPassword2);
                if (message.equals("changePasswordGood")) {
                    userFromSession.setUserPassword(newPassword);
                    this.session.setAttribute("loggedUser", userFromSession);
                    this.userAccountRepo.save(userFromSession);
                    return "changePasswordGood";
                } else {
                    return message;
                }
            } else {
                return "badCurrentPassword";
            }
        } else {
            this.session.setAttribute("loggedUser", userFromSession);
            this.userAccountRepo.save(userFromSession);
            return "";
        }
    }

    public String passwordChangeValidation(String password1, String password2) {
        if (password1.length() > 5 && password1.length() < 15 && password2.length() > 5 && password2.length() < 15) {
            return password1.equals(password2) ? "changePasswordGood" : "noMatchPasswords";
        } else {
            return "toShortPasswords";
        }
    }

    public String updateBalance(String email, int amount) {
        UserAccount user = this.getUserByEmail(email);
        if (user == null) {
            return "User dont exist";
        } else {
            user.setBalance(user.getBalance() + amount);
            this.userAccountRepo.save(user);
            return "Balance add correctly";
        }
    }

    public UserAccount getUserByEmail(String email) {
        return this.userAccountRepo.findOneByUserEmail(email);
    }
}
