package com.wbd.insta.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ImageTest {

    @Test
    public void testDefaultConstructor() {
        Image image = new Image();
        assertNull(image.getPost());
        assertNull(image.getImageName());
        assertNull(image.getImageType());
        assertNull(image.getImageData());
    }

    @Test
    public void testParameterizedConstructor() {
        Post post = new Post();
        String imageName = "testImage.jpg";
        String imageType = "image/jpeg";
        byte[] imageData = new byte[]{1, 2, 3};

        Image image = new Image(post, imageName, imageType, imageData);

        assertEquals(post, image.getPost());
        assertEquals(imageName, image.getImageName());
        assertEquals(imageType, image.getImageType());
        assertArrayEquals(imageData, image.getImageData());
    }

    @Test
    public void testGettersAndSetters() {
        Image image = new Image();

        Post post = new Post();
        image.setPost(post);
        assertEquals(post, image.getPost());

        String imageName = "testImage.jpg";
        image.setImageName(imageName);
        assertEquals(imageName, image.getImageName());

        String imageType = "image/jpeg";
        image.setImageType(imageType);
        assertEquals(imageType, image.getImageType());

        byte[] imageData = new byte[]{1, 2, 3};
        image.setImageData(imageData);
        assertArrayEquals(imageData, image.getImageData());
    }
}