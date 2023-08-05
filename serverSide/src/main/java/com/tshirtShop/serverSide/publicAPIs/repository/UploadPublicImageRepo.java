package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.entity.ProductImageList;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public List<ProductList> fetchAllProductImage(String username) {
        Query query = entityManager.createQuery("Select pl from ProductList pl where pl.username = :username", ProductList.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public ProductList fetchProductDetails(Long id) {
        ProductList productList = entityManager.find(ProductList.class, id);
        return productList;
    }

    public byte[] fetchProductThumbNail(String username, Long productId) {
        Query query = entityManager.createQuery("Select pl from ProductList pl where pl.username =  :username and pl.productId = :productId", ProductList.class);
        query.setParameter("username", username);
        query.setParameter("productId", productId);
        List<ProductList> product = query.getResultList();
        return product.get(0).getThumbNailImage();
    }

    public void saveNewProduct(ProductList productList) {
        entityManager.persist(productList);
    }

    public void clearExistingProductImage(Long productId) {
        Query query = entityManager.createQuery("Delete from ProductImageList pil where pil.productList.productId = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    public List<ProductImageList> fetchProductImageMeta(Long productId) {
        Query query = entityManager.createQuery("Select pil from ProductImageList pil where pil.productList.productId = :productId", ProductImageList.class);
        query.setParameter("productId", productId);
        List<ProductImageList> productImageList = query.getResultList();
        return productImageList;
    }

    public ProductImageList fetchProductImage(Long imageId) {
        Query query = entityManager.createQuery("Select pil from ProductImageList pil where pil.imageId = :imageId");
        query.setParameter("imageId", imageId);
        List<ProductImageList> productImageLists = query.getResultList();
        return productImageLists.get(0);
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
