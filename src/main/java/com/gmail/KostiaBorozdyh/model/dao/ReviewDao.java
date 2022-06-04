package com.gmail.KostiaBorozdyh.model.dao;

import com.gmail.KostiaBorozdyh.DB.DBHelper;
import com.gmail.KostiaBorozdyh.model.entity.Review;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    private static final Logger log = Logger.getLogger(ReviewDao.class);

    public static final String SQL_GET_REVIEWS = "SELECT d.id,  u.first_name, d.response, d.date\n" +
            "FROM delivery.reviews as d\n" +
            "join delivery.user as u on  d.user_id = u.id";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO delivery.reviews(user_id,response,date)  VALUES (?,?,?)";

    public static List<Review> getReviews() {
        log.info("Вибірка всіх відгуків");
        List<Review> reviewList = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_REVIEWS)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getInt("id"));
                    review.setFirstName(rs.getString("first_name"));
                    review.setResponse(rs.getString("response"));
                    review.setDate(rs.getDate("date").toLocalDate());
                    reviewList.add(review);
                }
                log.info("Вибірка всіх відгуків завершена");
            }
        } catch (SQLException ex) {
            log.error("Помилка вибірки всіх відгуків " + ex);
        }
        return reviewList;
    }

    public static void addReview(Integer id, String response, LocalDate date) {
        log.info("Додавання відгуку");
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            pst = connection.prepareStatement(SQL_INSERT_REVIEW);
            connection.setAutoCommit(false);
            pst.setInt(1, id);
            pst.setString(2, response);
            pst.setDate(3, Date.valueOf(date));
            pst.executeUpdate();
            connection.commit();
            log.info("Додавання відгуку завершено");
        } catch (SQLException ex) {
            log.error("Помилка, додавання відгуку "+ex);
            rollback(connection);
        } finally {
            close(pst);
            close(connection);
        }
    }

    private static void close(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
