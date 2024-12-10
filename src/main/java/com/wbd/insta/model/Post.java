package com.wbd.insta.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    //Post caption data
private String caption;
//One post can have multiple images
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Image> image;
//One post can have multiple comments
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Comment> comment;
}
