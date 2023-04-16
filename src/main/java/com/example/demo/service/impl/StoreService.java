package com.example.demo.service.impl;

import com.example.demo.dto.internal.StoreDto;
import com.example.demo.entity.Store;
import com.example.demo.repostitory.StoreRepository;
import com.example.demo.service.IFileService;
import com.example.demo.service.IStoreService;
import com.example.demo.utils.StoreTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("StoreService")
public class StoreService implements IStoreService {

    private final IFileService fileService;
    private final StoreRepository repository;
    private final Logger logger = LoggerFactory.getLogger(StoreService.class);

    public StoreService(@Qualifier("CSVService") IFileService csvService, StoreRepository repository) {
        logger.trace("Init StoreService");
        this.fileService = csvService;
        this.repository = repository;
    }

    @Override
    public List<Store> save(MultipartFile uploadedFile) {
        logger.trace("Processing Service request for input");
        File file = fileService.getFile(uploadedFile);
        logger.trace("Input file successfully converted");
        List<StoreDto> storeDtoList = fileService.readFile(file);
        logger.trace("Input file was converted into dto");
        List<Store> storeList = storeDtoList.stream().map(this::save).toList();
        logger.trace("StoreDtos were saved in db");
        return  storeList;
    }

    @Override
    public Store save(StoreDto dto) {
        logger.trace("Trying to convert store dto into entity");
        Store store = StoreTransformer.getStore(dto);
        logger.trace("Store dto converted into Store");
        store = repository.save(store);
        logger.trace("Store was created with id "+store.getId());
        return store;
    }

    @Override
    public List<Store> getAll() {
        logger.trace("Trying to load all data from db");
        List<Store> storeList = repository.findAll();
        logger.trace("Completed loading all store form db");
        return storeList;
    }

    @Override
    public List<Store> getByCode(String code) {
        List<Store> storeList = new ArrayList<>(1);
        logger.trace("Trying to load all store from db with code "+code);
        Store store = repository.findByCode(code);
        logger.trace("Loaded Store from DB ");
        storeList.add(store);
        return storeList;
    }

    @Override
    public void deleteAll() {
        logger.trace("Started removing all records from DB ");
        repository.deleteAll();
        logger.trace("Removed all records from DB ");
    }
}
