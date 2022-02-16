package com.te.csvfile.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvFile {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private Date openedDate;
	

}
