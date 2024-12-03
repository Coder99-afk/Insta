package com.wbd.insta.service;

import com.wbd.insta.dto.CustomDTO;
import com.wbd.insta.model.Image;
import com.wbd.insta.model.Post;
import com.wbd.insta.repository.ImageRepo;
import com.wbd.insta.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private ImageService imageService;
    public List<CustomDTO> getAllPosts(int pageNumber, int pageSize) throws IOException {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        return postRepo.findAllPosts(pageable);
    }

    public Post createPost(String caption, List<MultipartFile> images) throws IOException {
        Post post= new Post();
        post.setCaption(caption);
        Post p1 = postRepo.save(post);
        List<Image> imageList= new ArrayList<>();
        for(MultipartFile image: images){
            String imgFile= imageService.saveImage(image,post.getId());
            System.out.println(imgFile);
            Image img= new Image();
            img.setImageName(image.getOriginalFilename());
            img.setImageType(image.getContentType());
            img.setImageData(image.getBytes());
            img.setPost(p1);
            imageList.add(img);
            imageRepo.save(img);
        }
        post.setImage(imageList);
        return postRepo.save(post);
    }
}
