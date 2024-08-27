package com.practice.myappvs.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class FileUploadController {

    // @Autowired
    // private FileUploadHelper fileUploadHelper;

    @PostMapping("/ab")
    public String checkinnAb() {
        return "Hey yogi I am FIleController";
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
        try {
            // final String
            // UPLOAD_DIR="C:\\Users\\p\\Desktop\\Spring_Boot\\myappvs\\src\\main\\resources\\static\\image";
            // dynamic path bnana hh so

            // System.out.println("Hiiting fileuplosdController");
            // System.out.println(file.getOriginalFilename());
            // System.out.println(file.getSize());
            // System.out.println(file.getContentType());
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Empty file");
            }
            try {
                Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
                        .path(file.getOriginalFilename()).toUriString());
                // return ResponseEntity.ok("File is Successfully uploaded");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong try again");
    }
}
