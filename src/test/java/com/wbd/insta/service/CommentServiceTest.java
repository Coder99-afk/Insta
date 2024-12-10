package com.wbd.insta.service;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.wbd.insta.model.Comment;
import com.wbd.insta.model.Post;
import com.wbd.insta.repository.CommentRepo;
import com.wbd.insta.repository.PostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentServiceTest {

@Mock
private PostRepo postRepo;

@Mock
private CommentRepo commentRepo;

@InjectMocks
private CommentService commentService;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
public void testAddComment() {
    // Arrange
    Long postId = 1L;
    String commentText = "This is a test comment";
    Post post = new Post();
    post.setId(postId);

    Comment comment = new Comment();
    comment.setComments(commentText);
    comment.setPost(post);

    Mockito.when(postRepo.findById(postId)).thenReturn(Optional.of(post));
    Mockito.when(commentRepo.save(Mockito.any(Comment.class))).thenReturn(comment);

    // Act
    Comment savedComment = commentService.addComment(postId, commentText);

    // Assert
    assertNotNull(savedComment);
    assertEquals(commentText, savedComment.getComments());
    assertEquals(postId, savedComment.getPost().getId());
    Mockito.verify(commentRepo, Mockito.times(1)).save(Mockito.any(Comment.class));
}

    @Test
    public void testAddCommentPostNotFound() {
        // Arrange
        Long postId = 1L;
        String commentText = "This is a test comment";

        when(postRepo.findById(anyLong())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        try {
            commentService.addComment(postId, commentText);
        } catch (RuntimeException e) {
            assertEquals("Post not found", e.getMessage());
        }
    }
@Test
public void testDeleteComment() {
    // Arrange
    Long commentId = 1L;
    Comment comment = new Comment();
    comment.setId(commentId);

    when(commentRepo.findById(commentId)).thenReturn(Optional.of(comment));

    // Act
    String result = commentService.deleteComment(commentId);

    // Assert
    assertEquals("Comment deleted successfully!", result);
    Mockito.verify(commentRepo, Mockito.times(1)).delete(comment);
}

@Test
public void testDeleteCommentNotFound() {
    // Arrange
    Long commentId = 1L;

    when(commentRepo.findById(commentId)).thenReturn(Optional.empty());

    // Act & Assert
    try {
        commentService.deleteComment(commentId);
    } catch (RuntimeException e) {
        assertEquals("Comment not found", e.getMessage());
    }
}
}