package com.example.capstone2_with_advise.Repository;

import com.example.capstone2_with_advise.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);
    List<Review> findReviewByUserId(Integer id);


}