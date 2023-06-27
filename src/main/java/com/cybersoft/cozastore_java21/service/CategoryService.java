package com.cybersoft.cozastore_java21.service;

//import com.cybersoft.cozastore_java21.config.RedisConfig;
import com.cybersoft.cozastore_java21.entity.CategoryEntity;
import com.cybersoft.cozastore_java21.payload.response.CategoryResponse;
import com.cybersoft.cozastore_java21.repository.CategoryRepository;
import com.cybersoft.cozastore_java21.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override

    public List<CategoryResponse> getAllCategory() {
        System.out.println("Kiem tra category");
        List<CategoryResponse> categoryResponsesList = new ArrayList<>();

        if (redisTemplate.hasKey("listCategory")){
// nếu  có thì lấy giá trị lưu trên redis
            System.out.println("co giá trị trên redis");
            String data = redisTemplate.opsForValue().get("listCategory").toString();
            Type listType = new TypeToken<ArrayList<CategoryResponse>>(){}.getType();
            categoryResponsesList = new Gson().fromJson(data, listType);
        }
        else {
            System.out.println("ko giá trị trên redis");

            List<CategoryEntity> list = categoryRepository.findAll();

            for (CategoryEntity data : list) {
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setId(data.getId());
                categoryResponse.setName(data.getName());
                categoryResponsesList.add(categoryResponse);
            }
            Gson gson = new Gson();
            String data = gson.toJson(categoryResponsesList);

            redisTemplate.opsForValue().set("listCategory", data);
        }

        return categoryResponsesList;

    }
}
