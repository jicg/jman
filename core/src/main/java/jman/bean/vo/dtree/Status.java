package com.jicg.jman.bean.vo.dtree;

import java.io.Serializable;

/** 信息状态类*/
public class Status implements Serializable {
	
	private int code = 200;
	
	private String message = "success";
	
	public Status() {}

	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Status [code=" + code + ", message=" + message + "]";
	}

}
