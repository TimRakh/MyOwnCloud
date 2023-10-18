package com.timurka.MyOwnCloud.mapper;

import com.timurka.MyOwnCloud.entity.File;
import com.timurka.MyOwnCloud.data.FileData;
import org.springframework.stereotype.Component;

@Component
public class MapFile {
    public FileData mapFileToFileDto(File file) {
        return new FileData(file.getFileName(), file.getSize());
    }

}
