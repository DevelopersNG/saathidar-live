package com.sathidar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="search_members")
public class SearchMembersModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String	search_marital_status;
	private String	search_mother_tongue;
	private String	search_religions;
	private String	search_cast;
	private String	search_country;
	private String	search_state;
	private String	search_city;	
	private String	search_from_age;
	private String	search_to_age;
	private String	search_from_height;
	private String	search_to_height;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSearch_marital_status() {
		return search_marital_status;
	}
	public void setSearch_marital_status(String search_marital_status) {
		this.search_marital_status = search_marital_status;
	}
	public String getSearch_mother_tongue() {
		return search_mother_tongue;
	}
	public void setSearch_mother_tongue(String search_mother_tongue) {
		this.search_mother_tongue = search_mother_tongue;
	}
	public String getSearch_religions() {
		return search_religions;
	}
	public void setSearch_religions(String search_religions) {
		this.search_religions = search_religions;
	}
	public String getSearch_cast() {
		return search_cast;
	}
	public void setSearch_cast(String search_cast) {
		this.search_cast = search_cast;
	}
	public String getSearch_country() {
		return search_country;
	}
	public void setSearch_country(String search_country) {
		this.search_country = search_country;
	}
	public String getSearch_state() {
		return search_state;
	}
	public void setSearch_state(String search_state) {
		this.search_state = search_state;
	}
	public String getSearch_city() {
		return search_city;
	}
	public void setSearch_city(String search_city) {
		this.search_city = search_city;
	}
	public String getSearch_from_age() {
		return search_from_age;
	}
	public void setSearch_from_age(String search_from_age) {
		this.search_from_age = search_from_age;
	}
	public String getSearch_to_age() {
		return search_to_age;
	}
	public void setSearch_to_age(String search_to_age) {
		this.search_to_age = search_to_age;
	}
	public String getSearch_from_height() {
		return search_from_height;
	}
	public void setSearch_from_height(String search_from_height) {
		this.search_from_height = search_from_height;
	}
	public String getSearch_to_height() {
		return search_to_height;
	}
	public void setSearch_to_height(String search_to_height) {
		this.search_to_height = search_to_height;
	}
	public SearchMembersModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
