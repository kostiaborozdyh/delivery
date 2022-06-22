package com.gmail.KostiaBorozdyh.model.utils;

import com.gmail.KostiaBorozdyh.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {


    @Test
    public void Validation() {
        User user = new User();
        user.setLogin("user");
        user.setFirstName("Костя");
        user.setLastName("Бороздих");
        user.setPassword("qazwsxedcA7");
        user.setSecondPassword("qazwsxedcA7");
        user.setEmail("kostiaborozdyh@gmail.com");
        user.setPhoneNumber("+380938988645");
        assertTrue(Validation.count(Validation.valid(user, false,false)));
        User user2 = user.cloneUser();
        user2.setPassword("qazwsxedcA7");
        user2.setSecondPassword("qazwsxedcA7");
        user2.setLogin("user256");
        assertFalse(Validation.count(Validation.valid(user2, true,true)));
        user2.setEmail("kotikm@gmail.com");
        assertTrue(Validation.count(Validation.valid(user2, true,true)));
    }
}
