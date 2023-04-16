package com.example.demo.service.impl;

import com.example.demo.dto.internal.StoreDto;
import com.example.demo.exception.FileProcessingException;
import com.example.demo.service.IFileService;
import com.example.demo.utils.CSVTransformer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("CSVService")
public class CSVService implements IFileService {

    private final Logger logger = LoggerFactory.getLogger(CSVService.class);

    @Override
    public File getFile(MultipartFile uploadedFile) {
        logger.trace("Processing Input file");
        String fileName = UUID.randomUUID().toString();
        try {
            File file = File.createTempFile(fileName, "tmp");
            uploadedFile.transferTo(file);
            return file;
        } catch (IOException e) {
            logger.error("Could not process the input file", e);
            throw new FileProcessingException("Could not process the input file", e);
        } finally {
            logger.trace("Processing of Input file completed.");
        }
    }

    @Override
    public List<StoreDto> readFile(File file) {
        try (Reader reader = new FileReader(file)) {
            try(CSVReader csvReader = new CSVReader(reader)) {
                //Skipping the header
                csvReader.skip(1);

                List<StoreDto> list = new ArrayList<>();
                String[] row;
                while((row = csvReader.readNext()) != null) {
                    list.add(CSVTransformer.getStoreDto(row));
                }

                return list;

            } catch (CsvValidationException e) {
                logger.error("Could not extract information from input file.", e);
                throw new FileProcessingException("Could not extract information from input file.", e);
            }
        } catch (IOException e) {
            logger.error("Could not open file to process.", e);
            throw new FileProcessingException("Could not open file to process. ", e);
        }

    }
}
