package com.tshirtShop.serverSide.publicAPIs.controller;

import com.tshirtShop.serverSide.publicAPIs.DTO.UploadResponse;
import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;
import com.tshirtShop.serverSide.publicAPIs.repository.ImageRepo;
import com.tshirtShop.serverSide.publicAPIs.repository.UploadPublicImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/public")
public class UploadImage {

    @Autowired
    ImageRepo imageRepo;

    @Autowired
    UploadPublicImageRepo uploadPublicImageRepo;

    @PostMapping("uploadImage")
    public UploadResponse setNewImage(@RequestParam("image") MultipartFile multipartFile) {
        UploadResponse uploadResponse = new UploadResponse();
        try {
            byte[] imageBytes = multipartFile.getBytes();
            PublicImage publicImage = new PublicImage();
            // publicImage.setFileName(multipartFile.getOriginalFilename());
            publicImage.setImageBytes(imageBytes);
            // imageRepo.save(publicImage);
            uploadPublicImageRepo.imageUploader(publicImage);
            uploadResponse.setMessage("Image Upload SuccessFull");
            uploadResponse.setStatus("Success");
        }
        catch (IOException e) {
            uploadResponse.setMessage("Error while uploading Image");
            uploadResponse.setStatus("Error");
        }
        return uploadResponse;
    }

}
