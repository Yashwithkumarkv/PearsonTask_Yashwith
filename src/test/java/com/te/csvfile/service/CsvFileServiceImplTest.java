package com.te.csvfile.service;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.te.csvfile.pojo.CsvFile;

@ExtendWith(MockitoExtension.class)
class CsvFileServiceImplTest {

	@InjectMocks
	private CsvFileServiceImpl service;

	@Test
	void testGetCsvFileById() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		CsvFile file = new CsvFile("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		CsvFile csvFileById = service.getCsvFileById(file.getStoreId());
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", csvFileById.getStoreId());
	}

	@Test
	void testGetCsvFileByCityOrDate() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1998");
		CsvFile file = new CsvFile("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		List<CsvFile> list = new ArrayList<CsvFile>();
		list.add(file);
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f",
				service.getCsvFileByCityOrDate("city").get(0).getStoreId());

	}

}
