package com.travelBnb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

@Service
public class BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName){
        if (file.isEmpty()){
            throw new IllegalStateException("cannot upload empty file");
        }
        try {
            File convFile=new File(System.getProperty("java.io.tmpdir")+ "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            try {
                amazonS3.putObject(bucketName,convFile.getName(),convFile);
                return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();
            }catch (AmazonS3Exception s3Exception){
                return "unable to upload file :" +s3Exception.getMessage();
            }
        }catch (Exception e){
            throw new IllegalStateException("failed to upload file",e);
        }
    }
    public MultipartFile convert(String filePath) throws IOException {
        // Load the file from the specified path
        File file = new File(filePath);

        // Read the file content into array
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Convert byte array to a ByteArrayResource
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        // Create MultipartFile from Resource
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                // You can determine and set the content type here
                try {
                    return Files.probeContentType(file.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean isEmpty() {
                return file.length() == 0;
            }

            @Override
            public long getSize() {
                return file.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileContent;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(fileContent);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (InputStream inputStream = new ByteArrayInputStream(fileContent);
                     OutputStream outputStream = new FileOutputStream(dest)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        };

        return multipartFile;
    }
}