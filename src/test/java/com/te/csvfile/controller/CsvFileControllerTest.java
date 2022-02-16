package com.te.csvfile.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.csvfile.pojo.CsvFile;
import com.te.csvfile.service.CsvFileService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CsvFileControllerTest {

	@MockBean
	private CsvFileService service;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testGetCsvById() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		CsvFile file = new CsvFile("hi", "ss", "pp", "mm", date);
		when(service.getCsvFileById(Mockito.anyString())).thenReturn(file);

		String contentAsString = mockMvc
				.perform(get("/api/v1/store/hi").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(file)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		CsvFile readValue = mapper.readValue(contentAsString, CsvFile.class);
		assertEquals("hi", readValue.getStoreId());
	}

	@Test
	void testGetCsvByCityOrDate() throws UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		List<CsvFile> list = new ArrayList<CsvFile>();
		CsvFile file = new CsvFile("hi", "2e", "me", "ss", date);
		list.add(file);
		when(service.getCsvFileByCityOrDate(Mockito.anyString())).thenReturn(list);
		String contentAsString = mockMvc.perform(get("/api/v1/store/fetch/str")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		List<CsvFile> readValue = mapper.readValue(contentAsString, new TypeReference<List<CsvFile>>() {
		});
		assertEquals("hi", readValue.get(0).getStoreId());
	}

}
