package com.example.demo.dto.response;

import com.example.demo.dto.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteResponse implements IDto {
    private String message;
}
