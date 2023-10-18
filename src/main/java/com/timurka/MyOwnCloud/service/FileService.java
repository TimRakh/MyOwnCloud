package com.timurka.MyOwnCloud.service;


import com.timurka.MyOwnCloud.data.FileData;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileService {

    HttpStatus uploadFile(String filename,
                          MultipartFile file) throws IOException;

    HttpStatus editFileByName(String currentFileName, String newFileName);

    byte [] getFileByName(String fileName);

    HttpStatus deleteFile(String fileName);

    List<FileData>showSavedFile(int limit);
}
