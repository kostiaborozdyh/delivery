package com.gmail.KostiaBorozdyh.security;

import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
/**
 * Security class
 */
public class Security {

    /**
     * Helper method for hashing password.
     *
     * @param password User password
     * @return hash User password
     */
    public static String hashPassword(final String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * Sending mail to user for restoring password by User Email
     *
     * @param email User Email
     *
     * @return code that we're sending to user
     */
    public static String restorePassword(String email) {
        String code = generateCode();
        SendEmail.send(email, CreateMessage.restorePassword(code));
        return code;
    }

    /**
     * Sending mail to user for taking parcel in post office by User Email
     *
     * @param email User Email
     *
     * @return code that we're sending to user
     */
    public static String sendCode(String email) {
        String code = generateCode();
        SendEmail.send(email, CreateMessage.sendCode(code));
        return code;
    }

    /**
     * Helper method for generate code.
     *
     * @return code that we're generating
     */
    private static String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf((int) (Math.random() * 9)));
        }
        return code.toString();
    }
}
