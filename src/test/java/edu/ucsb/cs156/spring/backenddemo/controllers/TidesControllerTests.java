package edu.ucsb.cs156.spring.backenddemo.controllers;
import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;

//edited this ^ from edu.... to test.java.edu.....
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = TidesController.class)

public class TidesControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  TidesQueryService mockTidesQueryService;

  @Test
  public void test_getTides() throws Exception {
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    String beginDate = "20201019";
    String endDate = "20201021";
    String station = "9414290";

  
    when(mockTidesQueryService.getJSON(eq(beginDate), eq(endDate), eq(station))).thenReturn(fakeJsonResult);

    String url = String.format("/api/tides/get?beginDate=%s&endDate=%s&station=%s", beginDate, endDate, station);

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}