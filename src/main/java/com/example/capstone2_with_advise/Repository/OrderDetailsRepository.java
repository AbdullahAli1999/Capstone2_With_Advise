package com.example.capstone2_with_advise.Repository;

import com.example.capstone2_with_advise.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    OrderDetails findOrderDetailsById(Integer id);
    @Query("select o from OrderDetails o where o.order_id = ?1")
    List<OrderDetails> findByOrder_id(Integer orderId);
}
