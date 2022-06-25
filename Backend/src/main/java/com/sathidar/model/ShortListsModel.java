package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member_shortlists")
public class ShortListsModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String shortlist_from_id;
	
	private String shortlist_to_id;
	
	private String shortlist_status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortlist_from_id() {
		return shortlist_from_id;
	}

	public void setShortlist_from_id(String shortlist_from_id) {
		this.shortlist_from_id = shortlist_from_id;
	}

	public String getShortlist_to_id() {
		return shortlist_to_id;
	}

	public void setShortlist_to_id(String shortlist_to_id) {
		this.shortlist_to_id = shortlist_to_id;
	}

	public String getShortlist_status() {
		return shortlist_status;
	}

	public void setShortlist_status(String shortlist_status) {
		this.shortlist_status = shortlist_status;
	}

	public ShortListsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
