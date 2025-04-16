package com.example.capstone2_with_advise.Repository;


import com.example.capstone2_with_advise.Model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
    Shipment findShipmentById(Integer id);
    @Query("select s from Shipment  s where  s.tracking_number = ?1")
    Shipment byTrackingNumber(String track);
    @Query("select s from Shipment s where s.status = 'IN_TRANSIT'")
    Shipment findShipmentByStatus(String status);
}