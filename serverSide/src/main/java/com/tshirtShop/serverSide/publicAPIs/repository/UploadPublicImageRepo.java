package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UploadPublicImageRepo {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean imageUploader(PublicImage publicImage) {
        try {
            entityManager.persist(publicImage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public PublicImage fetchImageById(Long id) {
        PublicImage publicImage = entityManager.find(PublicImage.class, id);
        return publicImage;
    }

    public List<String> fetchAllPublicImages() {
        List<String> publicImages = (List<String>) entityManager.createNativeQuery("Select id from public_image").getResultList().stream().map(x -> x.toString()).collect(Collectors.toList());
        return publicImages;
    }

    /*public boolean imageUploader(PublicImage publicImage) {
        try {
            SqlLobValue imageBytes = new SqlLobValue(publicImage.getImageBytes());
            jdbcTemplate.update("INSERT INTO public_image (image_bytes) VALUES (?)", imageBytes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/

}
