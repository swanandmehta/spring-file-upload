package com.example.demo.dto.internal;

import com.example.demo.dto.IDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StoreDto implements IDto {

    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;

}
