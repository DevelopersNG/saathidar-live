package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hide_content")
public class HideContentModel {

	@Id
	@Column(name = "hide_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String column_name;
	
	@Transient
	private String status;
	
	private int member_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "HideContentModel [id=" + id + ", column_name=" + column_name + ", status=" + status + ", member_id="
				+ member_id + "]";
	}

	public HideContentModel(Integer id, String column_name, String status, int member_id) {
		super();
		this.id = id;
		this.column_name = column_name;
		this.status = status;
		this.member_id = member_id;
	}

	public HideContentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
