package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.OrderDetails;
import com.example.capstone2_with_advise.Service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/details")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllOrderDetaols(){
        return ResponseEntity.status(200).body(orderDetailsService.getAllOrderDetails());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addOrderDetails(@RequestBody @Valid OrderDetails orderDetails){
        orderDetailsService.addOrderDetails(orderDetails);
            return ResponseEntity.status(200).body(new ApiResponse("Order Details Added"));

    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrderDetails(@PathVariable Integer id, @RequestBody @Valid OrderDetails orderDetails){
         orderDetailsService.updateOrderDetails(id, orderDetails);

            return ResponseEntity.status(200).body(new ApiResponse("Order Details updated"));


    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        orderDetailsService.deleteOrderDetails(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
}