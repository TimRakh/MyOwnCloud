package com.timurka.MyOwnCloud.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FileData {
    @JsonProperty("filename")
    String fileName;
    @JsonProperty("size")
    long size;

    public FileData(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }
}