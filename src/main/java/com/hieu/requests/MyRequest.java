package com.hieu.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyRequest {
	@JsonProperty(value = "c")
	private int _c;
	@JsonProperty(value = "r")
	private int _r;
	@JsonProperty(value = "data")
	private Object _data;

	public int get_c() {
		return _c;
	}

	public void set_c(int _c) {
		this._c = _c;
	}

	public int get_r() {
		return _r;
	}

	public void set_r(int _r) {
		this._r = _r;
	}

	public Object get_data() {
		return _data;
	}

	public void set_data(Object _data) {
		this._data = _data;
	}
}
