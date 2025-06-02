package org.example.service;

import com.yandex.cloud.sdk.storage.Storage;
import com.yandex.cloud.sdk.storage.StorageFactory;
import com.yandex.cloud.sdk.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StorageService {

    private final Storage storage;

    public StorageService(@Value("${yandex.cloud.storage.endpoint}") String endpoint,
                          @Value("${yandex.cloud.storage.access-key}") String accessKey,
                          @Value("${yandex.cloud.storage.secret-key}") String secretKey) {
        StorageOptions options = StorageOptions.newBuilder()
                .setEndpoint(endpoint)
                .setCredentialsProvider(() -> accessKey, secretKey)
                .build();
        this.storage = StorageFactory.create(options);
    }

    public void uploadFile(String bucketName, String objectName, InputStream inputStream) {
        storage.bucket(bucketName).object(objectName).put(inputStream);
    }

    public InputStream downloadFile(String bucketName, String objectName) {
        return storage.bucket(bucketName).object(objectName).get();
    }
}
