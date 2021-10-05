package com.velog.server.service.ecryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PasswordEncryption {

    static public List<String> encryptPassword(String password) throws NoSuchAlgorithmException {
        List<String> hashedString = new ArrayList<String>();

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        String salt = new String(Base64.getEncoder().encode(bytes));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        md.update(password.getBytes());
        String hex = String.format("%0128x", new BigInteger(1, md.digest()));

        hashedString.add(hex);
        hashedString.add(salt);

        return hashedString;
    }
}
