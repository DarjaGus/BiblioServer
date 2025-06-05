package org.example.service;

import org.example.util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Autowired
    private FileStorageUtil fileStorageUtil;

    public String storeFile(MultipartFile file) throws IOException {
        return fileStorageUtil.storeFile(file);
    }

    public byte[] loadFile(String filePath) throws IOException {
        return fileStorageUtil.loadFile(filePath);
    }

    public void deleteFile(String filePath) throws IOException {
        fileStorageUtil.deleteFile(filePath);
    }

    public void uploadFile(String fileName, String tempFilePath) throws IOException {
        Path tempFile = Paths.get(tempFilePath);
        Path targetFile = Paths.get("uploads/" + fileName);
        Files.move(tempFile, targetFile);
    }

    public void downloadFile(String fileName, String tempFilePath) throws IOException {
        Path sourceFile = Paths.get("uploads/" + fileName);
        Path tempFile = Paths.get(tempFilePath);
        Files.copy(sourceFile, tempFile);
    }
}