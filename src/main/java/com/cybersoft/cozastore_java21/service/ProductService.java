package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.payload.response.ProductResponse;
import com.cybersoft.cozastore_java21.service.imp.ProductServiceImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Override
    public List<ProductResponse> getProductByCategory(int idC) {
        return null;
    }
}
