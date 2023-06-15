package com.cybersoft.cozastore_java21.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/demo")
public class DemoUploadFileController {
// Path : chứa toàn bộ hàm hỗ trợ sẵn liên quan tới đường dẫn
    @Value("${path.root}")
    private String sPath;

    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {
        // Định nghĩa đường dẫn

        Path rootPath = Paths.get(sPath);
        try{
            if (!Files.exists(rootPath)){
                // tạo folder ứng với lại đường dẫn nếu không tồn tại folder
                Files.createDirectory(rootPath);
            }
            // F:\CyberSoft\image21
            // resolve => "\"
            // file.getOriginalFilename() lấy tên file và định dạng
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            System.out.println("Loi" + e.getLocalizedMessage());
        }
        return new ResponseEntity<>("", HttpStatus.OK );
    }
}
