package com.finalProject.FinalProject.dto;


import lombok.Data;


@Data
public class FileInfoDTO {
    private Long id;
    private String name;
    private String filePath;
    private String type;






    private String url;
    private  byte[] image;

    public FileInfoDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public FileInfoDTO(String name, String url, byte[] image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }
}
