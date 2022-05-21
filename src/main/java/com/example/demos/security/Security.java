package com.example.demos.security;

import com.example.demos.model.SendEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Security {
    public static final String THEME = "Відновнення паролю";
    public static final String MAIL = "Для того щоб відновити пароль, введіть данний код:";


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
        StringBuilder mail = new StringBuilder(MAIL);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf((int) (Math.random()*9)));
        }
        mail.append(code);
        SendEmail.send(email,THEME,mail.toString());
        return code.toString();
    }
}
