package com.wbd.insta.repository;

import com.wbd.insta.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    @Query("SELECT i.imageData FROM Image i WHERE i.id = :imageId")
    byte[] findImageDataById(@Param("imageId") Long imageId);
}
