package com.sathidar.model;


public class RazorPayResponse {
	private int statusCode;
	private RazorPayModel razorPay;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public RazorPayModel getRazorPay() {
		return razorPay;
	}

	public void setRazorPay(RazorPayModel razorPay) {
		this.razorPay = razorPay;
	}

}
