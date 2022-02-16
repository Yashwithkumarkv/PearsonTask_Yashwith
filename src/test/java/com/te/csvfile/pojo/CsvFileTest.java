package com.te.csvfile.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CsvFileTest {
	String json = "{\"storeId\":\"as\",\"postCode\":\"asdf\",\"city\":\"we\",\"address\":\"jhg\",\"openedDate\":908130600000}";
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void serializeTest() throws JsonProcessingException, ParseException {
		CsvFile file = mapper.readValue(json, CsvFile.class);
		String writeValueAsString = mapper.writeValueAsString(file);
		assertEquals(json, writeValueAsString);
	}

	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		CsvFile file = mapper.readValue(json, CsvFile.class);
		assertEquals("asdf", file.getPostCode());
	}

}
