package com.gmail.KostiaBorozdyh.security;

import com.gmail.KostiaBorozdyh.model.utils.CreateMessage;
import com.gmail.KostiaBorozdyh.model.utils.SendEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Security {
    public static String hashPassword(final String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static String restorePassword(String email) throws UnsupportedEncodingException, MessagingException {
        StringBuilder code = generateCode();
        SendEmail.send(email, CreateMessage.restorePassword(code.toString()));
        return code.toString();
    }

    public static String sendCode(String email) throws MessagingException, UnsupportedEncodingException {
        StringBuilder code = generateCode();
        SendEmail.send(email, CreateMessage.sendCode(code.toString()));
        return code.toString();
    }
    private static StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf((int) (Math.random() * 9)));
        }
        return code;
    }
}
