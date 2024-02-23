package assignment3;
import java.util.ArrayList;
public class Player {
    private final String account = generateAccount();
    private String password;
    private Mail mail;
    private PhoneNumber phoneNumber;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public Player(Mail mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Player(PhoneNumber phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Player(Mail mail, PhoneNumber phoneNumber, String password) {
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public boolean checkIdentity(Mail mail, String password) {
        if (this.mail.mail.equals(mail.mail) && this.password.equals(password)) return true;
        else return false;
    }

    public boolean checkIdentity(PhoneNumber phoneNumber, String password) {
        if (this.phoneNumber.phoneNumber.equals(phoneNumber.phoneNumber) && this.password.equals(password)) return true;
        else return false;
    }

    public boolean setMail(PhoneNumber phoneNumber, String password, Mail mail) {
        if (checkIdentity(phoneNumber, password)) {
            this.mail = mail;
            return true;
        } else return false;
    }

    public boolean setPhoneNumber(Mail mail, String password, PhoneNumber phoneNumber) {
        if (checkIdentity(mail, password)) {
            this.phoneNumber = phoneNumber;
            return true;
        } else return false;
    }

    public String generateAccount() {
        StringBuilder str = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                str.append(1 + (int) (9 * Math.random()));
            } else {
                str.append((int) (10 * Math.random()));
            }
        }
        return String.valueOf(str);
    }

    public String getAccount() {
        return account;
    }

    public Mail getMail() {
        return mail;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean changePassword(PhoneNumber phoneNumber, String oldPassword, String newPassword) {
        if (checkIdentity(phoneNumber, oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    public boolean changePassword(Mail mail, String oldPassword, String newPassword) {
        if (checkIdentity(mail, oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

}
class Mail {
    public String mail;

    public Mail(String mail) {
        this.mail = mail;
    }
}
class PhoneNumber{
    public String phoneNumber;
    public PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}

