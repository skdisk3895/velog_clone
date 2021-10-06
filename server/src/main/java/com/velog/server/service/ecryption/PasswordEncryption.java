package com.velog.server.service.ecryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PasswordEncryption {

    static public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }

    static public String getHex(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        md.update(salt.getBytes());
        return String.format("%0128x", new BigInteger(1, md.digest()));
    }

    static public List<String> encryptPassword(String password) throws NoSuchAlgorithmException {
        List<String> hashedString = new ArrayList<String>();

        String salt = PasswordEncryption.getSalt();
        String hex = PasswordEncryption.getHex(password, salt);

        hashedString.add(hex);
        hashedString.add(salt);

        return hashedString;
    }

    static public String getEncryptPasswordBySalt(String password, String salt) throws NoSuchAlgorithmException {
        String hex = PasswordEncryption.getHex(password, salt);
        return hex;
    }
}
