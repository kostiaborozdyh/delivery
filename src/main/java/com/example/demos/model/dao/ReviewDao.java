package com.example.demos.model.dao;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.entity.Review;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {

    public static final String SQL_GET_REVIEWS = "SELECT d.id,  u.first_name, d.response, d.date\n" +
            "FROM delivery.reviews as d\n" +
            "join delivery.user as u on  d.user_id = u.id";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO delivery.reviews(user_id,response,date)  VALUES (?,?,?)";

    public static List<Review> getReviews() {
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
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reviewList;
    }

    public static void addReview(Integer id, String response, LocalDate date) {
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
        } catch (SQLException ex) {
            rollback(connection);
            ex.printStackTrace();
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
