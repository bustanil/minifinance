package id.co.skyforce.finance.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DummyController {

	private String message = "Welcome to my application";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
