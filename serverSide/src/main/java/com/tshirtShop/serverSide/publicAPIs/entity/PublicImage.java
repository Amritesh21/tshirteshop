package com.tshirtShop.serverSide.publicAPIs.entity;

import javax.persistence.*;

@Entity
public class PublicImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    byte [] imageBytes;

    public PublicImage() {
    }

    public PublicImage(byte [] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
