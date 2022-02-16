package com.te.csvfile.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.te.csvfile.exception.ExceptionClass;
import com.te.csvfile.pojo.CsvFile;

@Service
public class CsvFileServiceImpl implements CsvFileService {

	@Override
	public CsvFile getCsvFileById(String id) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stores.csv"));
				@SuppressWarnings("resource")
				CSVParser parser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			Iterable<CSVRecord> csvRecord = parser.getRecords();
			if (csvRecord != null) {
				for (CSVRecord csvRecord2 : csvRecord) {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord2.get("opened Date"));
					CsvFile csv = new CsvFile(csvRecord2.get("Store Id"), csvRecord2.get("Post Code"),
							csvRecord2.get("City"), csvRecord2.get("Address"), date);
					if (csv.getStoreId().equals(id)) {
						return csv;
					} else {
						throw new ExceptionClass("please enter valid id");
					}
				}
			}
			throw new ExceptionClass("data missing in csv file");
		} catch (Exception e) {
			throw new ExceptionClass("record not found");
		}
	}

	@Override
	public List<CsvFile> getCsvFileByCityOrDate(String field) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stores.csv"));
				@SuppressWarnings("resource")
				CSVParser parser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			Iterable<CSVRecord> csvRecord = parser.getRecords();
			List<CsvFile> listOfData = new ArrayList<CsvFile>();
			for (CSVRecord csvRecord2 : csvRecord) {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord2.get("opened Date"));
				CsvFile csv = new CsvFile(csvRecord2.get("Store Id"), csvRecord2.get("Post Code"),
						csvRecord2.get("City"), csvRecord2.get("Address"), date);
				if (field.equalsIgnoreCase("city")) {
					listOfData.add(csv);
				} else if (field.equalsIgnoreCase("openeddate")) {
					listOfData.add(csv);
				}
			}
			for (int i = 0; i < listOfData.size(); i++) {
				if (field.equalsIgnoreCase("city")) {
					List<CsvFile> list = listOfData.stream().sorted((o1, o2) -> o1.getCity().compareTo(o2.getCity()))
							.collect(Collectors.toList());
					return list;
				} else if (field.equalsIgnoreCase("openeddate")) {
					List<CsvFile> list = listOfData.stream()
							.sorted((o1, o2) -> o1.getOpenedDate().compareTo(o2.getOpenedDate()))
							.collect(Collectors.toList());
					return list;
				}
			}
			throw new ExceptionClass("something went wrong");

		} catch (Exception e) {
			throw new ExceptionClass("something went wrong");
		}

	}

}
