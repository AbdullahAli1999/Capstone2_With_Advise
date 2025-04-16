package com.example.capstone2_with_advise.Repository;

import com.example.capstone2_with_advise.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);
    List<Order> findOrderByStatus(String status);
    @Query("select o from Order o where o.user_id = ?1")
    List<Order> findOrderByUser_id(Integer id);
}
