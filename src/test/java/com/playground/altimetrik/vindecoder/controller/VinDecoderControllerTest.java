package com.playground.altimetrik.vindecoder.controller;

import com.playground.altimetrik.vindecoder.service.MakesService;
import com.playground.altimetrik.vindecoder.service.ModelService;
import com.playground.altimetrik.vindecoder.service.VehicleDecodingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = VinDecoderController.class)
class VinDecoderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MakesService makesService;

    @MockBean
    private ModelService modelService;

    @MockBean
    private VehicleDecodingService vehicleDecodingService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processVin() {


    }
}