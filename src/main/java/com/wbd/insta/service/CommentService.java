package com.wbd.insta.service;

import com.wbd.insta.model.Comment;
import com.wbd.insta.model.Post;
import com.wbd.insta.repository.CommentRepo;
import com.wbd.insta.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    public Comment addComment(Long postId, String comment){
        Post post= postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        Comment comment1= new Comment();
        comment1.setComments(comment);
        comment1.setPost(post);
        return commentRepo.save(comment1);
    }

    public String deleteComment(Long commentId){
        Comment comment= commentRepo.findById(commentId).orElseThrow(()->new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
        return "Comment deleted successfully!";
    }
}
