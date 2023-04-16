package com.example.demo.dto.response;

import com.example.demo.dto.IDto;
import com.example.demo.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreResponse implements IDto {
    private Integer count;
    private List<Store> store;
}
