package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.Product;
import com.example.capstone2_with_advise.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product){
        productService.addProduct(product);
            return ResponseEntity.status(200).body(new ApiResponse("Product Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product){
       productService.updateProduct(id, product);
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));

    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
    //9.
    @GetMapping("/out-of-stock")
    public ResponseEntity getStock(){
        List<Product> products = productService.getStock();
        return ResponseEntity.status(200).body(products);
    }
    //10.
    @PutMapping("/restock/{pid}/{amount}")
    public ResponseEntity reStockProduct(@PathVariable Integer pid,@PathVariable Integer amount){
    productService.reStockProduct(pid,amount);
            return ResponseEntity.status(200).body(new ApiResponse("Product restocked successfully."));

    }
    //11.
    @GetMapping("/range/{min}/{max}")
    public ResponseEntity findPriceByRange(@PathVariable Integer min,@PathVariable Integer max){
        return ResponseEntity.status(200).body(productService.rangePrice(min, max));
    }
}