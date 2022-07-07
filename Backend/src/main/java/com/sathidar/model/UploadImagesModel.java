package com.sathidar.model;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="member_photo")
public class UploadImagesModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Transient
	private String[] image_base_urls;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image_url;
	
	public byte[] getImage_url() {
		return image_url;
	}

	public void setImage_url(byte[] image_url) {
		this.image_url = image_url;
	}
	
	private Integer member_id;

	private String image_path;
	
	private String image_name;
	

	public Integer getId() {
		return id;
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

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public UploadImagesModel(Integer id, String[] image_base_urls, Integer member_id, String image_path,
			String image_name) {
		super();
		this.id = id;
		this.image_base_urls = image_base_urls;
		this.member_id = member_id;
		this.image_path = image_path;
		this.image_name = image_name;
	}

	public UploadImagesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
