package com.example.demo.utils;

import com.example.demo.dto.internal.StoreDto;
import com.example.demo.entity.Store;

import java.sql.Timestamp;

public class StoreTransformer {
    public static Store getStore(StoreDto dto) {
        Store store = new Store();

        store.setSource(dto.getSource());
        store.setCodeListCode(dto.getCodeListCode());
        store.setCode(dto.getCode());
        store.setDisplayValue(dto.getDisplayValue());
        store.setDescription(dto.getDescription());

        if(dto.getFromDate() != null) {
            store.setFromDate(Timestamp.valueOf(dto.getFromDate().atStartOfDay()));
        }

        if(dto.getToDate() != null) {
            store.setToDate(Timestamp.valueOf(dto.getFromDate().atStartOfDay()));
        }

        if(dto.getSortingPriority() != null) {
            store.setSortingPriority(dto.getSortingPriority());
        }

        return store;
    }
}
