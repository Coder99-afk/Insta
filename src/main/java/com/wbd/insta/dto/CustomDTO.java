// package com.wbd.insta.dto;

// import com.wbd.insta.model.Image;
// import jakarta.persistence.Basic;
// import jakarta.persistence.Column;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.Lob;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.util.List;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class CustomDTO {
//     private Long postId;
//     private String caption;
//     private Long commentId;
//     private String comments;

//     @Override
//     public String toString() {
//         return "CustomDTO{" +
//                 "postId=" + postId +
//                 ", caption='" + caption + '\'' +
//                 ", commentId=" + commentId +
//                 ", comments='" + comments + '\'' +
//                 '}';
//     }

//     @Override
//     public String toString() {
//         return "CustomDTO{" +
//                 "postId=" + postId +
//                 ", caption='" + caption + '\'' +
//                 ", commentId=" + commentId +
//                 ", comments='" + comments + '\'' +
//                 '}';
//     }
//    private Long imageId;
//    private String imageName;
//    private String imageType;
//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    @Column(name = "image_data")
//    private byte[] imageData;
//  private List<Image> imageList;

// }
package com.wbd.insta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomDTO {
    private Long postId;
    private String caption;
    private Long commentId;
    private String comments;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "postId=" + postId +
                ", caption='" + caption + '\'' +
                ", commentId=" + commentId +
                ", comments='" + comments + '\'' +
                '}';
    }
}
