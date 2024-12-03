package com.wbd.insta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity{
    @ManyToOne
    private Post post;
    private String imageName;
    private String imageType;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "image_data")
    private byte[] imageData;
}
