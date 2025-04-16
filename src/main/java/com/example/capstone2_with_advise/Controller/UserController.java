package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.User;
import com.example.capstone2_with_advise.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllusers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user){
        userService.updateUser(id, user);
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));

    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
    //1.Top buyers
    @GetMapping("/get-top")
    public ResponseEntity<List<User>> getTopBuyers(){
        List<User> topBuyers = userService.getTopBuyers();
        return ResponseEntity.status(200).body(topBuyers);
    }
    //3. checkDiscount
    @GetMapping("/discount/{id}")
    public ResponseEntity checkUserDiscount(@PathVariable Integer id){
        return ResponseEntity.status(200).body(userService.checkDiscount(id));
    }
    //17.
//    @GetMapping("/users/most-active")
//    public ResponseEntity<List<User>> getMostActiveUsers() {
//        return ResponseEntity.status(200).body(userService.getMostActiveUsers());
//    }

}