package com.example.demo.service;

import com.example.demo.dto.internal.StoreDto;
import com.example.demo.entity.Store;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStoreService {
    List<Store> save(MultipartFile uploadedFile);

    Store save(StoreDto dto);

    List<Store> getAll();

    List<Store> getByCode(String code);

    void deleteAll();
}
