package com.wbd.insta.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class CustomDTOTest {

    @Test
    public void testCustomDTOConstructorAndGetters() {
        // Arrange
        Long postId = 1L;
        String caption = "Test Caption";
        Long commentId = 2L;
        String comments = "Test Comments";

        // Act
        CustomDTO customDTO = new CustomDTO(postId, caption, commentId, comments);

        // Assert
        assertNotNull(customDTO);
        assertEquals(postId, customDTO.getPostId());
        assertEquals(caption, customDTO.getCaption());
        assertEquals(commentId, customDTO.getCommentId());
        assertEquals(comments, customDTO.getComments());
    }

    @Test
    public void testCustomDTOSetters() {
        // Arrange
        CustomDTO customDTO = new CustomDTO();
        Long postId = 1L;
        String caption = "Test Caption";
        Long commentId = 2L;
        String comments = "Test Comments";

        // Act
        customDTO.setPostId(postId);
        customDTO.setCaption(caption);
        customDTO.setCommentId(commentId);
        customDTO.setComments(comments);

        // Assert
        assertEquals(postId, customDTO.getPostId());
        assertEquals(caption, customDTO.getCaption());
        assertEquals(commentId, customDTO.getCommentId());
        assertEquals(comments, customDTO.getComments());
    }

    @Test
    public void testCustomDTOToString() {
        // Arrange
        Long postId = 1L;
        String caption = "Test Caption";
        Long commentId = 2L;
        String comments = "Test Comments";
        CustomDTO customDTO = new CustomDTO(postId, caption, commentId, comments);

        // Act
        String toString = customDTO.toString();

        // Assert
        assertNotNull(toString);
        assertEquals("CustomDTO{" +
                "postId=" + postId +
                ", caption='" + caption + '\'' +
                ", commentId=" + commentId +
                ", comments='" + comments + '\'' +
                '}', toString);
    }
}
