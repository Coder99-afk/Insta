package com.wbd.insta.dto;

import com.wbd.insta.model.Image;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomDTO {
    private Long postId;
    private String caption;
    private Long commentId;
    private String comments;
//    private Long imageId;
//    private String imageName;
//    private String imageType;
//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    @Column(name = "image_data")
//    private byte[] imageData;
//  private List<Image> imageList;

}
