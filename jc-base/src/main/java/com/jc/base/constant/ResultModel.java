/*
 *The code is written by 51jiecai.com.
 *All rights reserved.
 */
package com.jc.base.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回信息
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("c")
	private String code;
	@JsonProperty("m")
	private String msg;
	@JsonProperty("d")
	private T data;
	public ResultModel(){

	}
	public ResultModel(String c, String m){
		this.code = c;
		this.msg = m;
	}
	
//	@JsonProperty("c")
//	public String getCode() {
//		return code;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}
//	@JsonProperty("m")
//	public String getMsg() {
//		return msg;
//	}
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
//	@JsonProperty("d")
//	public T getData() {
//		return data;
//	}
//	public void setData(T data) {
//		this.data = data;
//	}
	
	
}
