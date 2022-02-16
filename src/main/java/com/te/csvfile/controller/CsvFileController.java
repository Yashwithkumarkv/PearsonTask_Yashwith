package com.te.csvfile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.te.csvfile.pojo.CsvFile;
import com.te.csvfile.pojo.StoreResponse;
import com.te.csvfile.service.CsvFileService;

@RestController
@RequestMapping("/api/v1/store")
public class CsvFileController {
	@Autowired
	private CsvFileService service;

	@GetMapping("/fetchbyid/{id}")
	public ResponseEntity<StoreResponse> getCsvById(@PathVariable("id") String id) {
		try {
			CsvFile fileList = service.getCsvFileById(id);
			return new ResponseEntity<StoreResponse>(new StoreResponse(false, "data fetched successfully", fileList), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new StoreResponse(true, "Something went wrong please enter valid id", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/fetch/{field}")
	public ResponseEntity<?> getCsvByCityOrDate(@PathVariable String field) {
		try {
			List<CsvFile> fileList2 = service.getCsvFileByCityOrDate(field);
			return new ResponseEntity<StoreResponse>(new StoreResponse(false, "data fetched are:", fileList2), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<StoreResponse>(new StoreResponse(true, "Something went wrong please enter valid field", null), HttpStatus.NOT_FOUND);
		}
	}
}
