package com.example.capstone2_with_advise.Service;


import com.example.capstone2_with_advise.Api.ApiException;
import com.example.capstone2_with_advise.Model.Order;
import com.example.capstone2_with_advise.Model.OrderDetails;
import com.example.capstone2_with_advise.Model.Product;
import com.example.capstone2_with_advise.Model.User;
import com.example.capstone2_with_advise.Repository.OrderDetailsRepository;
import com.example.capstone2_with_advise.Repository.OrderRepository;
import com.example.capstone2_with_advise.Repository.ProductRepository;
import com.example.capstone2_with_advise.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    //GET
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //ADD
    public void addOrder(Order order){
        User uid = userRepository.findUserById(order.getUser_id());
        if(uid.getId().equals(order.getUser_id())){
            uid.setTotalOrders(uid.getTotalOrders() + 1);
            userRepository.save(uid);
            order.setOrder_date(LocalDate.now());
            orderRepository.save(order);

        }
        throw new ApiException("Not found");
    }
    //UPDATE
    public void updateOrder(Integer id , Order order){
        Order oldOrder = orderRepository.findOrderById(id);
        if(oldOrder == null){
            throw new ApiException("Not found");
        }
        oldOrder.setOrder_date(LocalDate.now());
        oldOrder.setStatus(order.getStatus());
        orderRepository.save(oldOrder);

    }
    //DELETE
    public void deleteOrder(Integer id){
        Order delOrder = orderRepository.findOrderById(id);
        if(delOrder == null){
            throw new ApiException("Not found");
        }
        orderRepository.delete(delOrder);

    }
    //4.place order
    public void placeOrder(Integer userId, Integer productId, String coupon){
        User user = userRepository.findUserById(userId);
        Product product = productRepository.findProductById(productId);
        int price = product.getPrice();
        User rightCoupon = userRepository.takeCoupon(coupon);
        if(coupon != null && coupon.length() == 5){
            price = (int) (price * 0.9);
        }

        Order order = new Order();
        order.setStatus("PENDING");
        order.setUser_id(userId);
        orderRepository.save(order);

        OrderDetails details = new OrderDetails();
        details.setOrder_id(order.getId());
        details.setProduct_id(productId);
        details.setPrice(price);
        details.setQuantity(1);
        orderDetailsRepository.save(details);
    }

    //5.Return Order
    public Order returnOrder(Integer orderId){
        Order order = orderRepository.findOrderById(orderId);
        if (order == null){
            return null;
        }
        String status = order.getStatus();
        if("SHIPPED".equalsIgnoreCase(status) || "PENDING".equalsIgnoreCase(status) || "PROCESSING".equalsIgnoreCase(status)){
            order.setStatus("RETURNED");
            orderRepository.save(order);
            return order;
        }else if("RETURNED".equalsIgnoreCase(status)){
            return order;
        }else {
            return null;
        }
    }

    //7. get orders by status
    public List<Order> getOrderStatus(String status){
        return orderRepository.findOrderByStatus(status);

    }

    //8. Cancel order if not shipped
    public void cancelOrder(Integer id){
        Order order = orderRepository.findOrderById(id);
        if(order == null || order.getStatus().equalsIgnoreCase("SHIPPED") || order.getStatus().equalsIgnoreCase("RETURNED")){
            throw new ApiException("Not found");
        }
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}
