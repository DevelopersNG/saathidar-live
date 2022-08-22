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
	@Table(name="kyc_documents")
	public class AdminKycModel {

		
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
	
		private String document_path;
		
		private String document_name;
		
		private String document_type;
		
		@Transient
		private String image_id;

		public AdminKycModel(Integer id, String[] image_base_urls, byte[] image_url, String document_path,
				String document_name, String document_type, String image_id) {
			super();
			this.id = id;
			this.image_base_urls = image_base_urls;
			this.image_url = image_url;
			this.document_path = document_path;
			this.document_name = document_name;
			this.document_type = document_type;
			this.image_id = image_id;
		}

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

		public String getDocument_path() {
			return document_path;
		}

		public void setDocument_path(String document_path) {
			this.document_path = document_path;
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

		public String getImage_id() {
			return image_id;
		}

		public void setImage_id(String image_id) {
			this.image_id = image_id;
		}
		
		

	}