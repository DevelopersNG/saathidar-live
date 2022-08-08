package com.sathidar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="kyc_document")
public class UploadDocumentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	private Integer member_id;
	
	private String document_name;
	
	private String document_type;
	
	private String document_path;
	
	private String kyc_status;
	
	@Transient
	private String[] image_base_urls;
	
	@Transient
	private String image_id;

	public String[] getImage_base_urls() {
		return image_base_urls;
	}

	public void setImage_base_urls(String[] image_base_urls) {
		this.image_base_urls = image_base_urls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_path() {
		return document_path;
	}

	public void setDocument_path(String document_path) {
		this.document_path = document_path;
	}

	public String getKyc_status() {
		return kyc_status;
	}

	public void setKyc_status(String kyc_status) {
		this.kyc_status = kyc_status;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public UploadDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UploadDocumentModel(Integer id, Integer member_id, String document_name, String document_type,
			String document_path, String kyc_status, String image_id) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.document_name = document_name;
		this.document_type = document_type;
		this.document_path = document_path;
		this.kyc_status = kyc_status;
		this.image_id = image_id;
	}

	@Override
	public String toString() {
		return "UploadDocumentModel [id=" + id + ", member_id=" + member_id + ", document_name=" + document_name
				+ ", document_type=" + document_type + ", document_path=" + document_path + ", kyc_status=" + kyc_status
				+ ", image_id=" + image_id + "]";
	}
	
	
	
	
}
