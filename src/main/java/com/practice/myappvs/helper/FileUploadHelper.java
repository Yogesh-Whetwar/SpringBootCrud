package com.practice.myappvs.helper;


import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileUploadHelper {
 public final String UPLOAD_DIR="C:\\Users\\p\\Desktop\\Spring_Boot\\myappvs\\src\\main\\resources\\static\\image";

 public boolean uploadFile(MultipartFile mpf){
    boolean f=false;
   try{
    Files.copy(mpf.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+mpf.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
    f=true;
   }catch(Exception e){
     e.printStackTrace();
   }
    return f;
 }

}
