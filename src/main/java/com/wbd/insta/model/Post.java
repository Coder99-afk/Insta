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
private String caption;
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Image> image;
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Comment> comment;
}
