package com.policymanager.api.dto;

import org.springframework.stereotype.Component;

@Component //@service
public class ResponseDto {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 

}