package com.sathidar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RazorPayModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String applicationFee;

	private String razorpayOrderId;

	private String secretKey;

	private String paymentId;
	
	private String notes;
	
	private String imageURL;
	
	private String theme;

	private String  merchantName;
	
	private String purchaseDescription;

	private String customerName;

	private String customerEmail;

	private String customerContact;
	
	private String amount;

	private String email;

	private String member_id;
	
	private String plan_name;
	
	private String plan_amount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getPlan_amount() {
		return plan_amount;
	}

	public void setPlan_amount(String plan_amount) {
		this.plan_amount = plan_amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPurchaseDescription() {
		return purchaseDescription;
	}

	public void setPurchaseDescription(String purchaseDescription) {
		this.purchaseDescription = purchaseDescription;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	@Override
	public String toString() {
		return "RazorPayModel [applicationFee=" + applicationFee + ", razorpayOrderId=" + razorpayOrderId
				+ ", secretKey=" + secretKey + ", paymentId=" + paymentId + ", notes=" + notes + ", imageURL="
				+ imageURL + ", theme=" + theme + ", merchantName=" + merchantName + ", purchaseDescription="
				+ purchaseDescription + ", customerName=" + customerName + ", customerEmail=" + customerEmail
				+ ", customerContact=" + customerContact + "]";
	}

	public RazorPayModel(String applicationFee, String razorpayOrderId, String secretKey, String paymentId,
			String notes, String imageURL, String theme, String merchantName, String purchaseDescription,
			String customerName, String customerEmail, String customerContact) {
		super();
		this.applicationFee = applicationFee;
		this.razorpayOrderId = razorpayOrderId;
		this.secretKey = secretKey;
		this.paymentId = paymentId;
		this.notes = notes;
		this.imageURL = imageURL;
		this.theme = theme;
		this.merchantName = merchantName;
		this.purchaseDescription = purchaseDescription;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
	}

	public RazorPayModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
