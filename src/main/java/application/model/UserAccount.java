package application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "userAccount"
)
public class UserAccount {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
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
    private int balance;

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
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
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
        return this.userAddress;
    }

    public String getUserPostCode() {
        return this.userPostCode;
    }

    public String getUserCity() {
        return this.userCity;
    }

    public long getUserPhone() {
        return this.userPhone;
    }

    public String getWorkerCode() {
        return this.workerCode;
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

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
