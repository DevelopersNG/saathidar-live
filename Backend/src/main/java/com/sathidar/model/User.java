package com.sathidar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sathidar.service.AuditModel;


@Entity
@Table(name = "users")
public class User extends AuditModel{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String phone;
	
	private String status;

	private String franchise_code;
	
	@Transient
	private String oldPassword;
	
	@Transient
	private String newPassword;
	
	@Transient
	private String hide_period_time_month;
	
	@Transient
	private String otp;
	
	@Transient
	private String member_id;
	
	private String profilecreatedby;
	
	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "enabled")
	private boolean enabled;

	private String confirmationToken;
	
	private boolean isTempPassword;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>(); 
	
	public String getFranchise_code() {
		return franchise_code;
	}

	public void setFranchise_code(String franchise_code) {
		this.franchise_code = franchise_code;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
	
	public User() {
		id = 0;
		username = "";
		password = "";
		firstName = "";
		lastName = "";
		email = "";
		role = "";
		enabled = false;
		confirmationToken = "";
		gender="";
		phone="";
		status="";
		otp="";
		member_id="";
		profilecreatedby="";
		franchise_code="";	
		setTempPassword(false);
	}
	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public boolean isTempPassword() {
		return isTempPassword;
	}

	public void setTempPassword(boolean isTempPassword) {
		this.isTempPassword = isTempPassword;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfilecreatedby() {
		return profilecreatedby;
	}

	public void setProfilecreatedby(String profilecreatedby) {
		this.profilecreatedby = profilecreatedby;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getHide_period_time_month() {
		return hide_period_time_month;
	}

	public void setHide_period_time_month(String hide_period_time_month) {
		this.hide_period_time_month = hide_period_time_month;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

//	@Override
//	public boolean equals(Object o) {
//
//		if (o == this) {
//			return true;
//		}
//
//		if (!(o instanceof User)) {
//			return false;
//		}
//
//		User u = (User) o;
//
//		return u.getUsername().equals(this.getUsername()) && u.getEmail().equals(this.getEmail())
//				&& u.getRole().equals(this.getRole()) && (u.getEnabled() == this.getEnabled());
//	}
}
