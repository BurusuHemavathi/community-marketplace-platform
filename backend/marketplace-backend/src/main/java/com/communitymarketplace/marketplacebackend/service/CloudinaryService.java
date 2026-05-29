package com.communitymarketplace.marketplacebackend.service;
import com.communitymarketplace.marketplacebackend.service.CloudinaryService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;

@Service
public class CloudinaryService {


    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(
            MultipartFile file
    ) {

        try {

            Map uploadResult =
                    cloudinary.uploader().upload(
                            file.getBytes(),
                            ObjectUtils.emptyMap()
                    );

            return uploadResult
                    .get("secure_url")
                    .toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Image upload failed"
            );
        }
    }
}