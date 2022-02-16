package com.te.csvfile.service;

import java.util.List;

import com.te.csvfile.pojo.CsvFile;


public interface CsvFileService {
	public CsvFile getCsvFileById(String id);

	public List<CsvFile> getCsvFileByCityOrDate(String field);
}
