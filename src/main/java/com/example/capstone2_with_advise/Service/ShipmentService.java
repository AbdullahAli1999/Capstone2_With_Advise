package com.example.capstone2_with_advise.Service;

import com.example.capstone2_with_advise.Api.ApiException;
import com.example.capstone2_with_advise.Model.Order;
import com.example.capstone2_with_advise.Model.Shipment;
import com.example.capstone2_with_advise.Repository.OrderRepository;
import com.example.capstone2_with_advise.Repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    //GET
    public List<Shipment> getAllShipmets() {
        return shipmentRepository.findAll();
    }
    //ADD
    public void addShipment(Shipment shipment){
        Order oid = orderRepository.findOrderById(shipment.getOrder_id());
        if(oid.getId().equals(shipment.getOrder_id())){
            shipment.setShipment_date(LocalDate.now());
            shipmentRepository.save(shipment);

        }
        throw new ApiException("Not found");
    }
    //UPDATE
    public void updateShipment(Integer id , Shipment shipment){
        Shipment oldShipment = shipmentRepository.findShipmentById(id);
        if(oldShipment == null){
            throw new ApiException("Not found");
        }
        oldShipment.setTracking_number(shipment.getTracking_number());
        oldShipment.setStatus(shipment.getStatus());
        oldShipment.setShipment_date(LocalDate.now());
        shipmentRepository.save(oldShipment);

    }
    //DELETE
    public void deleteShipment(Integer id){
        Shipment delShipment = shipmentRepository.findShipmentById(id);
        if(delShipment == null){
            throw new ApiException("Not found");
        }
        shipmentRepository.delete(delShipment);

    }

    //2.Tracking By number
    public Shipment tracking(String track){
        return shipmentRepository.byTrackingNumber(track);
    }

    //12. put  shipment status to DELIVERED if current status is IN_TRANSIT.
    public Shipment markDelivered(Integer id){
        Shipment shipment = shipmentRepository.findShipmentById(id);
        if(shipment == null ||!shipment.getStatus().equalsIgnoreCase("IN_TRANSIT")){
            return null;
        }
        shipment.setStatus("DELIVERED");
        shipment.setShipment_date(LocalDate.now());
        return shipmentRepository.save(shipment);


    }



}
