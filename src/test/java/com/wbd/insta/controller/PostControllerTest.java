package com.wbd.insta.controller;

import com.wbd.insta.dto.CustomDTO;
import com.wbd.insta.model.Comment;
import com.wbd.insta.model.Post;
import com.wbd.insta.service.CommentService;
import com.wbd.insta.service.ImageService;
import com.wbd.insta.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private ImageService imageService;

    @Test
    public void testGetAllPosts() throws Exception {
        CustomDTO customDTO = new CustomDTO();
        // Initialize customDTO with necessary data

        when(postService.getAllPosts(1, 3)).thenReturn(Collections.singletonList(customDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/post")
                .param("pageNumber", "1")
                .param("pageSize", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testCreatePost() throws Exception {
        String caption = "Test Caption";
        MockMultipartFile image = new MockMultipartFile(
                "images", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());

        Post post = new Post();
        post.setId(1L);
        post.setCaption(caption);
        // Initialize other properties of post as needed

        when(postService.createPost(eq(caption), any())).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/post")
                .file(image)
                .param("caption", caption))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caption").value(caption));
    }

    @Test
    public void testAddComment() throws Exception {
        long postId = 1L;
        String commentText = "Test Comment";

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setComments(commentText);
        // Initialize other properties of comment as needed

        when(commentService.addComment(postId, commentText)).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comment/{postId}", postId)
                .param("comment", commentText))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments").value(commentText));
    }

    @Test
    public void testDeleteComment() throws Exception {
        long commentId = 1L;
        String responseMessage = "Comment deleted successfully";

        when(commentService.deleteComment(commentId)).thenReturn(responseMessage);

        mockMvc.perform(MockMvcRequestBuilders.delete("/comment/{commentId}", commentId))
                .andExpect(status().isOk())
                .andExpect(content().string(responseMessage));
    }
}


//package com.wbd.insta.controller;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.http.MediaType;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.multipart.MultipartFile;
//import com.wbd.insta.dto.CustomDTO;
//import com.wbd.insta.model.Comment;
//import com.wbd.insta.model.Post;
//import com.wbd.insta.service.CommentService;
//import com.wbd.insta.service.ImageService;
//import com.wbd.insta.service.PostService;
//@WebMvcTest(PostController.class)
//public class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private PostService postService; // Mocked service
//    @MockBean
//    private ImageService imageService; // Mocked service
//    @MockBean
//    private CommentService commentService; // Mocked service
//    @Test
//    public void testGetAllPosts() throws Exception {
//        // Mock data
//        List<CustomDTO> mockPosts = new ArrayList<>();
//        mockPosts.add(new CustomDTO(1L, "Sample caption1", null, null));
//        // Mock Service Behavior
//        Mockito.when(postService.getAllPosts(Mockito.eq(0), Mockito.eq(3))).thenReturn(mockPosts);
//        // Performing GET request
//        mockMvc.perform(MockMvcRequestBuilders.get("/post")
//                .param("pageNumber", "0") // Ensure parameters match the service mock
//                .param("pageSize", "3")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].caption").value("Sample caption1"));
//    }
//    @Test
//    public void testRetrieveImageForPost() throws Exception {
//        // Mock data
//        List<MultipartFile> mockImages = new ArrayList<>();
//        MultipartFile mockImage = Mockito.mock(MultipartFile.class);
//        mockImages.add(mockImage);
//        // Mock Service Behavior
//        Mockito.when(imageService.retrieveImagesForPost(Mockito.eq(1L))).thenReturn(mockImages);
//        // Performing GET request
//        mockMvc.perform(MockMvcRequestBuilders.get("/images/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
//    }
//    @Test
//    public void testRetrieveImageForPostNotFound() throws Exception {
//        // Mock Service Behavior
//        Mockito.when(imageService.retrieveImagesForPost(Mockito.eq(1L))).thenReturn(new ArrayList<>());
//        // Performing GET request
//        mockMvc.perform(MockMvcRequestBuilders.get("/images/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
//    }
//@Test
//public void testCreatePost() throws Exception {
//    // Mock data
//    String caption = "Sample caption";
//    List<MultipartFile> mockImages = new ArrayList<>();
//    MultipartFile mockImage = Mockito.mock(MultipartFile.class);
//    mockImages.add(mockImage);
//    Post mockPost = new Post();
//    mockPost.setCaption(caption);
//    // Mock Service Behavior
//    Mockito.when(postService.createPost(Mockito.eq(caption), Mockito.anyList())).thenReturn(mockPost);
//    // Performing POST request
//    mockMvc.perform(MockMvcRequestBuilders.post("/post")
//            .param("caption", caption)
//            .contentType(MediaType.MULTIPART_FORM_DATA)
//            .content("[{\"file\": \"mockImage\"}]"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.caption").value(caption));
//}
//@Test
//public void testCreatePostWithEmptyCaption() throws Exception {
//    // Mock data
//    String caption = "";
//    List<MultipartFile> mockImages = new ArrayList<>();
//    MultipartFile mockImage = Mockito.mock(MultipartFile.class);
//    mockImages.add(mockImage);
//    // Mock Service Behavior
//    Mockito.when(postService.createPost(Mockito.eq(caption), Mockito.anyList())).thenThrow(new IllegalArgumentException("Caption cannot be empty"));
//    // Performing POST request
//    mockMvc.perform(MockMvcRequestBuilders.post("/post")
//            .param("caption", caption)
//            .contentType(MediaType.MULTIPART_FORM_DATA)
//            .content("[{\"file\": \"mockImage\"}]"))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest());
//}
//@Test
//public void testCreatePostWithNoImages() throws Exception {
//    // Mock data
//    String caption = "Sample caption";
//    List<MultipartFile> mockImages = new ArrayList<>();
//    Post mockPost = new Post();
//    mockPost.setCaption(caption);
//    // Mock Service Behavior
//    Mockito.when(postService.createPost(Mockito.eq(caption), Mockito.anyList())).thenReturn(mockPost);
//    // Performing POST request
//    mockMvc.perform(MockMvcRequestBuilders.post("/post")
//            .param("caption", caption)
//            .contentType(MediaType.MULTIPART_FORM_DATA)
//            .content("[]"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.caption").value(caption));
//}
//@Test
//public void testAddComment() throws Exception {
//    // Mock data
//    long postId = 1L;
//    String commentText = "Sample comment";
//    Comment mockComment = new Comment();
//    mockComment.setId(1L);
//    mockComment.setComments(commentText);
//    // Mock Service Behavior
//    Mockito.when(commentService.addComment(Mockito.eq(postId), Mockito.eq(commentText))).thenReturn(mockComment);
//    // Performing POST request
//    mockMvc.perform(MockMvcRequestBuilders.post("/comment/" + postId)
//            .param("comment", commentText)
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(commentText));
//}
//@Test
//public void testAddCommentWithEmptyText() throws Exception {
//    // Mock data
//    long postId = 1L;
//    String commentText = "";
//    // Mock Service Behavior
//    Mockito.when(commentService.addComment(Mockito.eq(postId), Mockito.eq(commentText))).thenThrow(new IllegalArgumentException("Comment cannot be empty"));
//    // Performing POST request
//    mockMvc.perform(MockMvcRequestBuilders.post("/comment/" + postId)
//            .param("comment", commentText)
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest());
//}
//@Test
//public void testDeleteComment() throws Exception {
//    // Mock data
//    long commentId = 1L;
//    // Mock Service Behavior
//    Mockito.when(commentService.deleteComment(Mockito.eq(commentId))).thenReturn("Comment deleted successfully");
//    // Performing DELETE request
//    mockMvc.perform(MockMvcRequestBuilders.delete("/comment/" + commentId)
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.content().string("Comment deleted successfully"));
//}
//@Test
//public void testDeleteCommentNotFound() throws Exception {
//    // Mock data
//    long commentId = 1L;
//    // Mock Service Behavior
//    Mockito.when(commentService.deleteComment(Mockito.eq(commentId))).thenThrow(new IllegalArgumentException("Comment not found"));
//    // Performing DELETE request
//    mockMvc.perform(MockMvcRequestBuilders.delete("/comment/" + commentId)
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest());
//}
//}