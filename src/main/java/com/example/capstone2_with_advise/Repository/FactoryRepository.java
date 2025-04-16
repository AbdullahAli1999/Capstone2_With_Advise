package com.example.capstone2_with_advise.Repository;
import com.example.capstone2_with_advise.Model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactoryRepository extends JpaRepository<Factory,Integer> {
    Factory findFactoriesById(Integer id);
    @Query("select f from Factory f where f.factory_name = ?1")
    List<Factory> findFactoryByFactory_name(String name);
}