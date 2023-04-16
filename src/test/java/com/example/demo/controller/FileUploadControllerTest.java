package com.example.demo.controller;

import com.example.demo.dto.response.StoreResponse;
import com.example.demo.entity.Store;
import com.example.demo.service.IStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    @Qualifier("StoreService")
    private IStoreService storeService;

    @Test
    public void shouldBeAbleToAcceptMultipartFile() throws Exception {
        when(storeService.save(any(MultipartFile.class))).thenReturn(new ArrayList<>(0));
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test".getBytes()
        );
        mockMvc.perform(multipart("/storage/upload").file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldNotBeAbleToProcessInCaseOfInvalidPayload() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "data",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test".getBytes()
        );
        when(storeService.save(any(MultipartFile.class))).thenReturn(new ArrayList<>(0));
        mockMvc.perform(multipart("/storage/upload").file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldAbleToReturnRecordsAndCount() throws Exception {
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Store());
        storeList.add(new Store());

        String expectedResult = mapper.writeValueAsString(new StoreResponse(storeList.size(), storeList));

        when(storeService.save(any(MultipartFile.class))).thenReturn(storeList);

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test".getBytes()
        );

        mockMvc.perform(multipart("/storage/upload").file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectedResult));

    }

}
