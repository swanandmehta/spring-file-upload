package com.example.demo.service;

import com.example.demo.dto.internal.StoreDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IFileService {
    File getFile(MultipartFile uploadedFile);

    List<StoreDto> readFile(File file);
}
