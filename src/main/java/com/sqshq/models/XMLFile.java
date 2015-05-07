package com.sqshq.models;

import org.springframework.web.multipart.MultipartFile;

public class XMLFile{

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}