package com.finalProject.FinalProject.controller;

import com.finalProject.FinalProject.dto.FileInfoDTO;
import com.finalProject.FinalProject.entity.FileData;
import com.finalProject.FinalProject.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class Controller {


    @Autowired
    private StorageService storageService;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        FileData message;
        try {
            message = storageService.uploadImageToFileSystem(file);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = null;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @GetMapping("/fileSystem")
    public ResponseEntity<?> getAllImage() throws IOException {
        List<FileInfoDTO> fileInfoDTOList = storageService.getAllImage();
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfoDTOList);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<?> downloadImageByID(@PathVariable("id") Long fileName) throws IOException {
        FileData imageData = storageService.downloadImageByID(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageData);

    }
}
