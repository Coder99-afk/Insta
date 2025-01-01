package com.wbd.insta.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostTest {

    private Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(post);
    }

    @Test
    public void testParameterizedConstructor() {
        List<Image> images = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Post post = new Post("Sample caption", images, comments);

        assertEquals("Sample caption", post.getCaption());
        assertEquals(images, post.getImage());
        assertEquals(comments, post.getComment());
    }

    @Test
    public void testGetAndSetCaption() {
        post.setCaption("Sample caption");
        assertEquals("Sample caption", post.getCaption());
    }

    @Test
    public void testGetAndSetImage() {
        List<Image> images = new ArrayList<>();
        post.setImage(images);
        assertEquals(images, post.getImage());
    }

    @Test
    public void testGetAndSetComment() {
        List<Comment> comments = new ArrayList<>();
        post.setComment(comments);
        assertEquals(comments, post.getComment());
    }
}