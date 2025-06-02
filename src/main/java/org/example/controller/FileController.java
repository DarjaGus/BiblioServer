package org.example.controller;

import org.example.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String bucketName = "your-bucket-name";
        String objectName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        storageService.uploadFile(bucketName, objectName, inputStream);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileName") String fileName) throws IOException {
        String bucketName = "your-bucket-name";
        InputStream fileStream = storageService.downloadFile(bucketName, fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
}
