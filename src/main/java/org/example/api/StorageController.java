package org.example.api;

import org.example.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile.toFile());
        storageService.uploadFile(file.getOriginalFilename(), tempFile.toString());
        return "File uploaded successfully.";
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName) throws IOException {
        Path tempFile = Files.createTempFile("download-", fileName);
        storageService.downloadFile(fileName, tempFile.toString());
        return "File downloaded successfully.";
    }
}