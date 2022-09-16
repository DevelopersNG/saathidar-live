package com.sathidar.model;


	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
import javax.persistence.Transient;

	@Entity
	@Table(name = "plans")
	public class PlanDetailsModel {
	
		@Id
		@Column(name = "plan_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name="plan_name")
		private String plan_name;

		@Column(name="plan_validity")
		private String plan_validity;

		@Column(name="plan_price")
		private String plan_price;
		
		@Column(name="plan_discount")
		private String plan_discount;
	
		@Column(name="discount_price")
		private String discount_price;
		
		@Transient
		private String member_id;
		
		@Transient
		private String plan_status;
		
		@Transient
		private String feature_name;

		public PlanDetailsModel(Integer id, String plan_name, String plan_validity, String plan_price,
				String plan_discount, String member_id, String plan_status) {
			super();
			this.id = id;
			this.plan_name = plan_name;
			this.plan_validity = plan_validity;
			this.plan_price = plan_price;
			this.plan_discount = plan_discount;
			this.member_id = member_id;
			this.plan_status = plan_status;
		}

		public PlanDetailsModel(Integer planId, String planName, String planValidity, String planDiscount,
				String planPrice, boolean b) {
			// TODO Auto-generated constructor stub
		}
		
		public String getDiscount_price() {
			return discount_price;
		}

		public void setDiscount_price(String discount_price) {
			this.discount_price = discount_price;
		}

		public String getFeature_name() {
			return feature_name;
		}

		public void setFeature_name(String feature_name) {
			this.feature_name = feature_name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPlan_name() {
			return plan_name;
		}

		public void setPlan_name(String plan_name) {
			this.plan_name = plan_name;
		}

		public String getPlan_validity() {
			return plan_validity;
		}

		public void setPlan_validity(String plan_validity) {
			this.plan_validity = plan_validity;
		}

		public String getPlan_price() {
			return plan_price;
		}

		public void setPlan_price(String plan_price) {
			this.plan_price = plan_price;
		}

		public String getPlan_discount() {
			return plan_discount;
		}

		public void setPlan_discount(String plan_discount) {
			this.plan_discount = plan_discount;
		}

		public String getMember_id() {
			return member_id;
		}

		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}

		public String getPlan_status() {
			return plan_status;
		}

		public void setPlan_status(String plan_status) {
			this.plan_status = plan_status;
		}

		@Override
		public String toString() {
			return "PlanDetailsModel [id=" + id + ", plan_name=" + plan_name + ", plan_validity=" + plan_validity
					+ ", plan_price=" + plan_price + ", plan_discount=" + plan_discount + ", member_id=" + member_id
					+ ", plan_status=" + plan_status + "]";
		}

		public PlanDetailsModel() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
	}
		
		