package com.wbd.insta.service;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageServiceTest {
    public static final String IMAGE_DIRECTORY="/Users/aswain/Documents/InstaImages";
    @InjectMocks
    private ImageService imageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveImageSuccess() throws IOException {
        // Arrange
        Long postId = 1L;
        String originalFileName = "test.png";
        byte[] content = "test content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("file", originalFileName, "image/png", content);

        // Action
        String savedFileName = imageService.saveImage(imageFile, postId);

        // Assert
        assertNotNull(savedFileName);
        assertTrue(savedFileName.startsWith("post_" + postId + "_"));
        assertTrue(savedFileName.endsWith(".jpg"));

        // Clean up
        Files.deleteIfExists(Paths.get(ImageService.IMAGE_DIRECTORY, savedFileName));
    }

    @Test
    public void testSaveImageFileTooLarge() {
        // Arrange
        Long postId = 1L;
        byte[] content = new byte[(int) (FileSize.FILE_SIZE.getSizeInBytes() + 1)];
        MultipartFile imageFile = new MockMultipartFile("file", "test.png", "image/png", content);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> imageService.saveImage(imageFile, postId));
    }

    @Test
    public void testSaveImageInvalidFormat() {
        // Arrange
        Long postId = 1L;
        byte[] content = "test content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("file", "test.txt", "text/plain", content);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> imageService.saveImage(imageFile, postId));
    }

    @Test
    public void testSaveImageCreatesDirectoryIfNotExists() throws IOException {
        // Arrange
        Long postId = 1L;
        String originalFileName = "test.png";
        byte[] content = "test content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("file", originalFileName, "image/png", content);
        File directory = new File(ImageService.IMAGE_DIRECTORY);

        // Ensure directory does not exist before test
        if (directory.exists()) {
            directory.delete();
        }

        // Act
        String savedFileName = imageService.saveImage(imageFile, postId);

        // Assert
        assertTrue(directory.exists());
        assertTrue(directory.isDirectory());

        // Clean up
        Files.deleteIfExists(Paths.get(ImageService.IMAGE_DIRECTORY, savedFileName));
        directory.delete();
    }
}