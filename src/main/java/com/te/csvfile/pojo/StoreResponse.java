package com.te.csvfile.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreResponse {
	@JsonProperty
	private boolean error;
	@JsonProperty
	private String message;
	@JsonProperty
	private Object data;
}
