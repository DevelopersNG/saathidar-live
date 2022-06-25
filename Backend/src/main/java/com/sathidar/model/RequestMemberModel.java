package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_request")
public class RequestMemberModel {

	@Id
	@Column(name = "hide_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer request_from_id;
	
	private Integer request_to_id;
	
	private String request_status;
	
	private Integer block_by_id;
	
	private String block_status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRequest_from_id() {
		return request_from_id;
	}

	public void setRequest_from_id(Integer request_from_id) {
		this.request_from_id = request_from_id;
	}

	public Integer getRequest_to_id() {
		return request_to_id;
	}

	public void setRequest_to_id(Integer request_to_id) {
		this.request_to_id = request_to_id;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public Integer getBlock_by_id() {
		return block_by_id;
	}

	public void setBlock_by_id(Integer block_by_id) {
		this.block_by_id = block_by_id;
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

	public RequestMemberModel(Integer id, Integer request_from_id, Integer request_to_id, String request_status,
			Integer block_by_id, String block_status) {
		super();
		this.id = id;
		this.request_from_id = request_from_id;
		this.request_to_id = request_to_id;
		this.request_status = request_status;
		this.block_by_id = block_by_id;
		this.block_status = block_status;
	}

	public RequestMemberModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
