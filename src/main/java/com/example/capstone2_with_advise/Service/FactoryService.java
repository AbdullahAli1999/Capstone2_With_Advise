package com.example.capstone2_with_advise.Service;


import com.example.capstone2_with_advise.Api.ApiException;
import com.example.capstone2_with_advise.Model.Factory;
import com.example.capstone2_with_advise.Repository.FactoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {
    private final FactoryRepository factoryRepository;

    //GET
    public List<Factory> getAllFactory() {
        return factoryRepository.findAll();
    }
    //ADD
    public void addFactory(Factory factory){
        factoryRepository.save(factory);
    }

    //UPDATE
    public void updateFactory(Integer id , Factory factory){
        Factory oldFactory = factoryRepository.findFactoriesById(id);
        if(oldFactory == null){
            throw new ApiException("Not found");
        }
        oldFactory.setFactory_name(factory.getFactory_name());
        oldFactory.setLocation(factory.getLocation());
        oldFactory.setPhone(factory.getPhone());
        oldFactory.setCommerical_register(factory.getCommerical_register());
        oldFactory.setEnvironmental_permit(factory.getEnvironmental_permit());
        factoryRepository.save(factory);
    }
    //DELETE
    public void deleteFactory(Integer id){
        Factory delFactory = factoryRepository.findFactoriesById(id);
        if(delFactory == null){
            throw new ApiException("Not found");
        }
        factoryRepository.delete(delFactory);

    }
    //14.search by name
    public List<Factory> searchByName(String name){
        return factoryRepository.findFactoryByFactory_name(name);
    }
}