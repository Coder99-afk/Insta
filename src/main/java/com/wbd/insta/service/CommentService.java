package com.wbd.insta.service;

import com.wbd.insta.model.Comment;
import com.wbd.insta.model.Post;
import com.wbd.insta.repository.CommentRepo;
import com.wbd.insta.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
//Adds comment to a post, identified using the post id.
    public Comment addComment(Long postId, String comment){
        Post post= postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        Comment newComment = new Comment();
        newComment.setComments(comment);
        newComment.setPost(post);
        Comment savedComment = commentRepo.save(newComment);
        return savedComment;
    }
//Deletes comments based on the comment id.
    public String deleteComment(Long commentId){
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
        String responseMessage = "Comment deleted successfully";
        System.out.println("deleteComment response: " + responseMessage);
        return responseMessage;
    }
}
