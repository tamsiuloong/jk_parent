package com.yaorange.jk.entity.vo;


import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;

public class ApiResponse {

	public ApiResponse(Object body) {
		this.body = body;
	}

	public ApiResponse(String message, String status, String code) {
		this.message = message;
		this.status = status;
		this.code = code;
	}

	public ApiResponse(
					   Object body, String message, String code) {
		super();
		this.body = body;
		this.message = message;
		this.code = code;
	}

	/**
	 * 返回信息主体
	 */
	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	/**
	 * API调用提示信息
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * API接口调用状态
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "{\"body\":" + body + ", \"message\":" + message + ", \"code\":" + code + "}";
	}
	
	public String sourceToString() {
		return "{\"source\":" + body + ", \"message\":" + message + ", \"code\":" + code + "}";
	}

	private Object body;//数据
	private String message= SysConstant.API_MESSAGE_SUCCESS;//消息
	private String status= ResponseCode.API_CODE_CALL_SUCCESS;//状态码
	private String code;
}
