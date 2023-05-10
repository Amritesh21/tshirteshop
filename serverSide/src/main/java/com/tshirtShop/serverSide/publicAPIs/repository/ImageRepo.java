package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<PublicImage, Long> {
}
