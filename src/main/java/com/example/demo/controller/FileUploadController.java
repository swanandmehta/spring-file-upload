package com.example.demo.controller;

import com.example.demo.dto.response.DeleteResponse;
import com.example.demo.dto.response.StoreResponse;
import com.example.demo.entity.Store;
import com.example.demo.service.IStoreService;
import com.example.demo.service.impl.CSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/storage")
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private final IStoreService storeService;

    public FileUploadController(@Qualifier("StoreService") IStoreService storeService) {
        logger.trace("Started FileUploadController");
        this.storeService = storeService;
    }

    @PostMapping("/upload")
    public StoreResponse uploadFile(@RequestParam("file") MultipartFile uploadedFile) {
        logger.trace("Request received for uploading the file");
        List<Store> storeList = storeService.save(uploadedFile);
        logger.trace("File process completed");
        return new StoreResponse(storeList.size(), storeList);
    }

    @GetMapping("")
    public List<Store> getStore(@RequestParam(name = "code", defaultValue = "") String code) {
        logger.trace("Request received for loading store code : "+code);
        List<Store> storeList;

        if(code.isBlank()) {
            storeList = storeService.getAll();
        } else {
            storeList = storeService.getByCode(code);
        }

        logger.trace("Request completed for loading store");
        return storeList;
    }

    @DeleteMapping("")
    public DeleteResponse deleteStore() {
        logger.trace("Request received for deleting the store");
        storeService.deleteAll();
        logger.trace("Request completed for deleting store");
        return new DeleteResponse("All records removed.");
    }

}
