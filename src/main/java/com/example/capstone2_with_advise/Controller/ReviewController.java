package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.Review;
import com.example.capstone2_with_advise.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllReview(){
        return ResponseEntity.status(200).body(reviewService.getAllReview());
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody @Valid Review review){
        reviewService.addReview(review);
            return ResponseEntity.status(200).body(new ApiResponse("Review Added"));
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id,@RequestBody @Valid Review review){
         reviewService.updateReview(id, review);
            return ResponseEntity.status(200).body(new ApiResponse("Review is updated"));

    }

    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delReview(@PathVariable Integer id){
       reviewService.deleteReview(id);
            return ResponseEntity.status(200).body(new ApiResponse("Review Deleted"));

    }
    //13.
    @GetMapping("/top")
    public ResponseEntity getTopReviews() {
        return ResponseEntity.status(200).body(reviewService.getTopReviews());
    }
}
