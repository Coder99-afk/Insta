package com.wbd.insta.service;

import lombok.Getter;

@Getter
//To check the uploaded image size does not exceed above 100kB
public enum FileSize {
    FILE_SIZE(100*1024*1024);
    private final int sizeInBytes;

    FileSize(int sizeInBytes) {
        this.sizeInBytes= sizeInBytes;
    }

}
