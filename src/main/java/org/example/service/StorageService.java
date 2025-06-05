package org.example.service;

import org.example.util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}