package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.Factory;
import com.example.capstone2_with_advise.Model.Product;
import com.example.capstone2_with_advise.Service.FactoryService;
import com.example.capstone2_with_advise.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/factory")
@RequiredArgsConstructor
public class FactoryController {
    private final FactoryService factoryService;
    private final ProductService productService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllFactory(){
        return ResponseEntity.status(200).body(factoryService.getAllFactory());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addFactory(@RequestBody @Valid Factory factory){

        factoryService.addFactory(factory);
        return ResponseEntity.status(200).body(new ApiResponse("Factory Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateFactory(@PathVariable Integer id, @RequestBody @Valid Factory factory){
        factoryService.updateFactory(id, factory);
            return ResponseEntity.status(200).body(new ApiResponse("Factory updated"));

    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        factoryService.deleteFactory(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
    //6.Get product from factory
    @GetMapping("/get/{id}/products")
    public ResponseEntity getProductsByFactory(@PathVariable Integer id){
        List<Product> products = productService.getProductFromFactory(id);
        return ResponseEntity.status(200).body(products);
    }
    //14.
    @GetMapping("/name/{name}")
    public ResponseEntity searchByName(@PathVariable String name){
        return ResponseEntity.status(200).body(factoryService.searchByName(name));
    }
}
