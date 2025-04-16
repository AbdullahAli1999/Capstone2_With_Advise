package com.example.capstone2_with_advise.Service;


import com.example.capstone2_with_advise.Api.ApiException;
import com.example.capstone2_with_advise.Model.Order;
import com.example.capstone2_with_advise.Model.OrderDetails;
import com.example.capstone2_with_advise.Model.Product;
import com.example.capstone2_with_advise.Repository.OrderDetailsRepository;
import com.example.capstone2_with_advise.Repository.OrderRepository;
import com.example.capstone2_with_advise.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //GET
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    //ADD
    public void addOrderDetails(OrderDetails orderDetails){
        Order oid = orderRepository.findOrderById(orderDetails.getOrder_id());
        Product pid = productRepository.findProductById(orderDetails.getProduct_id());
        if(oid.getId().equals(orderDetails.getOrder_id()) && pid.getId().equals(orderDetails.getProduct_id())){
            pid.setQuantity_in_stock(pid.getQuantity_in_stock() - orderDetails.getQuantity());
            productRepository.save(pid);
            orderDetails.setPrice(pid.getPrice());
            orderDetailsRepository.save(orderDetails);
        }
        throw new ApiException("Not found");
    }
    //UPDATE
    public void updateOrderDetails(Integer id , OrderDetails orderDetails){
        OrderDetails oldOrderDetails = orderDetailsRepository.findOrderDetailsById(id);
        if(orderDetails == null){
            throw new ApiException("Not found");
        }
        oldOrderDetails.setQuantity(orderDetails.getQuantity());
        orderDetailsRepository.save(orderDetails);
    }
    //DELETE
    public void deleteOrderDetails(Integer id){
        OrderDetails delOrderDetails = orderDetailsRepository.findOrderDetailsById(id);
        if(delOrderDetails == null){
            throw new ApiException("Not found");
        }
        orderDetailsRepository.delete(delOrderDetails);

    }
}