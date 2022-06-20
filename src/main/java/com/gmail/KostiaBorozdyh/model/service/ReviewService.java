package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dao.ReviewDao;
import com.gmail.KostiaBorozdyh.model.entity.User;

import java.time.LocalDate;

public class ReviewService {

    public static void save(User user, String response){
        ReviewDao.addReview(user.getId(), response, LocalDate.now());
    }
}
