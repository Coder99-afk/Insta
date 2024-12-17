// package com.wbd.insta.service;
// import com.wbd.insta.dto.CustomDTO;
// import com.wbd.insta.model.Image;
// import com.wbd.insta.model.Post;
// import com.wbd.insta.repository.ImageRepo;
// import com.wbd.insta.repository.PostRepo;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class PostServiceTest {

//     @Mock
//     private PostRepo postRepo;

//     @Mock
//     private ImageRepo imageRepo;

//     @Mock
//     private ImageService imageService;
    
//     @InjectMocks
//     private PostService postService;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }


//     @Test
//     public void testGetAllPosts() throws IOException {
//         // Arrange
//         int pageNumber = 0;
//         int pageSize = 3;
//         Pageable pageable = PageRequest.of(pageNumber, pageSize);
//         List<CustomDTO> mockPosts = new ArrayList<>();
//         mockPosts.add(new CustomDTO(1L, "Sample caption1", null, null));
//         mockPosts.add(new CustomDTO(2L, "Sample caption2", null, null));
//         mockPosts.add(new CustomDTO(3L, "Sample caption3", null, null));

//         when(postRepo.findAllPosts(pageable)).thenReturn(mockPosts);

//         // Act
//         List<CustomDTO> result = postService.getAllPosts(pageNumber, pageSize);

//         // Assert
//         assertEquals(3, result.size());
//         assertEquals("Sample caption1", result.get(0).getCaption());
//         assertEquals("Sample caption2", result.get(1).getCaption());
//         assertEquals("Sample caption3", result.get(2).getCaption());
//         verify(postRepo, times(1)).findAllPosts(pageable);
//     }

//     @Test
//     public void testGetAllPostsEmpty() throws IOException {
//         // Arrange
//         int pageNumber = 0;
//         int pageSize = 3;
//         Pageable pageable = PageRequest.of(pageNumber, pageSize);
//         List<CustomDTO> mockPosts = new ArrayList<>();

//         when(postRepo.findAllPosts(pageable)).thenReturn(mockPosts);

//         // Act
//         List<CustomDTO> result = postService.getAllPosts(pageNumber, pageSize);

//         // Assert
//         assertEquals(0, result.size());
//         verify(postRepo, times(1)).findAllPosts(pageable);
//     }
//     @Test
//     public void testCreatePost() throws IOException {
//         // Arrange
//         String caption = "Test caption";
//         MultipartFile mockFile1 = mock(MultipartFile.class);
//         MultipartFile mockFile2 = mock(MultipartFile.class);
//         List<MultipartFile> images = List.of(mockFile1, mockFile2);

//         Post post = new Post();
//         post.setCaption(caption);

//         Post savedPost = new Post();
//         savedPost.setId(1L);
//         savedPost.setCaption(caption);

//         Image image1 = new Image();
//         image1.setImageName("image1.jpg");
//         image1.setImageType("image/jpeg");
//         image1.setImageData(new byte[]{1, 2, 3});
//         image1.setPost(savedPost);

//         Image image2 = new Image();
//         image2.setImageName("image2.jpg");
//         image2.setImageType("image/jpeg");
//         image2.setImageData(new byte[]{4, 5, 6});
//         image2.setPost(savedPost);

//         when(postRepo.save(any(Post.class))).thenReturn(savedPost);
//         when(imageService.saveImage(any(MultipartFile.class), anyLong())).thenReturn("image.jpg");
//         when(mockFile1.getOriginalFilename()).thenReturn("image1.jpg");
//         when(mockFile1.getContentType()).thenReturn("image/jpeg");
//         when(mockFile1.getBytes()).thenReturn(new byte[]{1, 2, 3});
//         when(mockFile2.getOriginalFilename()).thenReturn("image2.jpg");
//         when(mockFile2.getContentType()).thenReturn("image/jpeg");
//         when(mockFile2.getBytes()).thenReturn(new byte[]{4, 5, 6});

//         // Act
//         Post result = postService.createPost(caption, images);

//         // Assert
//         Assertions.assertNotNull(result);
//         assertEquals(caption, result.getCaption());
//         assertEquals(2, result.getImage().size());
//         verify(postRepo, times(2)).save(any(Post.class));
//         verify(imageRepo, times(2)).save(any(Image.class));
//         verify(imageService, times(2)).saveImage(any(MultipartFile.class), anyLong());
//     }

//     @Test
//     public void testCreatePostWithNoImages() throws IOException {
//         // Arrange
//         String caption = "Test caption";
//         List<MultipartFile> images = new ArrayList<>();

//         Post post = new Post();
//         post.setCaption(caption);

//         Post savedPost = new Post();
//         savedPost.setId(1L);
//         savedPost.setCaption(caption);

//         when(postRepo.save(any(Post.class))).thenReturn(savedPost);

//         // Act
//         Post result = postService.createPost(caption, images);

//         // Assert
//         Assertions.assertNotNull(result);
//         assertEquals(caption, result.getCaption());
//         assertEquals(0, result.getImage().size());
//         verify(postRepo, times(2)).save(any(Post.class));
//         verify(imageRepo, times(0)).save(any(Image.class));
//         verify(imageService, times(0)).saveImage(any(MultipartFile.class), anyLong());
//     }
// }
package com.wbd.insta.service;
import com.wbd.insta.dto.CustomDTO;
import com.wbd.insta.model.Image;
import com.wbd.insta.model.Post;
import com.wbd.insta.repository.ImageRepo;
import com.wbd.insta.repository.PostRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {
    @Mock
    private PostRepo postRepo;
    @InjectMocks
    private PostService postService;
    @MockBean
    ImageService imageService;
    @Autowired
    ImageRepo imageRepo;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    // Duplicate method removed

@Test
public void testGetAllPosts() throws IOException {
    // Arrange
    int pageNumber = 0;
    int pageSize = 3;
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    List<CustomDTO> mockPosts = new ArrayList<>();
    mockPosts.add(new CustomDTO(1L, "Sample caption1", null, null));
    mockPosts.add(new CustomDTO(2L, "Sample caption2", null, null));
    mockPosts.add(new CustomDTO(3L, "Sample caption3", null, null));
    when(postRepo.findAllPosts(pageable)).thenReturn(mockPosts);
    // Act
    List<CustomDTO> result = postService.getAllPosts(pageNumber, pageSize);
    // Assert
    assertEquals(3, result.size());
    assertEquals("Sample caption1", result.get(0).getCaption());
    assertEquals("Sample caption2", result.get(1).getCaption());
    assertEquals("Sample caption3", result.get(2).getCaption());
    verify(postRepo, times(1)).findAllPosts(pageable);
}
@Test
public void testGetAllPostsEmpty() throws IOException {
    // Arrange
    int pageNumber = 0;
    int pageSize = 3;
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    List<CustomDTO> mockPosts = new ArrayList<>();
    when(postRepo.findAllPosts(pageable)).thenReturn(mockPosts);
    // Act
    List<CustomDTO> result = postService.getAllPosts(pageNumber, pageSize);
    // Assert
    assertEquals(0, result.size());
    verify(postRepo, times(1)).findAllPosts(pageable);
}
@Test
public void testCreatePost() throws IOException {
    // Arrange
    String caption = "Test caption";
    MultipartFile mockFile1 = mock(MultipartFile.class);
    MultipartFile mockFile2 = mock(MultipartFile.class);
    List<MultipartFile> images = List.of(mockFile1, mockFile2);
    Post post = new Post();
    post.setCaption(caption);
    Post savedPost = new Post();
    savedPost.setId(1L);
    savedPost.setCaption(caption);
    Image image1 = new Image();
    image1.setImageName("image1.jpg");
    image1.setImageType("image/jpeg");
    image1.setImageData(new byte[]{1, 2, 3});
    image1.setPost(savedPost);
    Image image2 = new Image();
    image2.setImageName("image2.jpg");
    image2.setImageType("image/jpeg");
    image2.setImageData(new byte[]{4, 5, 6});
    image2.setPost(savedPost);
    when(postRepo.save(any(Post.class))).thenReturn(savedPost);
    when(imageService.saveImage(any(MultipartFile.class), anyLong())).thenReturn("image.jpg");
    when(mockFile1.getOriginalFilename()).thenReturn("image1.jpg");
    when(mockFile1.getContentType()).thenReturn("image/jpeg");
    when(mockFile1.getBytes()).thenReturn(new byte[]{1, 2, 3});
    when(mockFile2.getOriginalFilename()).thenReturn("image2.jpg");
    when(mockFile2.getContentType()).thenReturn("image/jpeg");
    when(mockFile2.getBytes()).thenReturn(new byte[]{4, 5, 6});
    // Act
    Post result = postService.createPost(caption, images);
    // Assert
    Assertions.assertNotNull(result);
    assertEquals(caption, result.getCaption());
    assertEquals(2, result.getImage().size());
    verify(postRepo, times(2)).save(any(Post.class));
    verify(imageRepo, times(2)).save(any(Image.class));
    verify(imageService, times(2)).saveImage(any(MultipartFile.class), anyLong());
}
@Test
public void testCreatePostWithNoImages() throws IOException {
    // Arrange
    String caption = "Test caption";
    List<MultipartFile> images = new ArrayList<>();
    Post post = new Post();
    post.setCaption(caption);
    Post savedPost = new Post();
    savedPost.setId(1L);
    savedPost.setCaption(caption);
    when(postRepo.save(any(Post.class))).thenReturn(savedPost);
    // Act
    Post result = postService.createPost(caption, images);
    // Assert
    Assertions.assertNotNull(result);
    assertEquals(caption, result.getCaption());
    assertEquals(0, result.getImage().size());
    verify(postRepo, times(2)).save(any(Post.class));
    verify(imageRepo, times(0)).save(any(Image.class));
    verify(imageService, times(0)).saveImage(any(MultipartFile.class), anyLong());
}
}