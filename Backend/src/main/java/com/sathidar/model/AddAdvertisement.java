package com.sathidar.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="advertisement")
public class AddAdvertisement {
	
	@Id
	//@Column(name = "advt_photo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	private String advt_photo;

	private String description;
	
	@Transient
	private String[] image_base_urls;
	

	public String[] getImage_base_urls() {
		return image_base_urls;
	}

	public void setImage_base_urls(String[] image_base_urls) {
		this.image_base_urls = image_base_urls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdvt_photo() {
		return advt_photo;
	}

	public void setAdvt_photo(String advt_photo) {
		this.advt_photo = advt_photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AddAdvertisement [id=" + id + ", advt_photo=" + advt_photo + ", description=" + description
				+ ", image_base_urls=" + Arrays.toString(image_base_urls) + "]";
	}
	
	}


	

	
	
	

