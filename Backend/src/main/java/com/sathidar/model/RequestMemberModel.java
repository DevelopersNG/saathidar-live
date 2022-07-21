package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="member_request")
public class RequestMemberModel {

	@Id
	@Column(name = "hide_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String request_from_id;
	
	private String request_to_id;
	
	private String request_status;
	
	private String block_by_id;
	
	private String block_status;
	
//	*********** for sms ****************************
	@Transient
	private String phone_number;
	
	@Transient
	private String message;
	
	@Transient
	private String sender_id;
	
//	*********** for email ****************************	
	
	@Transient
	private String email_to;
	
	@Transient
	private String email_subject;
	
	@Transient
	private String email_body;
	
	public String getEmail_to() {
		return email_to;
	}

	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}

	public String getEmail_subject() {
		return email_subject;
	}

	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}

	public String getEmail_body() {
		return email_body;
	}

	public void setEmail_body(String email_body) {
		this.email_body = email_body;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public String getBlock_status() {
		return block_status;
	}

	public void setBlock_status(String block_status) {
		this.block_status = block_status;
	}

	@Override
	public String toString() {
		return "RequestMemberModel [id=" + id + ", request_from_id=" + request_from_id + ", request_to_id="
				+ request_to_id + ", request_status=" + request_status + ", block_by_id=" + block_by_id
				+ ", block_status=" + block_status + "]";
	}

	public String getRequest_from_id() {
		return request_from_id;
	}

	public void setRequest_from_id(String request_from_id) {
		this.request_from_id = request_from_id;
	}

	public String getRequest_to_id() {
		return request_to_id;
	}

	public void setRequest_to_id(String request_to_id) {
		this.request_to_id = request_to_id;
	}

	public String getBlock_by_id() {
		return block_by_id;
	}

	public void setBlock_by_id(String block_by_id) {
		this.block_by_id = block_by_id;
	}

	public RequestMemberModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
