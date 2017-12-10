package application.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userAccount")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String userFirstName;

    @NotNull
    private String userLastName;

    @NotNull
    private String userAddress;

    @NotNull
    private String userPostCode;

    @NotNull
    private String userCity;

    @NotNull
    private long userPhone;

    @NotNull
    private String workerCode;

    @NotNull
    private String userEmail;

    @NotNull
    private String userPassword;

    public UserAccount() {
    }

    public UserAccount(long id) {
        this.id = id;
    }

    public UserAccount(String userFirstName, String userLastName, String userEmail, String userPassword) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPostCode() {
        return userPostCode;
    }

    public String getUserCity() {
        return userCity;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPostCode(String userPostCode) {
        this.userPostCode = userPostCode;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
}
