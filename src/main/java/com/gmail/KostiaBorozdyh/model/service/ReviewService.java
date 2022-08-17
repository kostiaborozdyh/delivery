package com.gmail.KostiaBorozdyh.model.service;

import com.gmail.KostiaBorozdyh.model.dao.ReviewDao;
import com.gmail.KostiaBorozdyh.model.entity.Review;
import com.gmail.KostiaBorozdyh.model.entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Review service
 */
public class ReviewService {

    public static void save(User user, String response) {
        ReviewDao.addReview(user.getId(), response, LocalDate.now());
    }

    public static List<Review> getAllReviews() {
        return ReviewDao.getReviews();
    }
}
