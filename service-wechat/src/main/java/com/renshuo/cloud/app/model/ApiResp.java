package com.renshuo.cloud.app.model;


import com.renshuo.cloud.app.util.ApiConstant;

import java.util.HashMap;
import java.util.Map;

public class ApiResp {

	private int code = ApiConstant.CODE_SUCCESS;
	private String msg = ApiConstant.MSG_SUCCESS;
	private Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();

	public ApiResp() {
	}

	public ApiResp(String msg) {
		this.msg = msg;
	}

	public ApiResp(int code, String msg) {
		this.code= code;
		this.msg = msg;
	}

	public ApiResp(Map<String, Object> map) {
		data.put("data", map);
	}

	public int getCode() {
		return code;
	}

	public ApiResp setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ApiResp setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Map<String, Object> getData() {
		return this.data.get("data");
	}

	public ApiResp setData(Map<String, Object> dataMap) {
		this.data.put("data", dataMap);
		return this;
	}

	public ApiResp addData(String key, Object value) {
		Map<String, Object> dataMap = getData();
		if (dataMap == null) {
			dataMap = new HashMap<String, Object>();
		}
		dataMap.put(key, value);
		this.data.put("data", dataMap);
		return this;
	}

	@Override
	public String toString() {
		return "[code=" + this.code + "]" //
				+ "[msg=" + this.msg + "]" //
				+ "[data=" + this.data + "]";
	}
}
