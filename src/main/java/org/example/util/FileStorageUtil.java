package org.example.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileStorageUtil {

    private final String uploadDir = "uploads/";

    public String storeFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return filePath.toString();
    }

    public byte[] loadFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public void deleteFile(String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }
}