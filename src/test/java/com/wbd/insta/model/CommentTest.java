package com.wbd.insta.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


public class CommentTest {

    private Comment comment;
    private Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
        comment = new Comment();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(comment);
    }

    @Test
    public void testParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Comment paramComment = new Comment("Test comment", post, now);
        assertEquals("Test comment", paramComment.getComments());
        assertEquals(post, paramComment.getPost());
        assertEquals(now, paramComment.getCreatedAt());
    }

    @Test
    public void testGetAndSetComments() {
        comment.setComments("Test comment");
        assertEquals("Test comment", comment.getComments());
    }

    @Test
    public void testGetAndSetPost() {
        comment.setPost(post);
        assertEquals(post, comment.getPost());
    }

    @Test
    public void testOnCreate() {
        comment.onCreate();
        assertNotNull(comment.getCreatedAt());
    }
}