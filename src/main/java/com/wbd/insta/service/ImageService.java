package com.wbd.insta.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    //Indicates the location of the local folder where the uploaded images will be saved.
    static final String IMAGE_DIRECTORY="/Users/aswain/Documents/InstaImages";
    //Saves the image(s) for multiple posts based on their post id, after checking their file format and file size such that
    //it follows the desired norm.
    public String saveImage(MultipartFile imageFile, Long postId) throws IOException {
        if(imageFile.getSize()>FileSize.FILE_SIZE.getSizeInBytes()){
            throw new RuntimeException("File size is too large");
        }
        String imageFormat= FilenameUtils.getExtension(imageFile.getOriginalFilename());
        boolean flag= false;
        for(ImageFormat format: ImageFormat.values()){
            if(format.name().equalsIgnoreCase(imageFormat)){
                flag= true;
                break;
            }
        }
        if(!flag){
            throw new RuntimeException("Invalid image format/type");
        }
        MultipartFile newImgFile= changeContentTypeToJpg(imageFile);
        File directory= new File(IMAGE_DIRECTORY);
        if(!directory.exists()){
            directory.mkdirs();
        }
        String fileName= "post_"+postId+"_"+ UUID.randomUUID()+"_"+newImgFile.getOriginalFilename();
        Path filePath= Paths.get(IMAGE_DIRECTORY, fileName);
        //Save image to local file
        Files.write(filePath, newImgFile.getBytes());
        return fileName;
    }
//Converts all image formats(jpg,png,bmp) to jpeg type.
    private MultipartFile changeContentTypeToJpg(MultipartFile imageFile) throws IOException {
        return new MockMultipartFile(
                imageFile.getName(),
                imageFile.getOriginalFilename(),
                "image/jpeg", // New content type
                imageFile.getBytes()
        );
    }
//Retrieves all image files that belongs to a particular post based on post id.
    public List<MultipartFile> retrieveImagesForPost(Long postId) throws IOException {
        List<MultipartFile> multipartFiles= new ArrayList<>();
        File directory= new File(IMAGE_DIRECTORY);
        File[] files= directory.listFiles(((dir, name) -> name.startsWith("post_"+postId+"_")));
        if(files!= null){
            for(File file: files){
                Path filePath= file.toPath();
                byte[] imageData= Files.readAllBytes(filePath);
                MultipartFile multipartFile = new MockMultipartFile(
                        file.getName(),
                        file.getName(),
                        Files.probeContentType(filePath),
                        imageData
                );
                multipartFiles.add(multipartFile);
            }
        }
        return multipartFiles;
    }


}
