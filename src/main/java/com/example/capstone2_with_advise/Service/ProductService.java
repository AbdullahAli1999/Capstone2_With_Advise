package com.example.capstone2_with_advise.Service;


import com.example.capstone2_with_advise.Api.ApiException;
import com.example.capstone2_with_advise.Model.Factory;
import com.example.capstone2_with_advise.Model.Product;
import com.example.capstone2_with_advise.Repository.FactoryRepository;
import com.example.capstone2_with_advise.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FactoryRepository factoryRepository;


    //GET
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //ADD
    public void addProduct(Product product){
        Factory fid = factoryRepository.findFactoriesById(product.getFactory_id());
        if(fid.getId().equals(product.getFactory_id())){
            productRepository.save(product);
        }
        throw new ApiException("Not found");
    }
    //UPDATE
    public void updateProduct(Integer id , Product product){
        Product oldProduct = productRepository.findProductById(id);
        if(oldProduct == null){
            throw new ApiException("Not found");
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity_in_stock(product.getQuantity_in_stock());
        oldProduct.setDescription(product.getDescription());
        productRepository.save(oldProduct);
    }

    //DELETE
    public void deleteProduct(Integer id){
        Product delProduct = productRepository.findProductById(id);
        if(delProduct == null){
            throw new ApiException("Not found");
        }
        productRepository.delete(delProduct);

    }
    //6.get all product from factory
    public List<Product> getProductFromFactory(Integer id){
        return productRepository.findProductByFactory_id(id);
    }
    //9. check if stock is null
    public List<Product> getStock(){
        return productRepository.findProductByQuantity_in_stock(0);
    }

    //10. re-stock
    public void reStockProduct(Integer pid,Integer amount){
        List<Product> product = getStock();
        for(Product p: product){
            if(p.getId().equals(pid)){
                p.setQuantity_in_stock(amount);
                productRepository.save(p);

            }
        }

        throw new ApiException("Not found");
    }

    //11. range of price
    public List<Product> rangePrice(Integer min,Integer max){
        return productRepository.findProductByPriceBetween(min, max);
    }
}