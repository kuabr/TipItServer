package de.tipit.server.transfer.data;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "UserAccount")
public class UserAccountTO implements Serializable {

    private static final long serialVersionUID = 3631917949945996427L;

    private String userName;

    private String password;

    public static byte[] encryptPassword(String readablePassword) {
        MessageDigest digest;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exc) {
            return readablePassword.getBytes(); // do not encrypt :-(
        }
        digest.update(readablePassword.getBytes());
        return digest.digest();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public byte[] getEncryptedPassword() {
        return encryptPassword(password);
    }
}
