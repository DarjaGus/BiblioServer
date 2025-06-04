package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

@Service
public class StorageService {

    private final S3Client s3Client;

    @Value("${yandex.cloud.storage.bucket-name}")
    private String bucketName;

    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String key, String filePath) {
        File file = new File(filePath);
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(request, RequestBody.fromFile(file));
        System.out.println("File uploaded successfully.");
    }

    public InputStream downloadFile(String key, String downloadPath) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.getObject(getRequest, ResponseTransformer.toFile(Path.of(downloadPath)));
        System.out.println("File downloaded successfully.");
        return null;
    }
}