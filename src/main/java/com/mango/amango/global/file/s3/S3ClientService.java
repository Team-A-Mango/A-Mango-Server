package com.mango.amango.global.file.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

import static com.mango.amango.global.file.ImageUtil.*;

@Service
@RequiredArgsConstructor
public class S3ClientService {

    private final static String DIR_NAME = "image";

    @Value("${cloud.aws.S3.bucket}")
    private String bucket;
    private final S3Client s3Client;

    public String upload(MultipartFile multipartFile) throws IOException {

        String randomName = UUID.randomUUID().toString();
        String fileName = DIR_NAME + "/" + randomName;
        String contentType = getFileContentType(multipartFile);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .contentType(contentType)
                .contentLength(multipartFile.getSize())
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));

        GetUrlRequest request = GetUrlRequest.builder().bucket(bucket).key(fileName).build();
        return s3Client.utilities().getUrl(request).toString();
    }
}
