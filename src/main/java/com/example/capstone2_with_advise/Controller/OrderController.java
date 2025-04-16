package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.Order;
import com.example.capstone2_with_advise.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Order order){

        orderService.addOrder(order);
            return ResponseEntity.status(200).body(new ApiResponse("Order Added"));

    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @RequestBody @Valid Order order){

        orderService.updateOrder(id, order);

            return ResponseEntity.status(200).body(new ApiResponse("Order updated"));

    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
    //4.
    @PostMapping("/place/{userId}/{productId}/{coupon}")
    public ResponseEntity placeOrder(@PathVariable Integer userId,@PathVariable Integer productId,@PathVariable String coupon){
        orderService.placeOrder(userId,productId,coupon);
        return ResponseEntity.status(200).body(new ApiResponse("Order placed"));
    }
    //5.
    @PostMapping("/return/{orderId}")
    public ResponseEntity returnOrder(@PathVariable Integer orderId){
        orderService.returnOrder(orderId);
        return ResponseEntity.status(400).body(new ApiResponse("Order returned"));
    }

    //7.
    @GetMapping("/get-status/{status}")
    public ResponseEntity getStatusOrder(@PathVariable String status){
        return ResponseEntity.status(200).body(orderService.getOrderStatus(status));
    }

    //8.
    @PutMapping("/cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable Integer id){
       orderService.cancelOrder(id);
            return ResponseEntity.status(200).body(new ApiResponse("Order cancelled"));

    }

}
