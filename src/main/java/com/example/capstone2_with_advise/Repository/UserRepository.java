package com.example.capstone2_with_advise.Repository;


import com.example.capstone2_with_advise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    @Query("select u from User u where u.couponCode = ?1")
    User takeCoupon(String coupon);

}