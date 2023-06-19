package com.cybersoft.cozastore_java21.controller;


import com.cybersoft.cozastore_java21.exception.CustomFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/{filename}")
    public ResponseEntity<?> loadfile(@PathVariable String filename){
        try {
            //đường dẫn folder lưu hình
            Path rootPath = Paths.get(sPath);
            Resource resource = new UrlResource(rootPath.resolve(filename).toUri());
            if (resource.exists()){
                // neu ton tai thi cho phep download
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
            else {
                throw new CustomFileNotFoundException(200, "File not found"); // code se stop ngay neu gap loi
            }
        }catch (Exception e){
            throw new CustomFileNotFoundException(200, "File not found"); // code se stop ngay neu gap loi
        }
    }
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
