package ir.maktab.model.user;

import ir.maktab.model.role.Role;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hamed-Abbaszadeh -> 09385136659 on 2/19/2018.
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role ;

    public User(){}
    public User(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = hashGenerator(password);
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public static String hashGenerator(String password)
    {
        String myHash = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            System.out.println("digest=  "+digest);
            myHash = DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println("return hash= " + myHash);
        return myHash;
    }
}
