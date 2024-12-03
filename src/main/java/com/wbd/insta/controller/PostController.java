package com.wbd.insta.controller;

import com.wbd.insta.dto.CustomDTO;
import com.wbd.insta.model.Comment;
import com.wbd.insta.model.Post;
import com.wbd.insta.service.CommentService;
import com.wbd.insta.service.ImageService;
import com.wbd.insta.service.PostService;
import org.postgresql.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;
    @GetMapping("/post")
    public List<CustomDTO> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "1", required = false)int pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "3", required = false)int pageSize) throws IOException {
        return postService.getAllPosts(pageNumber,pageSize);
    }
    @GetMapping("/images/{postId}")
    public ResponseEntity<List<MultipartFile>> retrieveImageForPost(@PathVariable long postId) throws IOException {
        return new ResponseEntity<>(imageService.retrieveImagesForPost(postId),HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestParam("caption") String caption,
                                           @RequestBody List<MultipartFile> images) throws IOException {
        Post post= postService.createPost(caption, images);
        return ResponseEntity.ok(post);
    }
    @PostMapping("/comment/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable long postId, @RequestParam String comment){
        return new ResponseEntity<>(commentService.addComment(postId,comment), HttpStatus.OK);
    }
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }

}
