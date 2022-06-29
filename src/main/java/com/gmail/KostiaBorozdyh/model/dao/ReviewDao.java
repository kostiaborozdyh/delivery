package com.gmail.KostiaBorozdyh.model.dao;

import com.gmail.KostiaBorozdyh.DB.DBHelper;
import com.gmail.KostiaBorozdyh.model.entity.Review;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Data access object for Review entity
 */
public class ReviewDao {
    private static final Logger log = Logger.getLogger(ReviewDao.class);

    public static final String SQL_GET_REVIEWS = "SELECT d.id,  u.first_name, d.response, d.date\n" +
            "FROM delivery.reviews as d\n" +
            "join delivery.user as u on  d.user_id = u.id";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO delivery.reviews(user_id,response,date)  VALUES (?,?,?)";

    /**
     * Return all reviews
     *
     * @return List of Review items. If any problems returns empty list.
     */
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
                log.info("Get list review from data base");
            }
        } catch (SQLException ex) {
            log.error("Problem with getting list review from data base");
            log.error("Exception - "+ ex);
        }
        return reviewList;
    }

    /**
     * Creating new review
     *
     * @param id User identifier
     * @param response User response
     * @param date LocalDate.now()
     */
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
            log.info("Add review to data base with userId - "+id);
        } catch (SQLException ex) {
            log.error("Problem with adding review to data base with userId - "+id);
            log.error("Exception - "+ ex);
            rollback(connection);
        } finally {
            close(pst);
            close(connection);
        }
    }

    /**
     * Closing PreparedStatement
     *
     * @param st PreparedStatement
     */
    private static void close(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                log.error("Problem with closing preparedStatement");
                log.error("Exception - "+ e);
            }
        }
    }

    /**
     * Closing Connection
     *
     * @param connection Connection
     */
    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Problem with closing connection");
                log.error("Exception - "+ e);
            }
        }
    }

    /**
     * Rollback
     *
     * @param connection Connection
     */
    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            log.error("Problem with rollback");
            log.error("Exception - "+ ex);
        }
    }
}
