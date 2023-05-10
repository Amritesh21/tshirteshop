package com.tshirtShop.serverSide.publicAPIs.controller;

import com.tshirtShop.serverSide.publicAPIs.DTO.PublicImageList;
import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;
import com.tshirtShop.serverSide.publicAPIs.repository.ImageRepo;
import com.tshirtShop.serverSide.publicAPIs.repository.UploadPublicImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/public")
public class GetAllImages {

    @Autowired
    UploadPublicImageRepo uploadPublicImageRepo;

    @GetMapping("test")
    public String testResponse() {
        return "tested ok";
    }

    @GetMapping(value = "getImageList")
    public PublicImageList getImageList() {
       List<String> publicImagesList = uploadPublicImageRepo.fetchAllPublicImages();
       publicImagesList.stream().forEach(x -> System.out.println(x));
       PublicImageList publicImageListDTO = new PublicImageList(publicImagesList);
       return publicImageListDTO;
    }

    @GetMapping("getImage/{id}")
    public ResponseEntity<byte []> getImage(@PathVariable("id") Long id) {
        PublicImage publicImage = uploadPublicImageRepo.fetchImageById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(publicImage.getImageBytes(), httpHeaders, HttpStatus.OK);
    }


}
