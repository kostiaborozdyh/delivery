package com.example.demos.security;

import com.example.demos.model.CreateMessage;
import com.example.demos.model.SendEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Security {
    public static String hashPassword(final String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes){
            sb.append(String.format("%02X",b));
        }
        return sb.toString();
    }
    public static String restorePassword(String email) throws UnsupportedEncodingException, MessagingException {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf((int) (Math.random()*9)));
        }
        String[] message = CreateMessage.restorePassword(code.toString());
        SendEmail.send(email,message[0],message[1]);
        return code.toString();
    }
    public static String sendCode(String email) throws MessagingException, UnsupportedEncodingException {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf((int) (Math.random()*9)));
        }
        String[] message = CreateMessage.sendCode(code.toString());
        SendEmail.send(email,message[0],message[1]);
        return code.toString();
    }
}
