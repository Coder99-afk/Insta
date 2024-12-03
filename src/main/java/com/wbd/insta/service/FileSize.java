package com.wbd.insta.service;

import lombok.Getter;

@Getter
public enum FileSize {
    FILE_SIZE(100*1024*1024);
    private final int sizeInBytes;

    FileSize(int sizeInBytes) {
        this.sizeInBytes= sizeInBytes;
    }

}
