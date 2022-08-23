package com.sathidar.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="success_story")

public class SuccessStoriesModel {
	
	@Id
	//@Column(name = "advt_photo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	@Transient
	private String image_path;
	
	private String success_photo;

	private String description;
	
	@Transient
	private String[] image_base_urls;
	
	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getSuccess_photo() {
		return success_photo;
	}

	public void setSuccess_photo(String success_photo) {
		this.success_photo = success_photo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AddAdvertisement [id=" + id + ", success_photo=" + success_photo + ", description=" + description
				+ ", image_base_urls=" + Arrays.toString(image_base_urls) + "]";
	}

	public SuccessStoriesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
