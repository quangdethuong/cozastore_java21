package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.entity.ProductEntity;
import com.cybersoft.cozastore_java21.payload.response.ProductResponse;
import com.cybersoft.cozastore_java21.repository.ProductRepository;
import com.cybersoft.cozastore_java21.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductResponse> getProductByCategory(int idC) {
        List<ProductEntity> list = productRepository.findByCategoryId(idC);
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (ProductEntity data : list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setName(data.getName());
            productResponse.setImage(data.getImage());
            productResponse.setPrice(data.getPrice());

            productResponseList.add(productResponse);
        }
        return productResponseList;
    }
}
