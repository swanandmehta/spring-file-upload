package com.example.demo.utils;

import com.example.demo.dto.internal.StoreDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVTransformer {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static StoreDto getStoreDto(String[] row) {
        StoreDto dto = new StoreDto();

        dto.setSource(row[0]);
        dto.setCodeListCode(row[1]);
        dto.setCode(row[2]);
        dto.setDisplayValue(row[3]);
        dto.setDescription(row[4]);

        if (row[5] != null && !row[5].isBlank()) {
            LocalDate localDate = LocalDate.parse(row[5], DATE_TIME_FORMATTER);
            dto.setFromDate(localDate);
        }

        if (row[6] != null && !row[6].isBlank()) {
            LocalDate localDate = LocalDate.parse(row[5], DATE_TIME_FORMATTER);
            dto.setFromDate(localDate);
        }

        if (row[7] != null && !row[7].isBlank()) {
            try {
                dto.setSortingPriority(Integer.parseInt(row[7]));
            } catch (NumberFormatException e) {
                dto.setSortingPriority(null);
            }

        }

        return dto;

    }
}
