package com.example.demos.model;

import com.example.demos.DB.DBHelper;
import com.example.demos.model.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {

    public static final String SQL_GET_REVIEWS = "SELECT d.id,  u.first_name, d.response\n" +
            "FROM delivery.reviews as d\n" +
            "join delivery.user as u on  d.user_id = u.id";

    public static final String SQL_INSERT_REVIEW = "INSERT INTO delivery.reviews(user_id,response)  VALUES (?,?)";

    public static List<Review> getReviews() {
        List<Review> reviewList = new ArrayList<>();
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_REVIEWS) ) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getInt("id"));
                    review.setFirstName(rs.getString("first_name"));
                    review.setResponse(rs.getString("response"));
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
    public static void addReview(Integer id, String response){
        try( Connection connection = DBHelper.getInstance().getConnection();
             PreparedStatement  st = connection.prepareStatement(SQL_INSERT_REVIEW)) {
            st.setInt(1,id);
            st.setString(2,response);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
