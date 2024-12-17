package com.wbd.insta.repository;

import com.wbd.insta.dto.CustomDTO;
import com.wbd.insta.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
//    @Query("SELECT p.id AS postId, p.caption, c.id AS commentId, c.comments, i.id AS imageId, i.imageName, i.imageType, i.imageData " +
//            "FROM Post p " +
//            "LEFT JOIN p.comment c " +
//            "LEFT JOIN p.image i " +
//            "WHERE (SELECT COUNT(c2) FROM Comment c2 WHERE c2.post = p AND c2.createdAt >= c.createdAt) <= 2 " +
//            "ORDER BY SIZE(p.comment) DESC, c.createdAt DESC")
@Query("SELECT new com.wbd.insta.dto.CustomDTO(p.id, p.caption, COALESCE(c.id, 0), COALESCE(c.comments, ''))" +
        "FROM Post p " +
        "LEFT JOIN p.comment c " +
        "WHERE (SELECT COUNT(c2) FROM Comment c2 WHERE c2.post = p AND c2.createdAt >= c.createdAt) <= 2 " +
        "ORDER BY SIZE(p.comment) DESC, c.createdAt DESC")
   List<CustomDTO> findAllPosts(Pageable pageable);

}

