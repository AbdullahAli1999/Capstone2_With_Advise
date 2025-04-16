package com.example.capstone2_with_advise.Controller;


import com.example.capstone2_with_advise.Api.ApiResponse;
import com.example.capstone2_with_advise.Model.Shipment;
import com.example.capstone2_with_advise.Service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllShipment(){
        return ResponseEntity.status(200).body(shipmentService.getAllShipmets());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addShipment(@RequestBody @Valid Shipment shipment){
        shipmentService.addShipment(shipment);
            return ResponseEntity.status(200).body(new ApiResponse("Shipment Added"));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateShipment(@PathVariable Integer id, @RequestBody @Valid Shipment shipment){
         shipmentService.updateShipment(id, shipment);

            return ResponseEntity.status(200).body(new ApiResponse("User updated"));

    }
    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity delShipment(@PathVariable Integer id){
        shipmentService.deleteShipment(id);
        return ResponseEntity.status(200).body(new ApiResponse("DELETED"));
    }
    //2.Track
    @GetMapping("/track/{track}")
    public ResponseEntity tracking(@PathVariable String track){
        Shipment trackByNumber = shipmentService.tracking(track);
        return ResponseEntity.status(200).body(trackByNumber);
    }
    //12.
    @PutMapping("/mark/{id}")
    public ResponseEntity markDelivered(@PathVariable Integer id){
        Shipment shipment = shipmentService.markDelivered(id);
        return ResponseEntity.status(200).body(shipment);
    }
}